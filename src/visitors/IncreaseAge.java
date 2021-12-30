package visitors;

import child.Child;
import santa.Santa;

public class IncreaseAge implements Visitor {
    /**
     * Increases the child's age by one and sets it
     */
    @Override
    public void visit(final Child child) {
        int newAge = child.getAge() + 1;
        child.setAge(newAge);
    }

    /**
     * Calls the visitor for every child in Santa's list
     */
    @Override
    public void visit(final Santa santa) {
        santa.getChildren().forEach(child -> child.accept(this));
    }


}
