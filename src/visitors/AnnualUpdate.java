package visitors;

import child.Child;
import common.Constants;
import fileio.AnnualChange;
import fileio.ChildUpdate;
import santa.Santa;

import java.util.LinkedHashSet;

public class AnnualUpdate implements Visitor {
    private final AnnualChange annualChange;

    public AnnualUpdate(final AnnualChange annualChange) {
        this.annualChange = annualChange;
    }

    /**
     * Not used
     */
    @Override
    public void visit(final Child child) {

    }

    /**
     * Updates Santa's fields based on annual change
     */
    @Override
    public void visit(final Santa santa) {
        santa.setBudget(annualChange.getNewSantaBudget());

        if (annualChange.getNewGifts() != null) {
            santa.getGifts().addAll(annualChange.getNewGifts());
        }
        if (annualChange.getNewChildren() != null) {
            santa.getChildren().addAll(annualChange.getNewChildren());
        }
        if (!annualChange.getChildrenUpdates().isEmpty()) {
            for (ChildUpdate childUpdate : annualChange.getChildrenUpdates()) {
                Child child = santa.getChildById(childUpdate.getId());
                if (child != null) {

                    if (childUpdate.getNewNiceScore() != Constants.NO_NEW_NICE_SCORE) {
                        child.getNiceScoreHistory().add(childUpdate.getNewNiceScore());
                    }
                    if (!childUpdate.getNewGiftsPreferences().isEmpty()) {
                        // LinkedHashSet cannot contain duplicates
                        LinkedHashSet<String> newGiftsPreferences = new LinkedHashSet<>();
                        for (String category : childUpdate.getNewGiftsPreferences()) {
                            newGiftsPreferences.add(category);
                            child.getGiftsPreferences().remove(category);
                        }
                        // adds at the beginning of the list
                        child.getGiftsPreferences().addAll(0, newGiftsPreferences);
                    }
                    child.setElf(childUpdate.getElf());
                }
            }
        }
    }
}
