package battleShips;

import java.io.File;

/**
 * The GameController class handles user input from the view (GameGUI or GameCLI)
 * and delegates actions to the GameModel. It acts as the 'Controller' in the MVC architecture.
 * It provides logic for processing cell clicks, placing ships randomly, and loading ship
 * configurations from a file.
 * 
 * @author Naing Aung Lwin(19337512)
 */
public class GameController {
    private final GameModel model;

    /**
     * Constructs the controller with a reference to the game model.
     *
     * @param model the GameModel instance
     */
    public GameController(GameModel model) {
        this.model = model;
    }

    /**
     * Handles a click from the GUI on a specific grid cell.
     * Converts row and column indices into Battleships coordinates (e.g., A5)
     * and performs an attack via the model.
     *
     * @param row board row (0–9)
     * @param col board column (0–9)
     */
    public void handleClick(int row, int col) {
        String pos = "" + (char)('A' + col) + (row + 1);
        model.attack(pos);
    }

    /**
     * Handles the request to place ships randomly on the board.
     * Delegates to the model's init() method.
     */
    public void handleRandomPlacement() {
        model.init();
    }

    /**
     * Handles loading ships from a file.
     * Passes the file path to the model's loadFromFile() method.
     *
     * @param file the file containing ship coordinates
     * @return true if the file was loaded successfully; false otherwise
     */
    public boolean handleLoadFromFile(File file) {
        return model.loadFromFile(file.getAbsolutePath());
    }
}