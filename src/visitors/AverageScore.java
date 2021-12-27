package visitors;

import child.Child;
import common.Constants;
import santa.Santa;

public class AverageScore implements Visitor {
    /**
     * a
     * @param child b
     */
    @Override
    public void visit(final Child child) {
        if (child.getAge() < Constants.BABY) {
            child.setAverageScore(Constants.BABY_SCORE);
            return;
        }
        if (child.getAge() < Constants.KID) {
            double score = 0;
            for (Double s : child.getNiceScoreHistory()) {
                score += s;
            }
            score /= child.getNiceScoreHistory().size();
            child.setAverageScore(score);
            return;
        }
        if (child.getAge() <= Constants.TEEN) {
            double score = 0;
            int i = 1;
            int sum = 0;
            for (Double s : child.getNiceScoreHistory()) {
                score += s * i;
                sum += i;
                i++;
            }
            score /= sum;
            child.setAverageScore(score);
        }
    }

    /**
     *
     * @param santa a
     */
    @Override
    public void visit(final Santa santa) {
        santa.getChildren().forEach(child -> child.accept(this));
    }
}
