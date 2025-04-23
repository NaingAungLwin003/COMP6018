package Battleships;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class GameGUI extends JFrame {
    private final GameModel model;
    private final JButton[][] buttons = new JButton[10][10];

    public GameGUI(GameModel model) {
        this.model = model;
        model.init();
        setTitle("Battleships GUI");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(10, 10));

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JButton btn = new JButton();
                buttons[i][j] = btn;
                final int row = i, col = j;
                btn.addActionListener(e -> {
                    String coord = "" + (char)('A' + col) + (row + 1);
                    String result = model.attack(coord);
                    updateButtons();
                    if (model.allSunk()) {
                        JOptionPane.showMessageDialog(this, "You won in " + model.getTries() + " tries!");
                    }
                });
                add(btn);
            }
        }
        updateButtons();
    }

    private void updateButtons() {
        char[][] board = model.getBoard();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                char c = board[i][j];
                if (c == 'H') buttons[i][j].setText("H");
                else if (c == 'M') buttons[i][j].setText("M");
                else buttons[i][j].setText("");
            }
        }
    }
}