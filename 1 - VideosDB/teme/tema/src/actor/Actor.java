package actor;

import entertainment.Movie;
import entertainment.Show;
import fileio.ActorInputData;

import java.util.ArrayList;
import java.util.Map;

import static entertainment.Video.findMovie;
import static entertainment.Video.findShow;

/**
 * Information about an actor.
 */

public final class Actor {
    private final String name;
    private final String description;
    private final ArrayList<String> filmography;
    private final Map<ActorsAwards, Integer> awards;
    private double averageRating;
    private int numberOfAwards;

    public Actor(final ActorInputData actor) {
        this.name = actor.getName();
        this.description = actor.getCareerDescription();
        this.filmography = actor.getFilmography();
        this.awards = actor.getAwards();
        averageRating = 0;

        numberOfAwards = 0;
        for (Map.Entry<ActorsAwards, Integer> entry : awards.entrySet()) {
            numberOfAwards += entry.getValue();
        }
    }

    /**
     * Computes average rating based on the ratings of all the videos the actor plays in
     */
    public void setAverageRating() {
        double movieAverage = 0;
        double showAverage = 0;
        int movieCount = 0;
        int showCount = 0;
        Movie movie;
        Show show;

        for (String title : filmography) {
            movie = findMovie(title);
            show = findShow(title);

            if (movie != null && movie.getAverageRating() != 0) {
                movieAverage += movie.getAverageRating();
                movieCount++;
                continue;
            }

            if (show != null && show.getAverageRating() != 0) {
                showAverage += show.getAverageRating();
                showCount++;
            }
        }

        if (movieCount != 0) {
            movieAverage /= movieCount;
        }

        if (showCount != 0) {
            showAverage /= showCount;
        }

        if (movieAverage != 0 && showAverage == 0) {
            averageRating = movieAverage;
        } else if (movieAverage == 0 && showAverage != 0) {
            averageRating = showAverage;
        } else {
            averageRating = (movieAverage + showAverage) / 2;
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Map<ActorsAwards, Integer> getAwards() {
        return awards;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public int getNumberOfAwards() {
        return numberOfAwards;
    }
}
