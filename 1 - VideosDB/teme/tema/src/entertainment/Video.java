package entertainment;

import common.Constants;
import database.Database;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Information about a video
 */

public class Video {
    protected String title;
    protected int year;
    protected ArrayList<String> genres;
    protected ArrayList<String> cast;
    protected int duration;
    protected double averageRating;
    /**
     * The number of appearances in the user's favorites list
     */
    protected int favouriteCounter;
    /**
     * The total number of views
     */
    protected int viewCounter;
    /**
     * The position of the video in the database
     */
    protected int databaseID;

    public Video(final String title, final int year, final ArrayList<String> genres,
                 final ArrayList<String> cast, final int duration) {
        this.title = title;
        this.year = year;
        this.genres = genres;
        this.cast = cast;
        this.duration = duration;
        averageRating = 0;
        favouriteCounter = 0;
        viewCounter = 0;
    }

    /**
     * Finds movie in database
     * @param title of the searched movie
     * @return the movie with the given title
     */
    public static Movie findMovie(final String title) {
        for (Movie movie : Database.getDatabase().getMovies()) {
            if (movie.getTitle().equals(title)) {
                return movie;
            }
        }
        return null;
    }

    /**
     * Find show in database
     * @param title of the searched show
     * @return the show with the given title
     */
    public static Show findShow(final String title) {
        for (Show show : Database.getDatabase().getShows()) {
            if (show.getTitle().equals(title)) {
                return show;
            }
        }
        return null;
    }

    /**
     * Finds the video with the given title and adds grade to its rating list
     * @param title of the video
     * @param grade of the video
     * @param season of the show the rating is given to. It is 0 if the video is a movie
     */
    public static void addRating(final String title, final double grade, final int season) {
        if (findMovie(title) != null) {
            Objects.requireNonNull(findMovie(title)).addRating(grade);
            Objects.requireNonNull(findMovie(title)).computeAverageRating();
        } else {
            Objects.requireNonNull(findShow(title)).addRating(grade, season);
            Objects.requireNonNull(findShow(title)).computeAverageRating();
        }
    }

    /**
     * Returns the list of movies/shows from the database, depending on the given object type
     * @param objectType of elements in the list
     * @return an ArrayList of Video elements, a copy of the corresponding list from the database
     */
    public static ArrayList<Video> makeVideoList(final String objectType) {
        ArrayList<Video> videoList;

        if (objectType.equals(Constants.MOVIES)) {
            videoList = new ArrayList<>(Database.getDatabase().getMovies());
        } else {
            videoList = new ArrayList<>(Database.getDatabase().getShows());
        }

        return videoList;
    }

    /**
     * Sets the database ID for each video
     */
    public static void setDatabaseID() {
        int counter = 1;
        for (Video video : Database.getDatabase().getMovies()) {
            video.databaseID = counter;
            counter++;
        }

        for (Video video : Database.getDatabase().getShows()) {
            video.databaseID = counter;
            counter++;
        }
    }

    /**
     * Increases the counter that keeps track of the video's number of appearances in the user's
     * favorites list
     */
    public void increaseFavouriteCounter() {
        favouriteCounter++;
    }

    /**
     * Increases the counter that keeps track of the video's number of views
     * @param numberOfViews to be added to the counter
     */
    public void increaseViewCounter(final int numberOfViews) {
        viewCounter += numberOfViews;
    }

    /**
     * for checkstyle
     * Getter used for accessing the title field
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * for checkstyle
     * Getter used for accessing the year field
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * for checkstyle
     * Getter used for accessing the genre list
     * @return the genre list
     */
    public ArrayList<String> getGenres() {
        return genres;
    }

    /**
     * for checkstyle
     * Getter used for accessing the cast
     * @return the cast
     */
    public ArrayList<String> getCast() {
        return cast;
    }

    /**
     * for checkstyle
     * Getter used for accessing the duration
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * for checkstyle
     * Getter used for accessing the average rating
     * @return the average rating
     */
    public double getAverageRating() {
        return averageRating;
    }

    /**
     * for checkstyle
     * Getter used for accessing the favorite counter
     * @return the favorite counter
     */
    public int getFavouriteCounter() {
        return favouriteCounter;
    }

    /**
     * for checkstyle
     * Getter used for accessing the view counter
     * @return the view counter
     */
    public int getViewCounter() {
        return viewCounter;
    }

    /**
     * for checkstyle
     * Getter used for accessing the database ID
     * @return the database ID
     */
    public int getDatabaseID() {
        return databaseID;
    }
}
