package battleShips;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * The GameGUI class implements the graphical interface of the Battleships game using Java Swing.
 * It observes the GameModel and updates its button grid whenever the model state changes.
 * This class represents the 'View' in the MVC pattern and implements the Observer interface.
 * It allows users to attack by clicking on buttons and provides options to place ships randomly
 * or load them from a file.
 * 
 *
 * @author Naing Aung Lwin(19337512)
 */
public class GameGUI extends JFrame implements Observer {
    private final GameModel model;
    private final GameController controller;
    private final JButton[][] buttons = new JButton[10][10]; // Grid of buttons for board

    /**
     * Constructs the GUI window and registers it as an observer to the model.
     *
     * @param model      the game model
     * @param controller the game controller
     */
    public GameGUI(GameModel model, GameController controller) {
        this.model = model;
        this.controller = controller;

        model.addObserver(this); // perform model updates

        setTitle("Battleships GUI");
        setSize(600, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Grid panel with 10x10 board buttons
        JPanel gridPanel = new JPanel(new GridLayout(10, 10));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JButton btn = new JButton();
                buttons[i][j] = btn;
                final int row = i, col = j;
                btn.addActionListener(e -> {
                    controller.handleClick(row, col); // Pass click to controller
                    if (model.allSunk()) {
                        JOptionPane.showMessageDialog(this, "You won in " + model.getTries() + " tries!");
                    }
                });
                gridPanel.add(btn);
            }
        }

        // Control panel with action buttons
        JPanel controlPanel = new JPanel();

        JButton loadButton = new JButton("Load Ships from File");
        loadButton.addActionListener(e -> loadShipsFromFile());
        controlPanel.add(loadButton);

        JButton randomButton = new JButton("Place Ships Randomly");
        randomButton.addActionListener(e -> controller.handleRandomPlacement());
        controlPanel.add(randomButton);

        add(gridPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        update(); // Draw initial board
    }

    /**
     * Observer method: called when the GameModel notifies changes.
     */
    @Override
    public void update() {
        updateButtons();
    }

    /**
     * Updates the button grid to reflect current board state.
     * Sets text and color based on cell content.
     */
    private void updateButtons() {
        char[][] board = model.getBoard();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JButton btn = buttons[i][j];
                char c = board[i][j];
                switch (c) {
                    case 'H' -> {
                        btn.setText("H");
                        btn.setBackground(Color.GREEN);
                        btn.setForeground(Color.WHITE);
                    }
                    case 'M' -> {
                        btn.setText("M");
                        btn.setBackground(Color.RED);
                        btn.setForeground(Color.WHITE);
                    }
                    default -> {
                        btn.setText("");
                        btn.setBackground(null);
                    }
                }
            }
        }
    }

    /**
     * Prompts the user to select a file and requests the controller to load ships from it.
     */
    private void loadShipsFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            boolean loaded = controller.handleLoadFromFile(file);
            if (loaded) {
                JOptionPane.showMessageDialog(this, "Ships loaded successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid ship file format.");
            }
        }
    }
}