package battleShips;

import java.util.List;

/**
 * The Ship class represents a ship placed on the Battleships game board.
 * Each ship consists of multiple positions (row, col pairs) and tracks hits.
 * It provides logic for hit detection and checking if the ship has been sunk.
 * 
 * Used internally by GameModel, ShipLoader, and ShipPlacer.
 * 
 * @author Naing Aung Lwin(19337512)
 */
class Ship {
    private final List<int[]> positions;  // List of coordinates occupied by this ship
    private final boolean[] hits;         // Tracks which positions have been hit

    /**
     * Constructs a Ship with the specified positions on the board.
     *
     * @param pos List of [row, col] pairs indicating ship's location
     */
    public Ship(List<int[]> pos) {
        positions = pos;
        hits = new boolean[pos.size()];
    }

    /**
     * Attempts to hit the ship at the given position.
     *
     * @param row the row index of the attack
     * @param col the column index of the attack
     * @return true if the ship was hit at that position; false otherwise
     */
    public boolean hit(int row, int col) {
        for (int i = 0; i < positions.size(); i++) {
            int[] p = positions.get(i);
            if (p[0] == row && p[1] == col) {
                hits[i] = true;  // Mark hit
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the entire ship has been sunk.
     *
     * @return true if all parts of the ship have been hit; false otherwise
     */
    public boolean isSunk() {
        for (boolean h : hits) {
            if (!h) return false;
        }
        return true;
    }
}