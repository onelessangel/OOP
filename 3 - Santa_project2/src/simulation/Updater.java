package simulation;

import entities.Child;
import entities.ChildInputData;
import entities.ChildUpdate;
import entities.AnnualChange;
import databases.Database;
import entities.Gift;
import enums.Category;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Observable;
import java.util.Set;

public final class Updater extends Observable {
    public Updater() {

    }

    /**
     * Notifies observers of given change.
     * @param change that affects the observers
     */
    public void notify(final AnnualChange change) {
        setChanged();
        notifyObservers(change);
    }

    /**
     * Updates Santa's budget.
     */
    public static void updateBudget(final double newBudget) {
        Database.getDatabase().setSantaBudget(newBudget);
    }

    /**
     * Updates the gift list.
     */
    public static void updateGifts(final ArrayList<Gift> newGifts) {
        newGifts.forEach(gift -> Database.getDatabase().getGifts().add(gift));
    }

    /**
     *
     * Adds new children.
     */
    public static void updateChildrenList(final ArrayList<ChildInputData> newChildren) {
        Database.getDatabase().getChildren().forEach(entry -> {
            entry.increaseAge(); entry.setChildType(); });
        newChildren.forEach(child -> Database.getDatabase().getChildren().add(new Child(child)));
        Database.getDatabase().getChildren().sort(Comparator.comparing(Child::getId));
    }

    /**
     *
     * Updates children.
     */
    public static void updateChildren(final ArrayList<ChildUpdate> childrenUpdates) {
        for (ChildUpdate update : childrenUpdates) {
            Child child = findChild(update.getId());

            if (child == null) {
                continue;
            }

            updateNiceScore(child, update.getNiceScore());
            updateGiftsPreferences(child, update.getGiftsPreferences());

            child.setElf(update.getElf());
        }
    }

    private static Child findChild(final int id) {
        for (Child child : Database.getDatabase().getChildren()) {
            if (child.getId() != id) {
                continue;
            }

            return child;
        }

        return null;
    }

    private static void updateNiceScore(final Child child, final Double newNiceScore) {
        if (newNiceScore == null) {
            return;
        }
        child.getNiceScoreHistory().add(newNiceScore);
    }

    private static void updateGiftsPreferences(final Child child,
                                               final ArrayList<Category> newGiftsPreferences) {
        Set<Category> noDuplicatesList = new LinkedHashSet<>(newGiftsPreferences);
        newGiftsPreferences.clear();
        newGiftsPreferences.addAll(noDuplicatesList);

        newGiftsPreferences.forEach(giftType -> child.getGiftsPreferences().remove(giftType));

        newGiftsPreferences.addAll(child.getGiftsPreferences());
        child.setGiftsPreferences(newGiftsPreferences);
    }

    /**
     *
     * Updates strategy type.
     */
    public static void updateStrategy(final AnnualChange currentChange) {
        Database.getDatabase().setStrategy(currentChange.getStrategy());
    }
}
