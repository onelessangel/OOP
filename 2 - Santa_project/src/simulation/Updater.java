package simulation;

import entities.Child;
import entities.ChildInputData;
import entities.ChildUpdate;
import entities.AnnualChange;
import databases.Database;
import entities.Gift;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;

public final class Updater {
    private Updater() {
        // constructor for checkstyle
    }

    /**
     * Updates the database.
     * @param currentChange that affects the database
     */
    public static void updateDatabase(final AnnualChange currentChange) {
        updateBudget(currentChange.getNewSantaBudget());
        updateGifts(currentChange.getNewGifts());
        updateChildrenList(currentChange.getNewChildren());
        updateChildren(currentChange.getChildrenUpdates());
    }

    private static void updateBudget(final double newBudget) {
        Database.getDatabase().setSantaBudget(newBudget);
    }

    private static void updateGifts(final ArrayList<Gift> newGifts) {
        newGifts.forEach(gift -> Database.getDatabase().getGifts().add(gift));
    }

    private static void updateChildrenList(final ArrayList<ChildInputData> newChildren) {
        Database.getDatabase().getChildren().forEach(entry -> {
            entry.increaseAge(); entry.setChildType(); });
        newChildren.forEach(child -> Database.getDatabase().getChildren().add(new Child(child)));
        Database.getDatabase().getChildren().sort(Comparator.comparing(Child::getId));
    }

    private static void updateChildren(final ArrayList<ChildUpdate> childrenUpdates) {
        for (ChildUpdate update : childrenUpdates) {
            Child child = findChild(update.getId());

            if (child == null) {
                continue;
            }

            updateNiceScore(child, update.getNiceScore());
            updateGiftsPreferences(child, update.getGiftsPreferences());
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
                                                   final ArrayList<String> newGiftsPreferences) {
        Set<String> noDuplicatesList = new LinkedHashSet<>(newGiftsPreferences);
        newGiftsPreferences.clear();
        newGiftsPreferences.addAll(noDuplicatesList);

        newGiftsPreferences.forEach(giftType -> child.getGiftsPreferences().remove(giftType));

        newGiftsPreferences.addAll(child.getGiftsPreferences());
        child.setGiftsPreferences(newGiftsPreferences);
    }
}
