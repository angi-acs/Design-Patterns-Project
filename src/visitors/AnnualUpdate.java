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
     * a
     * @param child b
     */
    @Override
    public void visit(final Child child) {

    }

    /**
     * a
     * @param santa a
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
                if (childUpdate.getNewNiceScore() != Constants.NO_NEW_NICE_SCORE) {
                    Child child = santa.getChildById(childUpdate.getId());
                    if (child != null) {
                        child.getNiceScoreHistory().add(childUpdate.getNewNiceScore());
                    }
                }
                if (!childUpdate.getNewGiftsPreferences().isEmpty()) {
                    Child child = santa.getChildById(childUpdate.getId());
                    if (child != null) {
                        LinkedHashSet<String> newGiftsPreferences = new LinkedHashSet<>();
                        for (String category : childUpdate.getNewGiftsPreferences()) {
                            newGiftsPreferences.add(category);
                            child.getGiftsPreferences().remove(category);
                        }
                        child.getGiftsPreferences().addAll(0, newGiftsPreferences);
                    }
                }
            }
        }
    }
}
