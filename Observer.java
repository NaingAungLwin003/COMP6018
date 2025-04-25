package battleShips;

/**
 * The Observer interface defines the contract for any class that wants to be notified
 * of changes in an Observable. Classes like GameGUI implement this interface to react
 * to updates from the GameModel.
 * 
 * It is part of a custom Observer pattern implementation in the Battleships project.
 * 
 * @author Naing Aung Lwin(19337512)
 */
public interface Observer {

    /**
     * Called by the Observable when a change occurs.
     * Implementing classes define the logic to execute on update.
     */
    void update();
}