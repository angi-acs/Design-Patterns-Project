package santa;

import child.Child;
import observers.Observable;
import visitors.Visitable;
import visitors.Visitor;

import java.util.ArrayList;

public class Santa extends Observable implements Visitable {
    private double budget;
    private final ArrayList<Child> children;
    private final ArrayList<Gift> gifts;

    public Santa(final double santaBudget, final ArrayList<Child> children,
                 final ArrayList<Gift> gifts) {
        this.budget = santaBudget;
        this.children = children;
        this.gifts = gifts;
    }

    /**
     * Calls visitor's method
     */
    @Override
    public void accept(final Visitor visitor) {
        visitor.visit(this);
    }

    /**
     * Method that calls the observers
     */
    public void update() {
        notifyObservers(children);
    }

    /**
     * Method that returns an instance of a child based on the id field
     * or null if the child does not belong to the list anymore
     */
    public Child getChildById(final int id) {
        for (Child child : children) {
            if (child.getId() == id) {
                return child;
            }
        }
        return null;
    }

    /**
     * checkstyle
     */
    public double getBudget() {
        return budget;
    }

    /**
     * checkstyle
     */
    public void setBudget(final double budget) {
        this.budget = budget;
    }

    /**
     * checkstyle
     */
    public ArrayList<Child> getChildren() {
        return children;
    }

    /**
     * checkstyle
     */
    public ArrayList<Gift> getGifts() {
        return gifts;
    }
}
