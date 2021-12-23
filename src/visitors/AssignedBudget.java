package visitors;

import child.Child;

public class AssignedBudget implements ChildVisitor {
    private final double budgetUnit;

    public AssignedBudget(final double budgetUnit) {
        this.budgetUnit = budgetUnit;
    }

    /**
     * a
     * @param child b
     */
    @Override
    public void visit(final Child child) {
      child.setAssignedBudget(child.getAverageScore() * budgetUnit);
    }
}
