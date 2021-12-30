package main;

import checker.Checker;
import common.Constants;
import fileio.AnnualChange;
import fileio.InputReader;
import fileio.Writer;
import observers.Output;
import org.json.simple.parser.ParseException;
import santa.Santa;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        //constructor for checkstyle
    }

    /**
     * This method is used to call the checker which calculates the score
     * and create the output files
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException, ParseException {

        File directory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.OUTPUT);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File outputDirectory = new File(Constants.OUTPUT);
        for (File file : Objects.requireNonNull(outputDirectory.listFiles())) {
            if (!file.delete()) {
                System.out.println("File was not deleted");
            }
        }

        for (File file : Objects.requireNonNull(directory.listFiles())) {

            String s = file.getName();
            int i = Integer.parseInt(s.replaceAll("[\\D]", ""));
            String filepath = Constants.OUTPUT_PATH + i + Constants.FILE_EXTENSION;
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                simulate(file.getAbsolutePath(), filepath);
            }
        }

        Checker.calculateScore();
    }

    /**
     * Calls necessary methods for every test
     * @param filePath1 test file
     * @param filePath2 output file
     */
    public static void simulate(final String filePath1,
                                final String filePath2) throws IOException, ParseException {

        InputReader inputReader = new InputReader(filePath1);
        Writer writer = new Writer(filePath2);

        Santa santa = inputReader.initialData();

        /*
         * Initialize observer
         */
        Output output = new Output(writer);
        santa.addObserver(output);

        /*
         * Initialize simulation
         */
        Simulation simulation = new Simulation(santa);
        simulation.roundZero();

        ArrayList<AnnualChange> annualChanges = inputReader.annualChanges();
        for (int i = 0; i < inputReader.getNumberOfYears(); i++) {
            simulation.round(annualChanges.get(i));
        }
        writer.closeFile();
    }
}
