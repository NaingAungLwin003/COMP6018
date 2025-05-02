package battleShips;

import java.util.ArrayList;
import java.util.List;

/**
 * The Observable class is a custom implementation of the Observer design pattern.
 * This class allows observers (Views like GameGUI) to register and receive
 * updates whenever the model's state changes. This enables decoupled communication
 * between the model and view components in the application.
 * 
 * Classes that want to be observed (e.g., GameModel) can extend this class.
 * 
 * @author Naing Aung Lwin(19337512)
 */
public class Observable {
    private final List<Observer> observers = new ArrayList<>();

    /**
     * Registers an observer to receive updates.
     *
     * @param o the observer to add
     */
    public void addObserver(Observer o) {
        observers.add(o);
    }

    /**
     * Removes a previously registered observer.
     *
     * @param o the observer to remove
     */
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    /**
     * Notifies all registered observers by calling their update() method.
     */
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }
}