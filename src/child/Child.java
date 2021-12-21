package child;

import enums.Category;
import lombok.Getter;
import santa.Gift;

import java.util.ArrayList;

@Getter
public class Child {
    private final int id;
    private final String lastName;
    private final String firstName;
    private final String city;
    private int age;
    private ArrayList<Category> giftsPreference;
    private double averageScore;
    private ArrayList<Double> niceScoreHistory = new ArrayList<>();
    private double assignedBudget;
    private ArrayList<Gift> receivedGifts;

    public Child(final int id, final String lastName, final String firstName, final String city,
                 final int age, final ArrayList<Category> giftsPreference, final double niceScore) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.city = city;
        this.age = age;
        this.giftsPreference = giftsPreference;
        this.niceScoreHistory.add(niceScore);
    }
}
