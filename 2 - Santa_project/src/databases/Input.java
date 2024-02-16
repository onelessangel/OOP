package databases;

import entities.AnnualChange;
import entities.InitialData;

import java.util.ArrayList;

public final class Input {
    private int numberOfYears;
    private double santaBudget;
    private InitialData initialData;
    private ArrayList<AnnualChange> annualChanges;

    // Singleton - Lazy Implementation
    private static Input instance = null;

    private Input() {

    }

    /**
     * Used to access the input database. Creates it when called for the first time.
     * @return the input database instance
     */
    public static Input getInput() {
        if (instance == null) {
            instance = new Input();
        }
        return instance;
    }

    /**
     * Empties the database
     */
    public static void reset() {
        getInput().numberOfYears = 0;
        getInput().santaBudget = 0d;
        getInput().initialData = new InitialData();
        getInput().annualChanges = new ArrayList<>();
    }

    /**
     * Stores in the database the given data
     */
    public static void create(final int numberOfYears, final double santaBudget,
                                  final InitialData initialData,
                                  final ArrayList<AnnualChange> annualChanges) {
        getInput().numberOfYears = numberOfYears;
        getInput().santaBudget = santaBudget;
        getInput().initialData = initialData;
        getInput().annualChanges = annualChanges;
    }

    public int getNumberOfYears() {
        return numberOfYears;
    }

    public double getSantaBudget() {
        return santaBudget;
    }

    public InitialData getInitialData() {
        return initialData;
    }

    public ArrayList<AnnualChange> getAnnualChanges() {
        return annualChanges;
    }

    @Override
    public String toString() {
        return "{numberOfYears:" + numberOfYears
                + "\nsantaBudget:" + santaBudget
                + "\ninitialData: " + initialData
                + "\nannualChanges: " + annualChanges
                + '}' + '\n';
    }
}
