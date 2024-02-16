package utils;

import actor.Actor;
import common.Constants;
import entertainment.GenrePopularity;
import entertainment.Video;
import user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Contains static methods used for sorting various types of lists.
 */

public final class Sorter {

    private Sorter() {

    }

    /**
     * Sorts the list of actors by their average rating.
     * The second sorting criteria is the alphabetical order of their names.
     * @param actorList to be sorted
     * @param sortType of the list of actors: ASCENDING or DESCENDING
     */
    public static void sortActorsByAverageRating(final ArrayList<Actor> actorList,
                                                 final String sortType) {
        actorList.sort(Comparator
                .comparing(Actor::getAverageRating)
                .thenComparing(Actor::getName));

        if (sortType.equals(Constants.DESCENDING)) {
            Collections.reverse(actorList);
        }
    }

    /**
     * Sorts the list of actors by their number of awards.
     * The second sorting criteria is the alphabetical order of their names.
     * @param actorList to be sorted
     * @param sortType of the list of actors: ASCENDING or DESCENDING
     */
    public static void sortActorsByAwards(final ArrayList<Actor> actorList, final String sortType) {
        actorList.sort(Comparator
                .comparing(Actor::getNumberOfAwards)
                .thenComparing(Actor::getName));

        if (sortType.equals(Constants.DESCENDING)) {
            Collections.reverse(actorList);
        }
    }

    /**
     * Sorts given list alphabetically.
     * @param list to be sorted
     * @param sortType of the list: ASCENDING or DESCENDING
     */
    public static void sortByName(final ArrayList<String> list, final String sortType) {
        Collections.sort(list);

        if (sortType.equals(Constants.DESCENDING)) {
            Collections.reverse(list);
        }
    }

    /**
     * Sorts the list of videos by their average rating.
     * The second sorting criteria is the alphabetical order of their titles.
     * @param videoList to be sorted
     * @param sortType of the video list: ASCENDING or DESCENDING
     */
    public static void sortVideosByAverageRating(final ArrayList<Video> videoList,
                                                 final String sortType) {
        videoList.sort(Comparator
                .comparing(Video::getAverageRating)
                .thenComparing(Video::getTitle));

        if (sortType.equals(Constants.DESCENDING)) {
            Collections.reverse(videoList);
        }
    }

    /**
     * Sorts the list of videos by how many users have them in their favorite list.
     * The second sorting criteria is the alphabetical order of their titles.
     * @param videoList to be sorted
     * @param sortType of the video list: ASCENDING or DESCENDING
     */
    public static void sortVideosByFavouriteCounter(final ArrayList<Video> videoList,
                                                    final String sortType) {
        videoList.sort(Comparator
                .comparing(Video::getFavouriteCounter)
                .thenComparing(Video::getTitle));

        if (sortType.equals(Constants.DESCENDING)) {
            Collections.reverse(videoList);
        }
    }

    /**
     * Sorts the list of videos by their total duration.
     * The second sorting criteria is the alphabetical order of their titles.
     * @param videoList to be sorted
     * @param sortType of the video list: ASCENDING or DESCENDING
     */
    public static void sortVideosByDuration(final ArrayList<Video> videoList,
                                            final String sortType) {
        videoList.sort(Comparator
                .comparing(Video::getDuration)
                .thenComparing(Video::getTitle));

        if (sortType.equals(Constants.DESCENDING)) {
            Collections.reverse(videoList);
        }
    }

    /**
     * Sorts the list of videos by their total number of views.
     * The second sorting criteria is the alphabetical order of their titles.
     * @param videoList to be sorted
     * @param sortType of the video list: ASCENDING or DESCENDING
     */
    public static void sortVideosByViewCounter(final ArrayList<Video> videoList,
                                               final String sortType) {
        videoList.sort(Comparator
                .comparing(Video::getViewCounter)
                .thenComparing(Video::getTitle));

        if (sortType.equals(Constants.DESCENDING)) {
            Collections.reverse(videoList);
        }
    }

    /**
     * Sorts the list of users by their total number of given ratings.
     * The second sorting criteria is the alphabetical order of their usernames.
     * @param userList to be sorted
     * @param sortType of the user list: ASCENDING or DESCENDING
     */
    public static void sortUsersByNumberOfRatings(final ArrayList<User> userList,
                                                  final String sortType) {
        userList.sort(Comparator
                .comparing(User::getNumberOfRatings)
                .thenComparing(User::getUsername));

        if (sortType.equals(Constants.DESCENDING)) {
            Collections.reverse(userList);
        }
    }

    /**
     * Sorts the list of videos descending by how many users have them in their favorite list.
     * The second sorting criteria is the position of the video in the database.
     * @param videoList to be sorted
     */
    public static void sortVideosByFavourite(final ArrayList<Video> videoList) {
        videoList.sort(Comparator
                .comparing(Video::getFavouriteCounter).reversed()
                .thenComparing(Video::getDatabaseID));
    }

    /**
     * Sorts the list of videos descending by their average rating.
     * The second sorting criteria is the position of the video in the database.
     * @param videoList to be sorted
     */
    public static void sortVideosByBest(final ArrayList<Video> videoList) {
        videoList.sort(Comparator
                .comparing(Video::getAverageRating).reversed()
                .thenComparing(Video::getDatabaseID));
    }

    /**
     * Sorts the list descending by the genre's popularity.
     * @param list to be sorted
     */
    public static void sortGenrePopularity(final ArrayList<GenrePopularity> list) {
        list.sort(Comparator.comparing(GenrePopularity::getPopularity).reversed());
    }
}
