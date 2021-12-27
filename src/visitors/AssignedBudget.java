package visitors;

import child.Child;
import santa.Santa;

public class AssignedBudget implements Visitor {
    private double budgetUnit;

    /**
     * a
     * @param child b
     */
    @Override
    public void visit(final Child child) {
      child.setAssignedBudget(child.getAverageScore() * budgetUnit);
    }

    /**
     *
     * @param santa a
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
