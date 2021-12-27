package visitors;

import child.Child;
import santa.Santa;

public class IncreaseAge implements Visitor {
    /**
     *
     * @param child b
     */
    @Override
    public void visit(final Child child) {
        int newAge = child.getAge() + 1;
        child.setAge(newAge);
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
