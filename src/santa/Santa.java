package santa;

import child.Child;
import fileio.AnnualChange;
import lombok.Getter;
import lombok.Setter;
import observers.Observable;

import java.util.ArrayList;

@Getter @Setter
public class Santa extends Observable {
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
     * a
     * @param annualChange b
     */
    public void update(final AnnualChange annualChange) {
        notifyObservers(annualChange.getNewChildren());
    }
}
