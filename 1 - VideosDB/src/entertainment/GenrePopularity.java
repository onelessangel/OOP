package entertainment;

import common.Constants;
import utils.Utils;

import java.util.ArrayList;

/**
 * Information about a genre and its popularity
 */

public final class GenrePopularity {
    private String genre;
    private int popularity;

    public GenrePopularity(final String genre) {
        this.genre = genre;
        popularity = 0;
    }

    /**
     * Increases popularity for element
     * @param numberViews to be added to the popularity counter of current element
     */
    public void increasePopularity(final int numberViews) {
        popularity += numberViews;
    }

    /**
     * Creates list of GenrePopularity items, computing the number of views for each genre
     * @param videoList containing all the videos in the database
     * @return an ArrayList with GenrePopularity elements corresponding to each genre
     */
    public static ArrayList<GenrePopularity> makeList(final ArrayList<Video> videoList) {
        ArrayList<GenrePopularity> list = new ArrayList<>();
        String snakeCaseGenre;

        for (Genre genre : Genre.values()) {
            snakeCaseGenre = genre.name().toLowerCase();
            snakeCaseGenre = Utils.capitalizeFirstLetter(snakeCaseGenre);
            list.add(new GenrePopularity(snakeCaseGenre));
        }

        for (GenrePopularity item : list) {
            if (item.getGenre().equals(Constants.TV_MOVIE_BASIC)) {
                item.setGenre(Constants.TV_MOVIE);
            } else if (item.getGenre().equals(Constants.SCI_FI_FANTASY_BASIC)) {
                item.setGenre(Constants.SCI_FI_FANTASY);
            } else if (item.getGenre().equals(Constants.SCIENCE_FICTION_BASIC)) {
                item.setGenre(Constants.SCIENCE_FICTION);
            } else if (item.getGenre().equals(Constants.ACTION_ADVENTURE_BASIC)) {
                item.setGenre(Constants.ACTION_ADVENTURE);
            }
        }

        for (Video video : videoList) {
            for (String genre : video.getGenres()) {
                for (GenrePopularity item : list) {
                    if (item.getGenre().equals(genre)) {
                        item.increasePopularity(video.getViewCounter());
                        break;
                    }
                }
            }
        }

        return list;
    }

    public String getGenre() {
        return genre;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setGenre(final String genre) {
        this.genre = genre;
    }
}
