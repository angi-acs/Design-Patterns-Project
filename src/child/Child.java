package child;

import lombok.Getter;
import lombok.Setter;
import santa.Gift;
import visitors.ChildVisitor;

import java.util.ArrayList;

@Getter @Setter
public class Child {
    private final int id;
    private final String lastName;
    private final String firstName;
    private final String city;
    private int age;
    private ArrayList<String> giftsPreference;
    private double averageScore;
    private ArrayList<Double> niceScoreHistory = new ArrayList<>();
    private double assignedBudget;
    private ArrayList<Gift> receivedGifts;

    public Child(final int id, final String lastName, final String firstName, final String city,
                 final int age, final ArrayList<String> giftsPreference, final double niceScore) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.city = city;
        this.age = age;
        this.giftsPreference = giftsPreference;
        this.niceScoreHistory.add(niceScore);
    }

    /**
     * a
     * @param v b
     */
    public void accept(final ChildVisitor v) {
        v.visit(this);
    }
}
