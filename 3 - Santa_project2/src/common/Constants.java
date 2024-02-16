package common;

public final class Constants {
    // checker constants
    public static final String OUTPUT_PATH = "output/out_";
    public static final String REF_PATH = "ref/ref_test";
    public static final String FILE_EXTENSION = ".json";
    public static final Integer SMALL_TEST_POINTS = 2;
    public static final Integer BIG_TEST_POINTS = 3;
    public static final Integer TESTS_NUMBER = 30;
    public static final Integer MAXIMUM_ERROR_CHECKSTYLE = 30;
    public static final Integer CHECKSTYLE_POINTS = 10;
    public static final Integer TESTS_NUMBER_SMALL = 15;

    // input/output constants
    public static final String INPUT_PATH = "tests/";
    public static final String OUTPUT_DIR = "output";
    public static final String NUMBER_OF_YEARS = "numberOfYears";
    public static final String SANTA_BUDGET = "santaBudget";
    public static final String INITIAL_DATA = "initialData";
    public static final String CHILDREN = "children";
    public static final String ANNUAL_CHANGES = "annualChanges";
    public static final String ID = "id";
    public static final String LAST_NAME = "lastName";
    public static final String FIRST_NAME = "firstName";
    public static final String AGE = "age";
    public static final String CITY = "city";
    public static final String NICE_SCORE = "niceScore";
    public static final String GIFTS_PREF = "giftsPreferences";
    public static final String NICE_SCORE_BONUS = "niceScoreBonus";
    public static final String ELF = "elf";
    public static final String SANTA_GIFTS_LIST = "santaGiftsList";
    public static final String PRODUCT_NAME = "productName";
    public static final String PRICE = "price";
    public static final String CATEGORY = "category";
    public static final String QUANTITY = "quantity";
    public static final String NEW_BUDGET = "newSantaBudget";
    public static final String NEW_GIFTS = "newGifts";
    public static final String NEW_CHILDREN = "newChildren";
    public static final String CHILDREN_UPDATES = "childrenUpdates";
    public static final String STRATEGY = "strategy";
    public static final String ANNUAL_CHILDREN = "annualChildren";
    public static final String AVERAGE_SCORE = "averageScore";
    public static final String NICE_SCORE_HISTORY = "niceScoreHistory";
    public static final String ASSIGNED_BUDGET = "assignedBudget";
    public static final String RECEIVED_GIFTS = "receivedGifts";

    // used instead of "magic numbers"
    public static final int BABY_AGE_MAX = 5;
    public static final int KID_AGE_MAX = 12;
    public static final int TEEN_AGE_MAX = 19;
    public static final double MAX_AVG_SCORE = 10d;

    // used for Test Class
    public static final String OUT_FILE = "out.txt";

    private Constants() {
        //constructor for checkstyle
    }

}
