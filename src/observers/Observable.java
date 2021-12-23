package observers;

import java.util.ArrayList;

public class Observable {
    private final ArrayList<Observer> observers = new ArrayList<>();

    /**
     * a
     * @param o b
     */
    public void addObserver(final Observer o) {
        observers.add(o);
    }

    /**
     * a
     * @param o b
     */
    public void removeObserver(final Observer o) {
        observers.remove(o);
    }

    /**
     * a
     * @param arg b
     */
    public void notifyObservers(final Object arg) {
        for (Observer o : observers) {
            o.update(arg);
        }
    }
}
