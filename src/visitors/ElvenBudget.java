package visitors;

import child.Child;
import common.Constants;
import santa.Santa;

public class ElvenBudget implements Visitor {

    /**
     * a
     * @param child object to be visited
     */
    @Override
    public void visit(final Child child) {
        double budget = child.getAssignedBudget();
        if (child.getElf().equals(Constants.BLACK)) {
            budget -= budget * Constants.ELF_BUDGET / Constants.HUNDRED;
        }
        if (child.getElf().equals(Constants.PINK)) {
            budget += budget * Constants.ELF_BUDGET / Constants.HUNDRED;
        }
        child.setAssignedBudget(budget);
    }

    /**
     * a
     * @param santa object to be visited
     */
    @Override
    public void visit(final Santa santa) {
        santa.getChildren().forEach(child -> child.accept(this));
    }
}
