package visitors;

import child.Child;
import santa.Santa;

public interface Visitor {
    /**
     * @param child object to be visited
     */
    void visit(Child child);

    /**
     *
     * @param santa object to be visited
     */
    void visit(Santa santa);
}
