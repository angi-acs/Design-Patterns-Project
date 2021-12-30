package visitors;

import child.Child;
import common.Constants;
import santa.Santa;

public class CheckAge implements Visitor {
    /**
     * Not used
     */
    @Override
    public void visit(final Child child) {

    }

    /**
     * If a child is over 18, it's removed from Santa's children list
     */
    @Override
    public void visit(final Santa santa) {
        santa.getChildren().removeIf(child -> child.getAge() > Constants.TEEN);
    }
}
