package entertainment;

import fileio.MovieInputData;

import java.util.ArrayList;

/**
 * Information about a movie.
 */

public final class Movie extends Video {
    private final ArrayList<Double> ratings;

    public Movie(final MovieInputData movie) {
        super(movie.getTitle(), movie.getYear(), movie.getGenres(), movie.getCast(),
                movie.getDuration());
        ratings = new ArrayList<>();
    }

    /**
     * Adds a new rating in the rating list of current movie
     * @param rating to be added to the ratings list
     */
    public void addRating(final double rating) {
        ratings.add(rating);
    }

    /**
     * Computes the average rating of the current movie, based on its ratings list
     */
    public void computeAverageRating() {
        double average = 0;
        for (Double entry : ratings) {
            average += entry;
        }
        average /= ratings.size();

        averageRating = average;
    }

    public int getDuration() {
        return duration;
    }

    public ArrayList<Double> getRatings() {
        return ratings;
    }
}
