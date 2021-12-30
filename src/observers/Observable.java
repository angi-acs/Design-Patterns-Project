package observers;

import java.util.ArrayList;

public class Observable {
    private final ArrayList<Observer> observers = new ArrayList<>();

    /**
     * @param o observer to be added to the list
     */
    public void addObserver(final Observer o) {
        observers.add(o);
    }

    /**
     * @param o observer to be removed from the list
     */
    public void removeObserver(final Observer o) {
        observers.remove(o);
    }

    /**
     * Calls the update method for every observer from the list
     * @param arg argument to be passed to the observer's update method
     */
    public void notifyObservers(final Object arg) {
        observers.forEach(observer -> observer.update(arg));
    }
}
