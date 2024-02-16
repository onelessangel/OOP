package main;

import checker.Checker;
import common.Constants;
import databases.Database;
import fileio.InputLoader;
import fileio.OutputLoader;
import simulation.Manager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException {
        File inputDirectory = new File(Constants.INPUT_PATH);
        Path outputPath = Paths.get(Constants.OUTPUT_DIR);

        if (!Files.exists(outputPath)) {
            Files.createDirectories(outputPath);
        }

        File outputDirectory = new File(Constants.OUTPUT_DIR);
        clearFolder(outputDirectory);

        for (File file : Objects.requireNonNull(inputDirectory.listFiles())) {
            String testNumber = file.getName().replaceAll("[^0-9]", "");
            String outPath = Constants.OUTPUT_PATH + testNumber + Constants.FILE_EXTENSION;
            execute(file.getAbsolutePath(), outPath);
        }

        Checker.calculateScore();
    }

    /**
     * Empties given directory.
     * @param directory to be emptied
     */
    public static void clearFolder(final File directory) {
        if (directory.listFiles() == null) {
            return;
        }

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (!file.delete()) {
                System.out.println("deletion failed");
            }
        }
    }

    /**
     * This method reads the input, executes the program and writes the output
     * @param inputPath of input file
     * @param outputPath of output file
     * @throws IOException in case of exceptions for reading/writing
     */
    public static void execute(final String inputPath, final String outputPath) throws IOException {
        // reads input and stores it in the Input database
        InputLoader.reset();
        InputLoader.create(inputPath);
        InputLoader.getInstance().readData();

        // creates main database
        Database.reset();
        Database.create();

        OutputLoader.reset();

        // executes the program
        Manager.execute();

        OutputLoader.writeFile(outputPath);
    }
}
