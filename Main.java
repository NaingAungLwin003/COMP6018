package Battleships;

public class Main {
    public static void main(String[] args) {
        GameModel model = new GameModel();
        boolean useGUI = true; // Set to false to run CLI version

        if (!useGUI) {
            GameCLI cli = new GameCLI(model);
            cli.start();
        } else {
            javax.swing.SwingUtilities.invokeLater(() -> {
                GameGUI gui = new GameGUI(model);
                gui.setVisible(true);
            });
        }
    }
}