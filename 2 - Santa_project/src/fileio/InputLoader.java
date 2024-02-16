package fileio;

import entities.ChildInputData;
import entities.ChildUpdate;
import common.Constants;
import entities.AnnualChange;
import entities.InitialData;
import databases.Input;
import entities.Gift;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public final class InputLoader {
    private String inputPath;

    // Singleton - Lazy Implementation
    private static InputLoader instance = null;

    private InputLoader() {

    }

    /**
     * Used to access the InputLoader. Creates it when called for the first time.
     * @return the InputLoader instance
     */
    public static InputLoader getInstance() {
        if (instance == null) {
            instance = new InputLoader();
        }
        return instance;
    }

    /**
     * Empties the instance.
     */
    public static void reset() {
        getInstance().inputPath = null;
    }

    /**
     * Sets the path for the input file.
     * @param inputPath of the input file
     */
    public static void create(final String inputPath) {
        getInstance().inputPath = inputPath;
    }

    /**
     * Parses the data from the input file to the input database.
     */
    public void readData() {
        JSONParser jsonParser = new JSONParser();

        int numberOfYears = 0;
        double santaBudget = 0;
        InitialData initialData = new InitialData();
        ArrayList<AnnualChange> annualChanges = new ArrayList<>();

        try (FileReader reader = new FileReader(inputPath)) {
            // read JSON file
            JSONObject mainObject = (JSONObject) jsonParser.parse(reader);

            // parse the number of years and the initial budget
            numberOfYears = ((Long) mainObject.get(Constants.NUMBER_OF_YEARS)).intValue();
            santaBudget = ((Long) mainObject.get(Constants.SANTA_BUDGET)).doubleValue();

            JSONObject jsonInitialData = (JSONObject) mainObject.get(Constants.INITIAL_DATA);

            // parse initial children list
            JSONArray childrenList = (JSONArray) jsonInitialData.get(Constants.CHILDREN);
            if (childrenList != null) {
                for (Object childObject : childrenList) {
                    ChildInputData child = parseChildObject((JSONObject) childObject);
                    initialData.getChildren().add(child);
                }
            } else {
                System.out.println("THE CHILDREN'S LIST DOESN'T EXIST");
            }

            // parse initial gifts list
            JSONArray giftsList = (JSONArray) jsonInitialData.get(Constants.SANTA_GIFTS_LIST);
            if (giftsList != null) {
                for (Object giftObject : giftsList) {
                    Gift gift = parseGiftObject((JSONObject) giftObject);
                    initialData.getGifts().add(gift);
                }
            } else {
                System.out.println("THE GIFTS' LIST DOESN'T EXIST");
            }

            // parse annual changes
            JSONArray annualChangesList = (JSONArray) mainObject.get(Constants.ANNUAL_CHANGES);
            if (annualChangesList != null) {
                for (Object annualChangeObject : annualChangesList) {
                    AnnualChange change = parseAnnualChangeObject((JSONObject) annualChangeObject);
                    annualChanges.add(change);
                }
            } else {
                System.out.println("THE ANNUAL CHANGES' LIST DOESN'T EXIST");
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        Input.reset();
        Input.create(numberOfYears, santaBudget, initialData, annualChanges);
    }

    private ChildInputData parseChildObject(final JSONObject childObject) {
        int id           = ((Long)  childObject.get(Constants.ID)).intValue();
        String lastName  = (String) childObject.get(Constants.LAST_NAME);
        String firstName = (String) childObject.get(Constants.FIRST_NAME);
        int age          = ((Long)  childObject.get(Constants.AGE)).intValue();
        String city      = (String) childObject.get(Constants.CITY);
        double niceScore = ((Long)  childObject.get(Constants.NICE_SCORE)).doubleValue();
        ArrayList<String> giftsPreferences = new ArrayList<>();

        // parse giftsPreferences
        JSONArray jsonGiftsPreferences = (JSONArray) childObject.get(Constants.GIFTS_PREF);
        for (Object gift : jsonGiftsPreferences) {
            giftsPreferences.add((String) gift);
        }

        return new ChildInputData(id, lastName, firstName, age, city, niceScore, giftsPreferences);
    }

    private Gift parseGiftObject(final JSONObject giftObject) {
        String productName = (String) giftObject.get(Constants.PRODUCT_NAME);
        double price       = ((Long)  giftObject.get(Constants.PRICE)).doubleValue();
        String category    = (String) giftObject.get(Constants.CATEGORY);

        return new Gift(productName, price, category);
    }

    private AnnualChange parseAnnualChangeObject(final JSONObject changeObject) {
        double newSantaBudget = ((Long) changeObject.get(Constants.NEW_BUDGET)).doubleValue();
        ArrayList<Gift> newGifts               = new ArrayList<>();
        ArrayList<ChildInputData> newChildren  = new ArrayList<>();
        ArrayList<ChildUpdate> childrenUpdates = new ArrayList<>();

        // parse new gifts list
        JSONArray newGiftsList = (JSONArray) changeObject.get(Constants.NEW_GIFTS);
        if (newGiftsList != null) {
            for (Object newGiftObject : newGiftsList) {
                Gift newGift = parseGiftObject((JSONObject) newGiftObject);
                newGifts.add(newGift);
            }
        } else {
            System.out.println("THE NEW GIFTS' LIST DOESN'T EXIST");
        }

        // parse new children list
        JSONArray newChildrenList = (JSONArray) changeObject.get(Constants.NEW_CHILDREN);
        if (newChildrenList != null) {
            for (Object newChildObject : newChildrenList) {
                ChildInputData newChild = parseChildObject((JSONObject) newChildObject);
                newChildren.add(newChild);
            }
        } else {
            System.out.println("THE NEW CHILDREN'S LIST DOESN'T EXIST");
        }

        // parse children updates
        JSONArray childrenUpdatesList = (JSONArray) changeObject.get(Constants.CHILDREN_UPDATES);
        if (childrenUpdatesList != null) {
            for (Object childUpdateObject : childrenUpdatesList) {
                ChildUpdate childUpdate = parseChildUpdateObject((JSONObject) childUpdateObject);
                childrenUpdates.add(childUpdate);
            }
        } else {
            System.out.println("THE CHILDREN UPDATES' LIST DOESN'T EXIST");
        }

        return new AnnualChange(newSantaBudget, newGifts, newChildren, childrenUpdates);
    }

    private ChildUpdate parseChildUpdateObject(final JSONObject childUpdateObject) {
        int id               = ((Long) childUpdateObject.get(Constants.ID)).intValue();
        Long parsedNiceScore = (Long) childUpdateObject.get(Constants.NICE_SCORE);
        Double niceScore     = null;
        if (parsedNiceScore != null) {
            niceScore = Double.valueOf(parsedNiceScore);
        }

        ArrayList<String> giftsPreferences = new ArrayList<>();

        // parse giftsPreferences
        JSONArray jsonGiftsPreferences = (JSONArray) childUpdateObject.get(Constants.GIFTS_PREF);
        for (Object gift : jsonGiftsPreferences) {
            giftsPreferences.add((String) gift);
        }

        return new ChildUpdate(id, niceScore, giftsPreferences);
    }
}
