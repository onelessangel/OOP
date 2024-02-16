package utils;

import java.util.Objects;

import static entertainment.Video.findMovie;
import static entertainment.Video.findShow;

/**
 * Contains static methods used for increasing the favorite/view counter of a video
 * with a given title.
 */

public final class Counter {

    private Counter() {

    }

    /**
     * Finds a video in the database and increases its favorite counter
     * @param title of the searched video
     */
    public static void increaseFavouriteCounter(final String title) {
        if (findMovie(title) != null) {
            Objects.requireNonNull(findMovie(title)).increaseFavouriteCounter();
        } else {
            Objects.requireNonNull(findShow(title)).increaseFavouriteCounter();
        }
    }

    /**
     * Finds a video in the database and increases its view counter
     * @param title of the searched video
     * @param numberOfViews to be added to the video's view counter
     */
    public static void increaseViewCounter(final String title, final int numberOfViews) {
        if (findMovie(title) != null) {
            Objects.requireNonNull(findMovie(title)).increaseViewCounter(numberOfViews);
        } else {
            Objects.requireNonNull(findShow(title)).increaseViewCounter(numberOfViews);
        }
    }
}
