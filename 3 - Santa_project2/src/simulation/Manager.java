package simulation;

import entities.AnnualChange;
import databases.Database;
import databases.Input;
import fileio.OutputLoader;

public final class Manager {
    private Manager() {
        // constructor for checkstyle
    }

    /**
     * Executes the whole program by simulating each round, writing the result after each simulation
     * and updating the database when necessary.
     */
    public static void execute() {
        int yearsCounter = 0;

        Updater updater = new Updater();
        updater.addObserver(Database.getDatabase());

        standardExecution();

        for (AnnualChange change : Input.getInput().getAnnualChanges()) {
            updater.notify(change);
            standardExecution();

            yearsCounter++;
            if (yearsCounter == Database.getDatabase().getNumberOfYears()) {
                break;
            }
        }
    }

    private static void standardExecution() {
        Simulator.simulateRound();
        OutputLoader.writeCurrentData();
    }
}
