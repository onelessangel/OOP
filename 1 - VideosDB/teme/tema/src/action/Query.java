package action;

import actor.Actor;
import actor.ActorsAwards;
import common.Constants;
import database.Database;
import entertainment.Video;
import user.User;
import utils.Sorter;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains static methods used to execute each type of query.
 */

public final class Query {

    private Query() {

    }

    /**
     * Calls the function corresponding to the query type
     * @param action to be executed
     * @return the message with the result of the query
     */
    public static String execute(final Action action) {
        String objectType = action.getObjectType();
        ArrayList<String> result = new ArrayList<>();

        if (objectType.equals(Constants.ACTORS)) {
            result = actorQuery(action);
        } else if (objectType.equals(Constants.MOVIES) || objectType.equals(Constants.SHOWS)) {
            result = videoQuery(action);
        } else if (objectType.equals(Constants.USERS)) {
            result = userQuery(action);
        }

        return makeMessage(result);
    }

    /**
     * Creates message based on the result of the query
     * @param result of the query
     * @return the message with the result of the query
     */
    private static String makeMessage(final ArrayList<String> result) {
        StringBuilder message = new StringBuilder("Query result: [");

        if (result.size() != 0) {
            for (String item : result) {
                message.append(item).append(", ");
            }
            message = new StringBuilder(message.substring(0, message.length() - 2));
        }

        message.append("]");
        return message.toString();
    }

    /**
     * Calls the functions corresponding to the correct actor query
     * @param action to be executed
     * @return an ArrayList containing the names of the actors returned by the query
     */
    private static ArrayList<String> actorQuery(final Action action) {
        int number = action.getNumber();
        String sortType = action.getSortType();
        String criteria = action.getCriteria();
        List<String> words = action.getFilters().get(2);
        List<String> awards = action.getFilters().get(3);
        ArrayList<String> result = new ArrayList<>();

        if (criteria.equals(Constants.AVERAGE)) {
            result = actorAverage(number, sortType);
        } else if (criteria.equals(Constants.AWARDS)) {
            result = actorAwards(awards, sortType);
        } else if (criteria.equals(Constants.FILTER_DESCRIPTIONS)) {
            result = actorFilterDescription(words, sortType);
        }

        return result;
    }

    /**
     * Calls the functions corresponding to the correct video query
     * @param action to be executed
     * @return an ArrayList containing the titles of the videos returned by the query
     */
    private static ArrayList<String> videoQuery(final Action action) {
        int number = action.getNumber();
        String objectType = action.getObjectType();
        String sortType = action.getSortType();
        String criteria = action.getCriteria();
        String yearFiler = action.getFilters().get(0).get(0);
        String genre = action.getFilters().get(1).get(0);

        int year = 0;
        if (yearFiler != null) {
            year = Integer.parseInt(yearFiler);
        }

        ArrayList<String> result = null;

        if (criteria.equals(Constants.RATINGS)) {
            result = videoRating(number, year, genre, objectType, sortType);
        } else if (criteria.equals(Constants.FAVORITE)) {
            result = videoFavorite(number, year, genre, objectType, sortType);
        } else if (criteria.equals(Constants.LONGEST)) {
            result = videoLongest(number, year, genre, objectType, sortType);
        } else if (criteria.equals(Constants.MOST_VIEWED)) {
            result = videoMostViewed(number, year, genre, objectType, sortType);
        }

        return result;
    }

    /**
     * Calls the function associated with the user query.
     * @param action to be executed
     * @return an ArrayList containing the usernames of the users returned by the query
     */
    private static ArrayList<String> userQuery(final Action action) {
        int number = action.getNumber();
        String sortType = action.getSortType();

        return userNumRatings(number, sortType);
    }

    /**
     * Returns the first N actors, sorted by the average of the videos in which they played
     * @param number of the returned actors
     * @param sortType of the list of actors
     * @return an ArrayList containing the names of the actors returned by the query
     */
    private static ArrayList<String> actorAverage(final int number, final String sortType) {
        ArrayList<Actor> actorList = Database.getDatabase().getActors();
        ArrayList<String> result = new ArrayList<>();
        int counter = 0;

        for (Actor actor : Database.getDatabase().getActors()) {
            actor.setAverageRating();
        }

        Sorter.sortActorsByAverageRating(actorList, sortType);

        for (Actor actor : actorList) {
            if (actor.getAverageRating() != 0) {
                if (counter < number) {
                    result.add(actor.getName());
                    counter++;
                }
            }
        }

        return result;
    }

    /**
     * Returns all actors who have certain awards
     * @param awards list containing the awards each actor has to have
     * @param sortType of the list of actors
     * @return an ArrayList containing the names of the actors returned by the query
     */
    private static ArrayList<String> actorAwards(final List<String> awards, final String sortType) {
        ArrayList<Actor> actorList = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();
        boolean actorHasAllAwards;

        for (Actor actor : Database.getDatabase().getActors()) {
            actorHasAllAwards = true;

            for (String award : awards) {
                if (!actor.getAwards().containsKey(ActorsAwards.valueOf(award))) {
                    actorHasAllAwards = false;
                    break;
                }
            }
            if (actorHasAllAwards) {
                actorList.add(actor);
            }
        }

        Sorter.sortActorsByAwards(actorList, sortType);

        for (Actor actor : actorList) {
            result.add(actor.getName());
        }

        return result;
    }

    /**
     * Returns all actors who have certain words in their description
     * @param words to be contained in the description
     * @param sortType of the list of actors
     * @return an ArrayList containing the names of the actors returned by the query
     */
    private static ArrayList<String> actorFilterDescription(final List<String> words,
                                                            final String sortType) {
        ArrayList<Actor> actorList = Database.getDatabase().getActors();
        ArrayList<String> result = new ArrayList<>();
        boolean actorHasAllWordsInDescription;

        for (Actor actor : actorList) {
            actorHasAllWordsInDescription = true;

            for (String word : words) {
                if (!wordIsInDescription(actor.getDescription(), word)) {
                    actorHasAllWordsInDescription = false;
                    break;
                }
            }
            if (actorHasAllWordsInDescription) {
                result.add(actor.getName());
            }
        }

        Sorter.sortByName(result, sortType);

        return result;
    }

    /**
     * Returns the first N videos, sorted by rating
     * @param number of the returned videos
     * @param year in which the video must have been released
     * @param genre of which the video must be a part of
     * @param objectType the type of video: MOVIE or SHOW
     * @param sortType of the list of videos
     * @return an ArrayList containing the titles of the videos returned by the query
     */
    private static ArrayList<String> videoRating(final int number, final int year,
                                                 final String genre, final String objectType,
                                                 final String sortType) {
        ArrayList<Video> videoList = Video.makeVideoList(objectType);
        ArrayList<String> result = new ArrayList<>();
        int counter = 0;

        Sorter.sortVideosByAverageRating(videoList, sortType);

        for (Video item : videoList) {
            if (item.getYear() != year && year != 0
                    || !item.getGenres().contains(genre) && genre != null) {
                continue;
            }

            if (item.getAverageRating() != 0) {
                if (counter < number) {
                    result.add(item.getTitle());
                    counter++;
                }
            }
        }

        return result;
    }

    /**
     * Returns the first N videos, sorted by the number of appearances in users' favorite list
     * @param number of the returned videos
     * @param year in which the video must have been released
     * @param genre of which the video must be a part of
     * @param objectType the type of video: MOVIE or SHOW
     * @param sortType of the list of videos
     * @return an ArrayList containing the titles of the videos returned by the query
     */
    private static ArrayList<String> videoFavorite(final int number, final int year,
                                                   final String genre, final String objectType,
                                                   final String sortType) {
        ArrayList<Video> videoList = Video.makeVideoList(objectType);
        ArrayList<String> result = new ArrayList<>();
        int counter = 0;

        Sorter.sortVideosByFavouriteCounter(videoList, sortType);

        for (Video item : videoList) {
            if (item.getYear() != year && year != 0
                    || !item.getGenres().contains(genre) && genre != null) {
                continue;
            }

            if (item.getFavouriteCounter() != 0) {
                if (counter < number) {
                    result.add(item.getTitle());
                    counter++;
                }
            }
        }

        return result;
    }

    /**
     * Returns the first N videos, sorted by their length
     * @param number of the returned videos
     * @param year in which the video must have been released
     * @param genre of which the video must be a part of
     * @param objectType the type of video: MOVIE or SHOW
     * @param sortType of the list of videos
     * @return an ArrayList containing the titles of the videos returned by the query
     */
    private static ArrayList<String> videoLongest(final int number, final int year,
                                                  final String genre, final String objectType,
                                                  final String sortType) {
        ArrayList<Video> videoList = Video.makeVideoList(objectType);
        ArrayList<String> result = new ArrayList<>();
        int counter = 0;

        Sorter.sortVideosByDuration(videoList, sortType);

        for (Video item : videoList) {
            if (item.getYear() != year && year != 0
                    || !item.getGenres().contains(genre) && genre != null) {
                continue;
            }

            if (item.getDuration() != 0) {
                if (counter < number) {
                    result.add(item.getTitle());
                    counter++;
                }
            }
        }

        return result;
    }

    /**
     * Returns the first N videos, sorted by the number of views
     * @param number of the returned videos
     * @param year in which the video must have been released
     * @param genre of which the video must be a part of
     * @param objectType the type of video: MOVIE or SHOW
     * @param sortType of the list of videos
     * @return an ArrayList containing the titles of the videos returned by the query
     */
    private static ArrayList<String> videoMostViewed(final int number, final int year,
                                                     final String genre, final String objectType,
                                                     final String sortType) {
        ArrayList<Video> videoList = Video.makeVideoList(objectType);
        ArrayList<String> result = new ArrayList<>();
        int counter = 0;

        Sorter.sortVideosByViewCounter(videoList, sortType);

        for (Video item : videoList) {
            if (item.getYear() != year && year != 0
                    || !item.getGenres().contains(genre) && genre != null) {
                continue;
            }

            if (item.getViewCounter() != 0) {
                if (counter < number) {
                    result.add(item.getTitle());
                    counter++;
                }
            }
        }

        return result;
    }

    /**
     * Returns the first N users, sorted by the number of given ratings
     * @param number of the returned users
     * @param sortType of the list of users
     * @return an ArrayList containing the usernames of the users returned by the query
     */
    private static ArrayList<String> userNumRatings(final int number, final String sortType) {
        for (User user : Database.getDatabase().getUsers()) {
            user.setNumberOfRatings();
        }

        ArrayList<User> userList = Database.getDatabase().getUsers();
        ArrayList<String> result = new ArrayList<>();
        int counter = 0;

        Sorter.sortUsersByNumberOfRatings(userList, sortType);

        for (User user : userList) {
            if (user.getNumberOfRatings() != 0) {
                if (counter < number) {
                    result.add(user.getUsername());
                    counter++;
                }
            }
        }

        return result;
    }

    /**
     * Find a word in an actor's description
     * @param description of the actor
     * @param word to be searched for in the description
     * @return TRUE if the word is found in the description, FALSE otherwise
     */
    private static boolean wordIsInDescription(final String description, final String word) {
        String[] prefix = {" ", "-"};
        String[] suffix = {" ", ",", "."};

        for (String characterPrefix : prefix) {
            for (String characterSuffix : suffix) {
                if (description.toLowerCase().contains(characterPrefix + word + characterSuffix)) {
                    return true;
                }
            }
        }

        return false;
    }
}
