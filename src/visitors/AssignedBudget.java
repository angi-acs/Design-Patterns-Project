package visitors;

import child.Child;
import santa.Santa;

public class AssignedBudget implements Visitor {
    private double budgetUnit;

    /**
     * Sets the budget for the child received as @param
     */
    @Override
    public void visit(final Child child) {
      child.setAssignedBudget(child.getAverageScore() * budgetUnit);
    }

    /**
     * Computes the budget unit based on the average score sum
     * and calls the visitor for every child in Santa's list
     */
    @Override
    public void visit(final Santa santa) {
        double averageScoreSum = 0;
        for (Child child : santa.getChildren()) {
            averageScoreSum += child.getAverageScore();
        }
        budgetUnit = santa.getBudget() / averageScoreSum;

        santa.getChildren().forEach(child -> child.accept(this));
    }


}
