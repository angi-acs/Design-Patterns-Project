package visitors;

import child.Child;
import common.Constants;
import santa.Santa;

public class BonusScore implements Visitor {

    /**
     * a
     * @param child object to be visited
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
     * a
     * @param santa object to be visited
     */
    @Override
    public void visit(final Santa santa) {
        santa.getChildren().forEach(child -> child.accept(this));
    }
}
