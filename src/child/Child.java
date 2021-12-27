package child;

import lombok.Getter;
import lombok.Setter;
import santa.Gift;
import visitors.Visitable;
import visitors.Visitor;

import java.util.ArrayList;

@Getter @Setter
public class Child implements Visitable {
    private final int id;
    private final String lastName;
    private final String firstName;
    private final String city;
    private int age;
    private ArrayList<String> giftsPreferences;
    private double averageScore;
    private ArrayList<Double> niceScoreHistory;
    private double assignedBudget;
    private ArrayList<Gift> receivedGifts;

    public Child(final int id, final String lastName, final String firstName, final String city,
                 final int age, final ArrayList<String> giftsPreferences, final double niceScore) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.city = city;
        this.age = age;
        this.giftsPreferences = giftsPreferences;
        this.niceScoreHistory = new ArrayList<>();
        this.niceScoreHistory.add(niceScore);
    }

    /**
     *
     * @param visitor a
     */
    @Override
    public void accept(final Visitor visitor) {
        visitor.visit(this);
    }

    // copy constructor
    public Child(final Child child) {
        this(child.getId(), child.getLastName(), child.getFirstName(),
                child.getCity(), child.getAge(), child.getGiftsPreferences(),
                child.getAverageScore());
        this.averageScore = child.getAverageScore();
        this.niceScoreHistory = new ArrayList<>();
        this.niceScoreHistory.addAll(child.getNiceScoreHistory());
        this.assignedBudget = child.getAssignedBudget();
        this.receivedGifts = child.getReceivedGifts();
        this.giftsPreferences = new ArrayList<>();
        this.giftsPreferences.addAll(child.getGiftsPreferences());
    }

}
