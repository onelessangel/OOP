package strategies.years;

import databases.Database;
import entities.Child;

import java.util.Comparator;

public final class NiceScoreYearStrategy implements YearStrategy {
    @Override
    public void assignGifts() {
        Database.getDatabase().getChildren().sort(Comparator
                    .comparing(Child::getAverageScore).reversed()
                    .thenComparing(Child::getId));
        for (Child child : Database.getDatabase().getChildren()) {
            YearStrategy.assignGiftsToChild(child);
        }
    }
}
