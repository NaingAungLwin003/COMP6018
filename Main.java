package battleShips;

/**
 * The Main class serves as the entry point for the Battleships application.
 * It initializes the GameModel and GameController, and launches either the
 * CLI or GUI version of the game depending on the useGUI flag.
 * 
 * This class demonstrates the integration of MVC architecture by coordinating
 * the model, view, and controller components during application startup.
 * 
 * @author Naing Aung Lwin(19337512)
 */
public class Main {
    public static void main(String[] args) {
        GameModel model = new GameModel();
        GameController controller = new GameController(model);

        boolean useGUI = false; // Set to false to launch CLI version

        if (useGUI) {
            // Launch GUI on the Event Dispatch Thread (EDT)
            javax.swing.SwingUtilities.invokeLater(() -> {
                GameGUI gui = new GameGUI(model, controller);
                gui.setVisible(true);
            });
        } else {
            // Launch the CLI version of the game
            GameCLI cli = new GameCLI(model);
            cli.start();
        }
    }
}