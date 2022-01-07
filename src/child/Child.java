package child;

import com.fasterxml.jackson.annotation.JsonIgnore;
import santa.Gift;
import visitors.Visitable;
import visitors.Visitor;

import java.util.ArrayList;

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
    @JsonIgnore
    private final Integer niceScoreBonus;
    @JsonIgnore
    private String elf;

    public Child(final int id, final String lastName, final String firstName, final String city,
                 final int age, final ArrayList<String> giftsPreferences, final double niceScore,
                 final Integer niceScoreBonus, final String elf) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.city = city;
        this.age = age;
        this.giftsPreferences = giftsPreferences;
        this.niceScoreHistory = new ArrayList<>();
        this.niceScoreHistory.add(niceScore);
        this.niceScoreBonus = niceScoreBonus;
        this.elf = elf;
    }

    /**
     * Calls visitor's method
     */
    @Override
    public void accept(final Visitor visitor) {
        visitor.visit(this);
    }

    // copy constructor
    public Child(final Child child) {
        this(child.getId(), child.getLastName(), child.getFirstName(),
                child.getCity(), child.getAge(), child.getGiftsPreferences(),
                child.getAverageScore(), child.getNiceScoreBonus(), child.getElf());
        this.averageScore = child.getAverageScore();
        this.niceScoreHistory = new ArrayList<>();
        this.niceScoreHistory.addAll(child.getNiceScoreHistory());
        this.assignedBudget = child.getAssignedBudget();
        this.receivedGifts = child.getReceivedGifts();
        this.giftsPreferences = new ArrayList<>();
        this.giftsPreferences.addAll(child.getGiftsPreferences());
    }

    /**
     * checkstyle
     */
    public int getId() {
        return id;
    }

    /**
     * checkstyle
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * checkstyle
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * checkstyle
     */
    public String getCity() {
        return city;
    }

    /**
     * checkstyle
     */
    public int getAge() {
        return age;
    }

    /**
     * checkstyle
     */
    public void setAge(final int age) {
        this.age = age;
    }

    /**
     * checkstyle
     */
    public ArrayList<String> getGiftsPreferences() {
        return giftsPreferences;
    }

    /**
     * checkstyle
     */
    public void setGiftsPreferences(final ArrayList<String> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    /**
     * checkstyle
     */
    public double getAverageScore() {
        return averageScore;
    }

    /**
     * checkstyle
     */
    public void setAverageScore(final double averageScore) {
        this.averageScore = averageScore;
    }

    /**
     * checkstyle
     */
    public ArrayList<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    /**
     * checkstyle
     */
    public void setNiceScoreHistory(final ArrayList<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    /**
     * checkstyle
     */
    public double getAssignedBudget() {
        return assignedBudget;
    }

    /**
     * checkstyle
     */
    public void setAssignedBudget(final double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    /**
     * checkstyle
     */
    public ArrayList<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    /**
     * checkstyle
     */
    public void setReceivedGifts(final ArrayList<Gift> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }

    /**
     * checkstyle
     */
    public Integer getNiceScoreBonus() {
        return niceScoreBonus;
    }

    /**
     * checkstyle
     */
    public String getElf() {
        return elf;
    }

    /**
     * checkstyle
     */
    public void setElf(final String elf) {
        this.elf = elf;
    }
}
