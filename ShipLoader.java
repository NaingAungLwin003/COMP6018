package Battleships;

import java.io.*;
import java.util.*;

class ShipLoader {
    public static boolean loadShipsFromFile(String filename, List<Ship> ships, char[][] board) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            List<int[]> coords = new ArrayList<>();
            for (String part : parts) {
                int row = Integer.parseInt(part.substring(1)) - 1;
                int col = part.charAt(0) - 'A';
                if (row < 0 || row >= 10 || col < 0 || col >= 10 || board[row][col] != '-') {
                    return false;
                }
                coords.add(new int[]{row, col});
            }
            for (int[] coord : coords) {
                board[coord[0]][coord[1]] = 'S';
            }
            ships.add(new Ship(coords));
        }
        return true;
    }
}