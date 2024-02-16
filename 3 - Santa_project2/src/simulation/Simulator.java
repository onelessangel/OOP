package simulation;

import common.Constants;
import entities.Child;
import enums.ChildType;
import databases.Database;
import enums.ElvesType;
import strategies.averagescore.AverageScoreStrategy;
import strategies.averagescore.AverageScoreStrategyFactory;
import strategies.elves.ElfStrategyFactory;
import strategies.years.YearStrategyFactory;

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
        applyBudgetElfModifications();
        assignGifts();
        applyExtraGiftElfModifications();
        sortDatabase();
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
        Double averageScore;

        for (Child child : Database.getDatabase().getChildren()) {
            strategy = AverageScoreStrategyFactory.createStrategy(child.getChildType());
            averageScore = strategy.getAverageScore(child.getNiceScoreHistory());
            averageScore += averageScore * child.getNiceScoreBonus() / 100;
            averageScore = Math.min(averageScore, Constants.MAX_AVG_SCORE);
            child.setAverageScore(averageScore);
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

    private static void applyBudgetElfModifications() {
        for (Child child : Database.getDatabase().getChildren()) {
            ElvesType elfType = child.getElf();

            if (elfType.equals(ElvesType.WHITE) || elfType.equals(ElvesType.YELLOW)) {
                continue;
            }

            ElfStrategyFactory.createStrategy(elfType).makeChanges(child);
        }
    }

    private static void assignGifts() {
        YearStrategyFactory.createStrategy(Database.getDatabase().getStrategy()).assignGifts();
    }

    private static void applyExtraGiftElfModifications() {
        for (Child child : Database.getDatabase().getChildren()) {
            if (!child.getReceivedGifts().isEmpty()) {
                continue;
            }

            ElvesType elfType = child.getElf();

            if (elfType.equals(ElvesType.WHITE) || elfType.equals(ElvesType.BLACK)
                        || elfType.equals(ElvesType.PINK)) {
                continue;
            }

            ElfStrategyFactory.createStrategy(elfType).makeChanges(child);
        }
    }

    private static void sortDatabase() {
        Database.getDatabase().getChildren().sort(Comparator.comparing(Child::getId));
    }
}
