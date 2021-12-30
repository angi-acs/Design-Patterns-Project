package main;

import fileio.AnnualChange;
import santa.Santa;
import visitors.AnnualUpdate;
import visitors.AssignedBudget;
import visitors.AverageScore;
import visitors.CheckAge;
import visitors.IncreaseAge;
import visitors.ReceivedGifts;
import visitors.Visitor;

import java.util.ArrayList;

public class Simulation {
    private final Santa santa;

    public Simulation(final Santa santa) {
        this.santa = santa;
    }

    /**
     * Initializes visitors needed for round zero
     * Calls them in a specific order
     */
    public void roundZero() {
        ArrayList<Visitor> visitors = new ArrayList<>();

        CheckAge checkAge = new CheckAge();
        visitors.add(checkAge);
        AverageScore averageScore = new AverageScore();
        visitors.add(averageScore);
        AssignedBudget assignedBudget = new AssignedBudget();
        visitors.add(assignedBudget);
        ReceivedGifts receivedGifts = new ReceivedGifts();
        visitors.add(receivedGifts);

        visitors.forEach(santa::accept);

        santa.update();
    }

    /**
     * Initializes visitors needed for rounds
     * that contain annual changes
     * Calls them in a specific order
     */
    public void round(final AnnualChange annualChange) {
        ArrayList<Visitor> visitors = new ArrayList<>();

        IncreaseAge increaseAge = new IncreaseAge();
        visitors.add(increaseAge);
        AnnualUpdate annualUpdate = new AnnualUpdate(annualChange);
        visitors.add(annualUpdate);
        CheckAge checkAge = new CheckAge();
        visitors.add(checkAge);
        AverageScore averageScore = new AverageScore();
        visitors.add(averageScore);
        AssignedBudget assignedBudget = new AssignedBudget();
        visitors.add(assignedBudget);
        ReceivedGifts receivedGifts = new ReceivedGifts();
        visitors.add(receivedGifts);

        visitors.forEach(santa::accept);

        santa.update();
    }
}
