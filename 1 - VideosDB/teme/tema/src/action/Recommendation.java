package action;

import common.Constants;
import database.Database;
import entertainment.GenrePopularity;
import entertainment.Video;
import user.User;
import utils.Sorter;
import utils.Utils;

import java.util.ArrayList;

/**
 * Contains static methods used to execute each type of recommendation.
 */

public final class Recommendation {

    private Recommendation() {

    }

    /**
     * Calls the function corresponding to the recommendation type
     * @param action to be executed
     * @return the message with the recommendation
     */
    public static String execute(final Action action) {
        String type = action.getType();
        User user = User.getUser(action.getUsername());
        String genre = action.getGenre();
        String subscriptionType = user.getSubscriptionType();
        boolean userIsPremium = subscriptionType.equals(Constants.PREMIUM);
        boolean addBrackets = false;

        ArrayList<String> result = new ArrayList<>();

        ArrayList<Video> videoList = new ArrayList<>();
        videoList.addAll(Database.getDatabase().getMovies());
        videoList.addAll(Database.getDatabase().getShows());

        Video.setDatabaseID();

        if (type.equals(Constants.STANDARD)) {
            result = standard(user, videoList);
        } else if (type.equals(Constants.BEST_UNSEEN)) {
            result = bestUnseen(user, videoList);
            type = Constants.BEST_RATED_UNSEEN;
        } else if (type.equals(Constants.POPULAR) && userIsPremium) {
            result = popular(user, videoList);
        } else if (type.equals(Constants.FAVORITE) && userIsPremium) {
            result = favorite(user, videoList);
        } else if (type.equals(Constants.SEARCH) && userIsPremium) {
            result = search(user, videoList, genre);
            addBrackets = true;
        }

        return makeMessage(result, type, addBrackets);
    }

    /**
     * Creates message based on the result of the recommendation
     * @param result of the recommendation
     * @param type of the recommendation
     * @param addBrackets is TRUE if the recommendation result has multiple items, FALSE otherwise
     * @return the message with the result of the recommendation
     */
    private static String makeMessage(final ArrayList<String> result, final String type,
                                      final boolean addBrackets) {
        StringBuilder message = new StringBuilder(type);
        int resultSize = result.size();

        if (resultSize == 0) {
            message.append("Recommendation cannot be applied!");
        } else {
            message.append("Recommendation result: ");

            if (addBrackets) {
                message.append("[");
            }

            for (String item : result) {
                message.append(item).append(", ");
            }
            message = new StringBuilder(message.substring(0, message.length() - 2));

            if (addBrackets) {
                message.append("]");
            }
        }

        return Utils.capitalizeFirstLetter(message.toString());
    }

    /**
     * Returns the first unseen video in the database
     * @param user for which the recommendation is made
     * @param videoList containing all the videos in the database
     * @return an ArrayList containing the name of the video
     */
    private static ArrayList<String> standard(final User user, final ArrayList<Video> videoList) {
        return singleVideoResult(videoList, user);
    }

    /**
     * Returns the first best video unseen by the given user
     * @param user for which the recommendation is made
     * @param videoList containing all the videos in the database
     * @return an ArrayList containing the name of the best unseen video
     */
    private static ArrayList<String> bestUnseen(final User user, final ArrayList<Video> videoList) {
        Sorter.sortVideosByBest(videoList);
        return singleVideoResult(videoList, user);
    }

    /**
     * Returns the first unseen video of the most popular genre
     * @param user for which the recommendation is made
     * @param videoList containing all the videos in the database
     * @return an ArrayList containing the name of the video
     */
    private static ArrayList<String> popular(final User user, final ArrayList<Video> videoList) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<GenrePopularity> genrePopularityList = GenrePopularity.makeList(videoList);

        Sorter.sortGenrePopularity(genrePopularityList);

        for (GenrePopularity item : genrePopularityList) {
            for (Video video : videoList) {
                if (video.getGenres().contains(item.getGenre())
                        && !user.getHistory().containsKey(video.getTitle())) {
                    result.add(video.getTitle());
                    return result;
                }
            }
        }

        return result;
    }

    /**
     * Returns the video that is most common in the user's favorite list
     * @param user for which the recommendation is made
     * @param videoList containing all the videos in the database
     * @return an ArrayList containing the name of the video
     */
    private static ArrayList<String> favorite(final User user, final ArrayList<Video> videoList) {
        ArrayList<String> result = new ArrayList<>();

        if (videoList.size() == 0) {
            return result;
        }

        Sorter.sortVideosByFavourite(videoList);
        return singleVideoResult(videoList, user);
    }

    /**
     * Returns all videos of a certain genre, unseen by the given user
     * @param user for which the recommendation is made
     * @param videoList containing all the videos in the database
     * @param genre the videos should be a part of
     * @return an ArrayList containing the name of the video
     */
    private static ArrayList<String> search(final User user, final ArrayList<Video> videoList,
                                            final String genre) {
        ArrayList<String> result = new ArrayList<>();

        Sorter.sortVideosByAverageRating(videoList, Constants.ASCENDING);

        for (Video video : videoList) {
            if (!video.getGenres().contains(genre)) {
                continue;
            }

            if (!user.getHistory().containsKey(video.getTitle())) {
                result.add(video.getTitle());
            }
        }

        return result;
    }

    /**
     * Returns the name of first video in the video list, unseen by the given user
     * @param videoList containing all the videos in the database, sorted by a certain criteria
     * @param user for which the recommendation is made
     * @return an ArrayList containing the name of the first unseen video
     */
    private static ArrayList<String> singleVideoResult(final ArrayList<Video> videoList,
                                                       final User user) {
        ArrayList<String> result = new ArrayList<>();

        for (Video video : videoList) {
            if (!user.getHistory().containsKey(video.getTitle())) {
                result.add(video.getTitle());
                break;
            }
        }

        return result;
    }
}
