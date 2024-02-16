package entertainment;

import fileio.SerialInputData;

import java.util.ArrayList;

/**
 * Information about a tv show
 */

public final class Show extends Video {
    private final ArrayList<Season> seasons;

    public Show(final SerialInputData serial) {
        super(serial.getTitle(), serial.getYear(), serial.getGenres(), serial.getCast(), 0);
        seasons = serial.getSeasons();
        setDuration();
    }

    /**
     * Computes total duration of the show
     */
    private void setDuration() {
        for (Season season : seasons) {
            duration += season.getDuration();
        }
    }

    /**
     * Adds a new rating in the rating list of given season
     * @param rating to be added to the ratings list
     * @param season the rating is given to
     */
    public void addRating(final double rating, final int season) {
        seasons.get(season - 1).getRatings().add(rating);
    }

    /**
     * Computes the average rating of the current show, based in its seasons' ratings lists
     */
    public void computeAverageRating() {
        double totalAverage = 0;
        double seasonAverage;

        for (Season season : seasons) {
            seasonAverage = 0;

            for (Double entry : season.getRatings()) {
                seasonAverage += entry;
            }

            if (season.getRatings().size() != 0) {
                seasonAverage /= season.getRatings().size();
            }
            totalAverage += seasonAverage;
        }
        totalAverage /= seasons.size();

        averageRating = totalAverage;
    }
}
