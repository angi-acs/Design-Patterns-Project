package visitors;

import child.Child;
import common.Constants;
import santa.Santa;

public class ElvenBudget implements Visitor {
    /**
     * Increases or decreases a child's budget
     * based on the elf assigned to him/her
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
     * Calls the visitor for every child in Santa's list
     */
    @Override
    public void visit(final Santa santa) {
        santa.getChildren().forEach(child -> child.accept(this));
    }
}
