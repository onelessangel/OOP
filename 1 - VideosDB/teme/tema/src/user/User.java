package user;

import database.Database;
import fileio.UserInputData;
import utils.Counter;

import java.util.ArrayList;
import java.util.Map;

/**
 * Information about a user
 */

public final class User {
    private String username;
    private String subscriptionType;
    private Map<String, Integer> history;
    private ArrayList<String> favoriteVideos;
    /**
     * List of rated videos
     */
    private ArrayList<String> ratedVideos;
    /**
     * The total number of ratings given by the user
     */
    private int numberOfRatings;

    public User() {

    }

    public User(final UserInputData user) {
        username = user.getUsername();
        subscriptionType = user.getSubscriptionType();
        history = user.getHistory();
        favoriteVideos = user.getFavoriteMovies();
        ratedVideos = new ArrayList<>();
        numberOfRatings = 0;
        setFavoriteCounter();
        setViewCounter();
    }

    /**
     * Increases the favorite counter of each video in the user's favorite list
     */
    private void setFavoriteCounter() {
        for (String title : favoriteVideos) {
            Counter.increaseFavouriteCounter(title);
        }
    }

    /**
     * Increases the view counter of each video watched by the user with its corresponding number
     * of views
     */
    private void setViewCounter() {
        for (Map.Entry<String, Integer> entry : history.entrySet()) {
            Counter.increaseViewCounter(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Finds a user with a given username in the database
     * @param username of the searched user
     * @return the user with the given username
     */
    public static User getUser(final String username) {
        User returnedUser = new User();

        for (User user : Database.getDatabase().getUsers()) {
            if (user.getUsername().equals(username)) {
                returnedUser = user;
            }
        }

        return returnedUser;
    }

    /**
     * Sets the total number of ratings given by the user
     */
    public void setNumberOfRatings() {
        numberOfRatings = ratedVideos.size();
    }

    public String getUsername() {
        return username;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public Map<String, Integer> getHistory() {
        return history;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public ArrayList<String> getFavouriteVideos() {
        return favoriteVideos;
    }

    public ArrayList<String> getRatedVideos() {
        return ratedVideos;
    }
}
