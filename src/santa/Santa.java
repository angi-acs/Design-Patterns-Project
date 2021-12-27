package santa;

import child.Child;
import lombok.Getter;
import lombok.Setter;
import observers.Observable;
import visitors.Visitable;
import visitors.Visitor;

import java.util.ArrayList;

@Getter @Setter
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
     *
     * @param visitor a
     */
    @Override
    public void accept(final Visitor visitor) {
        visitor.visit(this);
    }

    /**
     * a
     */
    public void update() {
        notifyObservers(children);
    }

    /**
     * a
     * @param id b
     * @return c
     */
    public Child getChildById(final int id) {
        for (Child child : children) {
            if (child.getId() == id) {
                return child;
            }
        }
        return null;
    }

}
