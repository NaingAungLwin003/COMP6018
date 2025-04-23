package Battleships;

import java.io.*;
import java.util.*;

class GameModel {
    private final char[][] board = new char[10][10];
    private final List<Ship> ships = new ArrayList<>();
    private int tries = 0;

    public void init() {
        for (int i = 0; i < 10; i++) Arrays.fill(board[i], '-');
        ships.clear();
        ShipPlacer.placeShips(ships, board);
    }

    public boolean loadFromFile(String filename) {
        try {
            for (int i = 0; i < 10; i++) Arrays.fill(board[i], '-');
            ships.clear();
            return ShipLoader.loadShipsFromFile(filename, ships, board);
        } catch (IOException e) {
            return false;
        }
    }

    public String attack(String pos) {
        tries++;
        if (pos.length() < 2 || pos.length() > 3) return "Invalid input";
        int col = pos.charAt(0) - 'A';
        int row = Integer.parseInt(pos.substring(1)) - 1;
        if (row < 0 || row >= 10 || col < 0 || col >= 10) return "Invalid input";
        if (board[row][col] == 'H' || board[row][col] == 'M') return "Already tried";

        for (Ship s : ships) {
            if (s.hit(row, col)) {
                board[row][col] = 'H';
                return s.isSunk() ? "Hit and sunk!" : "Hit!";
            }
        }
        board[row][col] = 'M';
        return "Miss!";
    }

    public boolean allSunk() {
        return ships.stream().allMatch(Ship::isSunk);
    }

    public char[][] getBoard() {
        return board;
    }

    public int getTries() {
        return tries;
    }
}