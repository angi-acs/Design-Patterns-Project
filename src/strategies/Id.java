package strategies;

import child.Child;

import java.util.ArrayList;
import java.util.Comparator;

public class Id implements Strategy {

    /**
     * Sorts children by Id
     */
    @Override
    public void sort(final ArrayList<Child> children) {
        children.sort(Comparator.comparingInt(Child::getId));
    }
}
