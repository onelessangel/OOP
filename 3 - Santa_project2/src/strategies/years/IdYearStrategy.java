package strategies.years;

import databases.Database;
import entities.Child;

import java.util.Comparator;

public final class IdYearStrategy implements YearStrategy {
    @Override
    public void assignGifts() {
        Database.getDatabase().getChildren().sort(Comparator.comparing(Child::getId));
        for (Child child : Database.getDatabase().getChildren()) {
            YearStrategy.assignGiftsToChild(child);
        }
    }
}
