package fileio;

import entities.Child;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import common.Constants;
import databases.Database;
import entities.Gift;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;

public final class OutputLoader {
    private JSONObject mainObject;
    private JSONArray annualChildrenList;

    // Singleton - Lazy Implementation
    private static OutputLoader instance = null;

    private OutputLoader() {

    }

    /**
     * Used to access the OutputLoader. Creates it when called for the first time.
     * @return the OutputLoader instance
     */
    public static OutputLoader getInstance() {
        if (instance == null) {
            instance = new OutputLoader();
        }
        return instance;
    }

    /**
     * Empties the instance.
     */
    public static void reset() {
        getInstance().mainObject = new JSONObject();
        getInstance().annualChildrenList = new JSONArray();
    }

    /**
     * Adds in the main JSON array another element containing the current data about each child
     * found in the database.
     */
    public static void writeCurrentData() {
        JSONObject children = new JSONObject();
        JSONArray childrenList = new JSONArray();

        for (Child child : Database.getDatabase().getChildren()) {
            JSONObject childObject = new JSONObject();

            childObject.put(Constants.ID, child.getId());
            childObject.put(Constants.LAST_NAME, child.getLastName());
            childObject.put(Constants.FIRST_NAME, child.getFirstName());
            childObject.put(Constants.CITY, child.getCity());
            childObject.put(Constants.AGE, child.getAge());

            JSONArray giftsPreferences = new JSONArray();
            giftsPreferences.addAll(child.getGiftsPreferences());
            childObject.put(Constants.GIFTS_PREF, giftsPreferences);

            childObject.put(Constants.AVERAGE_SCORE, child.getAverageScore());

            JSONArray niceScoreHistory = new JSONArray();
            niceScoreHistory.addAll(child.getNiceScoreHistory());
            childObject.put(Constants.NICE_SCORE_HISTORY, niceScoreHistory);

            childObject.put(Constants.ASSIGNED_BUDGET, child.getAssignedBudget());

            JSONArray receivedGifts = new JSONArray();
            for (Gift gift : child.getReceivedGifts()) {
                JSONObject giftObject = new JSONObject();
                giftObject.put(Constants.PRODUCT_NAME, gift.getProductName());
                giftObject.put(Constants.PRICE, gift.getPrice());
                giftObject.put(Constants.CATEGORY, gift.getCategory());

                receivedGifts.add(giftObject);
            }
            childObject.put(Constants.RECEIVED_GIFTS, receivedGifts);

            childrenList.add(childObject);
        }

        children.put(Constants.CHILDREN, childrenList);

        getInstance().annualChildrenList.add(children);
    }

    /**
     * Creates the output file and writes the main JSON object in it.
     * @param outputPath of the output file
     * @throws IOException in case of exceptions for writing
     */
    public static void writeFile(final String outputPath) throws IOException {
        getInstance().mainObject.put(Constants.ANNUAL_CHILDREN, getInstance().annualChildrenList);

        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        writer.writeValue(new File(outputPath), getInstance().mainObject);
    }
}
