package strategies;

import child.Child;

import java.util.ArrayList;

public interface Strategy {

    /**
     * Child sorting strategy
     */
    void sort(ArrayList<Child> children);
}
