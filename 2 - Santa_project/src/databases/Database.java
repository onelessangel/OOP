package databases;

import entities.Child;
import entities.Gift;

import java.util.ArrayList;

public final class Database {
    private int numberOfYears;
    private Double santaBudget;
    private ArrayList<Child> children;
    private ArrayList<Gift> gifts;

    // Singleton - Lazy Implementation
    private static Database instance = null;

    private Database() {

    }

    /**
     * Used to access the database. Creates it when called for the first time.
     * @return the database instance
     */
    public static Database getDatabase() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    /**
     * Empties the database
     */
    public static void reset() {
        getDatabase().numberOfYears = 0;
        getDatabase().santaBudget = 0d;
        getDatabase().children = new ArrayList<>();
        getDatabase().gifts = new ArrayList<>();
    }

    /**
     * Stores current data from the input in the database
     */
    public static void create() {
        getDatabase().numberOfYears = Input.getInput().getNumberOfYears();
        getDatabase().santaBudget = Input.getInput().getSantaBudget();
        Input.getInput().getInitialData().getChildren()
                .forEach(child -> getDatabase().children.add(new Child(child)));
        Input.getInput().getInitialData().getGifts().forEach(gift -> getDatabase().gifts.add(gift));
    }

    public int getNumberOfYears() {
        return numberOfYears;
    }

    public Double getSantaBudget() {
        return santaBudget;
    }

    public ArrayList<Child> getChildren() {
        return children;
    }

    public ArrayList<Gift> getGifts() {
        return gifts;
    }

    public void setSantaBudget(final Double santaBudget) {
        this.santaBudget = santaBudget;
    }
}
