package battleShips;

import java.io.*;
import java.util.*;

/**
 * The ShipLoader class provides functionality for loading ship configurations from an external file.
 * Each ship is represented by a comma-separated list of
 * coordinates (e.g., A1,A2,A3). The class ensures all positions are valid and non-overlapping.
 * 
 * This class also implements the Load from File feature.
 * 
 * @author Naing Aung Lwin(19337512)
 */
class ShipLoader {

    /**
     * Loads ship configurations from a file and places them on the board.
     *
     * @param filename path to the ship configuration file
     * @param ships list to store loaded Ship objects
     * @param board the game board to mark ship positions
     * @return true if all ships were loaded successfully; false if the file is invalid
     * @throws IOException if the file can't be read
     */
    public static boolean loadShipsFromFile(String filename, List<Ship> ships, char[][] board) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            List<int[]> coords = new ArrayList<>();

            for (String part : parts) {
                int row = Integer.parseInt(part.substring(1)) - 1;
                int col = part.charAt(0) - 'A';

                // Validate position and check for overlap
                if (row < 0 || row >= 10 || col < 0 || col >= 10 || board[row][col] != '-') {
                    return false;  // Invalid or overlapping ship
                }

                coords.add(new int[]{row, col});
            }

            // Place ship on the board
            for (int[] coord : coords) {
                board[coord[0]][coord[1]] = 'S';
            }

            ships.add(new Ship(coords));
        }

        return true;
    }
}