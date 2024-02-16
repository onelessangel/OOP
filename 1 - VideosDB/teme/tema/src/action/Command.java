package action;

import common.Constants;
import entertainment.Video;
import user.User;
import utils.Counter;

/**
 * Contains static methods used to execute each type of command.
 */

public final class Command {

    private Command() {

    }

    /**
     * Calls the function corresponding to the command type
     * @param action to be executed
     * @return the message with the result of the command
     */
    public static String execute(final Action action) {
        String message = null;
        String type = action.getType();
        User user = User.getUser(action.getUsername());
        String title = action.getTitle();
        double grade = action.getGrade();
        int season = action.getSeasonNumber();

        if (type.equals(Constants.FAVORITE)) {
            message = favorite(user, title);
        } else if (type.equals(Constants.VIEW)) {
            message = view(user, title);
        } else if (type.equals(Constants.RATING)) {
            message = rating(user, title, grade, season);
        }

        return message;
    }

    /**
     * Adds a video in the user's favorite list
     * @param user who gives the command
     * @param title of the video
     * @return the message with the result of the command
     */
    private static String favorite(final User user, final String title) {
        String message;

        if (!user.getHistory().containsKey(title)) {
            message = "error -> " + title + " is not seen";
        } else if (user.getFavouriteVideos().contains(title)) {
            message = "error -> " + title + " is already in favourite list";
        } else {
            Counter.increaseFavouriteCounter(title);
            user.getFavouriteVideos().add(title);
            message = "success -> " + title + " was added as favourite";
        }

        return message;
    }

    /**
     * Marks a video as seen by the given user
     * @param user who gives the command
     * @param title of the video
     * @return the message with the result of the command
     */
    private static String view(final User user, final String title) {
        String message;

        if (!user.getHistory().containsKey(title)) {
            user.getHistory().put(title, 1);
        } else {
            user.getHistory().put(title, user.getHistory().get(title) + 1);
        }

        Counter.increaseViewCounter(title, 1);
        message = "success -> " + title + " was viewed with total views of "
                + user.getHistory().get(title);

        return message;
    }

    /**
     * Gives a rating to a video
     * @param user who gives the command
     * @param title of the video
     * @param grade of the video
     * @param season of the show the rating is given to. It is 0 if the video is a movie
     * @return the message with the result of the command
     */
    private static String rating(final User user, final String title, final double grade,
                                 final int season) {
        String message;
        String videoName = title + " " + season;

        if (!user.getHistory().containsKey(title)) {
            message = "error -> " + title + " is not seen";
        } else if (user.getRatedVideos().contains(videoName)) {
            message = "error -> " + title + " has been already rated";
        } else {
            Video.addRating(title, grade, season);
            user.getRatedVideos().add(videoName);
            message = "success -> " + title + " was rated with " + grade + " by "
                    + user.getUsername();
        }

        return message;
    }
}
