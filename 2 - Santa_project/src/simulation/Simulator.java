package simulation;

import entities.Child;
import enums.ChildType;
import databases.Database;
import entities.Gift;
import strategies.AverageScoreStrategy;
import strategies.AverageScoreStrategyFactory;

import java.util.ArrayList;
import java.util.Comparator;

public final class Simulator {
    private Simulator() {
        // constructor for checkstyle
    }

    /**
     * Performs the simulation of a round.
     */
    public static void simulateRound() {
        excludeYoungAdults();
        clearReceivedGifts();
        setAverageScore();
        setBudget();
        assignGifts();
    }

    private static void excludeYoungAdults() {
        Database.getDatabase().getChildren()
                    .removeIf(child -> child.getChildType().equals(ChildType.YOUNG_ADULT));
    }

    private static void clearReceivedGifts() {
        Database.getDatabase().getChildren()
                    .forEach(child -> child.setReceivedGifts(new ArrayList<>()));
    }

    private static void setAverageScore() {
        AverageScoreStrategy<ArrayList<Double>> strategy;

        for (Child child : Database.getDatabase().getChildren()) {
            strategy = AverageScoreStrategyFactory.createStrategy(child.getChildType());
            child.setAverageScore(strategy.getAverageScore(child.getNiceScoreHistory()));
        }
    }

    private static void setBudget() {
        double budgetUnit = 0d;

        for (Child child : Database.getDatabase().getChildren()) {
            budgetUnit += child.getAverageScore();
        }
        budgetUnit = Database.getDatabase().getSantaBudget() / budgetUnit;

        for (Child child : Database.getDatabase().getChildren()) {
            child.setAssignedBudget(child.getAverageScore() * budgetUnit);
        }
    }

    private static void assignGifts() {
        double budget;
        Gift gift;

        for (Child child : Database.getDatabase().getChildren()) {
            budget = child.getAssignedBudget();

            for (String category : child.getGiftsPreferences()) {
                gift = Database.getDatabase().getGifts().stream()
                        .filter(g -> g.getCategory().equals(category))
                        .min(Comparator.comparing(Gift::getPrice))
                        .orElse(null);

                if (gift == null || gift.getPrice() > budget) {
                    continue;
                }

                child.getReceivedGifts().add(gift);
                budget -= gift.getPrice();
            }
        }
    }
}
