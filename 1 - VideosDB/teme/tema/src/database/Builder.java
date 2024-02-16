package database;

import action.Action;
import action.Command;
import action.Query;
import action.Recommendation;
import common.Constants;
import org.json.JSONObject;
import org.json.simple.JSONArray;

/**
 * Contains static methods used for populating the result array and executing actions.
 */

public final class Builder {

    private Builder() {

    }

    /**
     * Adds a new entry in the arrayResult for each action
     * @param arrayResult JASON array containing the results of each action
     */
    public static void makeArrayResult(final JSONArray arrayResult) {
        for (Action action : Database.getDatabase().getActions()) {
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("id", action.getActionId());
            jsonObject.put("message", executeAction(action));

            arrayResult.add(jsonObject);
        }
    }

    /**
     * Calls the function corresponding to the action type
     * @param action to be executed
     * @return the message with the result of the action
     */
    private static String executeAction(final Action action) {
        String message = null;
        String actionType = action.getActionType();

        if (actionType.equals(Constants.COMMAND)) {
            message = Command.execute(action);
        } else if (actionType.equals(Constants.QUERY)) {
            message = Query.execute(action);
        } else if (actionType.equals(Constants.RECOMMENDATION)) {
            message = Recommendation.execute(action);
        }

        return message;
    }
}
