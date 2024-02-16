package databases;

import entities.AnnualChange;
import entities.Child;
import entities.Gift;
import enums.Strategy;
import simulation.Updater;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public final class Database implements Observer {
    private int numberOfYears;
    private Double santaBudget;
    private ArrayList<Child> children;
    private ArrayList<Gift> gifts;
    private Strategy strategy;

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
        getDatabase().strategy = Strategy.ID;
    }

    @Override
    public void update(final Observable o, final Object change) {
        Updater.updateBudget(((AnnualChange) change).getNewSantaBudget());
        Updater.updateGifts(((AnnualChange) change).getNewGifts());
        Updater.updateChildrenList(((AnnualChange) change).getNewChildren());
        Updater.updateChildren(((AnnualChange) change).getChildrenUpdates());
        Updater.updateStrategy(((AnnualChange) change));
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

    public Strategy getStrategy() {
        return strategy;
    }

    public void setSantaBudget(final Double santaBudget) {
        this.santaBudget = santaBudget;
    }

    public void setStrategy(final Strategy strategy) {
        this.strategy = strategy;
    }
}
