package main;

import common.Constants;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public final class Test {

    private Test() {
        // constructor for checkstyle
    }

    /**
     * @param args input files
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File inputDirectory = new File(Constants.INPUT_PATH);
        File[] inputFiles = inputDirectory.listFiles();

        if (inputFiles != null) {
            Arrays.sort(inputFiles);

//            Scanner scanner = new Scanner(System.in);
//            String fileName = scanner.next();
            String fileName = "test4.json";
            for (File file : inputFiles) {
                if (file.getName().equalsIgnoreCase(fileName)) {
                    Main.execute(file.getAbsolutePath(), Constants.OUT_FILE);
                    break;
                }
            }
        }
    }
}
