package main;

import child.Child;
import common.Constants;
import fileio.Writer;
import santa.Santa;
import visitors.AssignedBudget;
import visitors.ReceivedGifts;

public class Simulation {
    private final Writer writer;
    private final Santa santa;

    public Simulation(final Santa santa, final Writer writer) {
        this.writer = writer;
        this.santa = santa;
    }

    /**
     * a
     */
    public void roundZero() {
        checkAge();

        double averageScoreSum = 0;
        for (Child child : santa.getChildren()) {
            if (child.getAge() < Constants.BABY) {
                child.setAverageScore(Constants.BABY_SCORE);
            } else {
                child.setAverageScore(child.getNiceScoreHistory().get(0));
            }
            averageScoreSum += child.getAverageScore();
        }

        double budgetUnit = santa.getBudget() / averageScoreSum;

        AssignedBudget assignedBudget = new AssignedBudget(budgetUnit);
        ReceivedGifts receivedGifts = new ReceivedGifts(santa);

        for (Child child : santa.getChildren()) {
            child.accept(assignedBudget);
            child.accept(receivedGifts);
        }

        writer.writeToFile(santa.getChildren());
    }

    /**
     * a
     */
    public void checkAge() {
        santa.getChildren().removeIf(child -> child.getAge() > Constants.TEEN);
    }
}
