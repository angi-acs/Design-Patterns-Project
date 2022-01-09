package main;

import common.Constants;
import fileio.AnnualChange;
import santa.Santa;
import visitors.AnnualUpdate;
import visitors.AssignedBudget;
import visitors.AverageScore;
import visitors.BonusScore;
import visitors.CheckAge;
import visitors.ElvenBudget;
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
        BonusScore bonusScore = new BonusScore();
        visitors.add(bonusScore);
        AssignedBudget assignedBudget = new AssignedBudget();
        visitors.add(assignedBudget);
        ElvenBudget elvenBudget = new ElvenBudget();
        visitors.add(elvenBudget);
        ReceivedGifts receivedGifts = new ReceivedGifts(Constants.ID);
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
        BonusScore bonusScore = new BonusScore();
        visitors.add(bonusScore);
        AssignedBudget assignedBudget = new AssignedBudget();
        visitors.add(assignedBudget);
        ElvenBudget elvenBudget = new ElvenBudget();
        visitors.add(elvenBudget);
        ReceivedGifts receivedGifts = new ReceivedGifts(annualChange.getStrategy());
        visitors.add(receivedGifts);

        visitors.forEach(santa::accept);

        santa.update();
    }
}
