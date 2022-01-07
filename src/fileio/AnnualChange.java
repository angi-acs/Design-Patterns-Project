package fileio;

import child.Child;
import santa.Gift;

import java.util.ArrayList;

public class AnnualChange {
    private final double newSantaBudget;
    private final ArrayList<Gift> newGifts;
    private final ArrayList<Child> newChildren;
    private final ArrayList<ChildUpdate> childrenUpdates;

    public AnnualChange(final double newSantaBudget, final ArrayList<Gift> newGifts,
                        final ArrayList<Child> newChildren,
                        final ArrayList<ChildUpdate> childrenUpdates) {
        this.newSantaBudget = newSantaBudget;
        this.newGifts = newGifts;
        this.newChildren = newChildren;
        this.childrenUpdates = childrenUpdates;
    }

    /**
     * checkstyle
     */
    public double getNewSantaBudget() {
        return newSantaBudget;
    }

    /**
     * checkstyle
     */
    public ArrayList<Gift> getNewGifts() {
        return newGifts;
    }

    /**
     * checkstyle
     */
    public ArrayList<Child> getNewChildren() {
        return newChildren;
    }

    /**
     * checkstyle
     */
    public ArrayList<ChildUpdate> getChildrenUpdates() {
        return childrenUpdates;
    }
}
