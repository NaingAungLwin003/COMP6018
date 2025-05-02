package battleShips;

import java.io.*;
import java.util.*;

/**
 * The GameModel class maintains the state and logic of the Battleships game.
 * It manages the board, tracks ship positions, handles attacks, and supports both
 * random and file-based ship placement. This class represents the 'Model' in the
 * MVC architecture and extends Observable to notify views (like GameGUI) when the
 * game state changes.
 * 
 * Invariants:
 * - The board is always a 10x10 grid.
 * - Ship positions are within the board boundaries and do not overlap.
 * - The number of tries is non-negative.
 * - Cells on the board are either '-', 'H', 'M', or 'S' (ship).
 * 
 * @author Naing Aung Lwin(19337512)
 */
public class GameModel extends Observable {
    private final char[][] board = new char[10][10]; // 10x10 game board
    private final List<Ship> ships = new ArrayList<>(); // list of ships
    private int tries = 0; // number of user attempts

    /**
     * Initializes the board and places ships randomly.
     * Notifies observers once the board is ready.
     */
    public void init() {
        // Reset board
        for (int i = 0; i < 10; i++) Arrays.fill(board[i], '-');
        ships.clear();
        ShipPlacer.placeShips(ships, board);
        notifyObservers(); // trigger GUI update
    }

    /**
    * Loads ships from a configuration text file.
    * 
    * @param filename path to the ship placement file
    * @requires filename is a valid file path
    * @ensures If successful:
    * - Ships are placed on the board without overlaps
    * - Observers are notified
    * @return true if loading succeeded; false otherwise
    */
    public boolean loadFromFile(String filename) {
        try {
            for (int i = 0; i < 10; i++) Arrays.fill(board[i], '-');
            ships.clear();
            boolean loaded = ShipLoader.loadShipsFromFile(filename, ships, board);
            notifyObservers();
            return loaded;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Processes an attack at the given board position.
        * 
        * @param pos position string in format "A1" to "J10"
        * @requires pos is a valid coordinate string (e.g., length 2-3, valid row/col)
        * @ensures If the attack is valid:
        * - The board is updated with 'H' (hit) or 'M' (miss)
        * - Observers are notified of the change
        * @return Result message: "Hit!", "Miss!", "Invalid input", or "Already tried"
     */
    public String attack(String pos) {
        tries++;
        // Pre-condition: pos is not null
        if (pos.length() < 2 || pos.length() > 3) return "Invalid input";
        int col = pos.charAt(0) - 'A';
        int row = Integer.parseInt(pos.substring(1)) - 1;
        // Validate parsed row/col
        if (row < 0 || row >= 10 || col < 0 || col >= 10) return "Invalid input";
        if (board[row][col] == 'H' || board[row][col] == 'M') return "Already tried";

        // Check each ship for a hit
        for (Ship s : ships) {
            if (s.hit(row, col)) {
                board[row][col] = 'H';
                notifyObservers();
                return s.isSunk() ? "Hit and sunk!" : "Hit!";
            }
        }
        // Check each ship for a miss
        board[row][col] = 'M';
        notifyObservers();
        return "Miss!";
    }

    /**
     * @return true if all ships have been sunk (game over condition)
     */
    public boolean allSunk() {
        return ships.stream().allMatch(Ship::isSunk);
    }

    /**
     * @return the current state of the board (2D char array)
     */
    public char[][] getBoard() {
        return board;
    }

    /**
     * @return number of player attack attempts so far
     */
    public int getTries() {
        return tries;
    }

    /**
     * @return the list of all ships on the board
     */
    public List<Ship> getShips() {
        return ships;
    }
}