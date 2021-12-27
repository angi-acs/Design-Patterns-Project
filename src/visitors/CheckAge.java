package visitors;

import child.Child;
import common.Constants;
import santa.Santa;

public class CheckAge implements Visitor {
    /**
     *
     * @param child b
     */
    @Override
    public void visit(final Child child) {

    }

    /**
     *
     * @param santa a
     */
    @Override
    public void visit(final Santa santa) {
        santa.getChildren().removeIf(child -> child.getAge() > Constants.TEEN);
    }
}
