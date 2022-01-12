package visitors;

import child.Child;
import common.Constants;
import santa.Santa;

public class BonusScore implements Visitor {
    /**
     * Adds a nice score bonus to the average score,
     * also checks to not exceed the maximum allowed
     */
    @Override
    public void visit(final Child child) {
        double score = child.getAverageScore();
        score += score * child.getNiceScoreBonus() / Constants.HUNDRED;
        if (score > Constants.BABY_SCORE) {
            score = Constants.BABY_SCORE;
        }
        child.setAverageScore(score);
    }

    /**
     * Calls the visitor for every child in Santa's list
     */
    @Override
    public void visit(final Santa santa) {
        santa.getChildren().forEach(child -> child.accept(this));
    }
}
