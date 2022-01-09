package strategies;

import child.Child;

import java.util.ArrayList;

public class NiceScore implements Strategy {

    /**
     * a
     * @param children b
     */
    @Override
    public void sort(final ArrayList<Child> children) {
        children.sort((o1, o2) -> {
            if (o1.getAverageScore() == o2.getAverageScore()) {
                return Integer.compare(o1.getId(), o2.getId());
            } else {
                return Double.compare(o2.getAverageScore(), o1.getAverageScore());
            }
        });
    }
}
