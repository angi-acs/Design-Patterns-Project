package visitors;

import child.Child;
import santa.Santa;

public interface Visitor {
    /**
     * a
     * @param child b
     */
    void visit(Child child);

    /**
     *
     * @param santa a
     */
    void visit(Santa santa);
}
