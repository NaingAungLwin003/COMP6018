package Battleships;

import java.util.*;

class ShipPlacer {
    public static void placeShips(List<Ship> ships, char[][] board) {
        int[] sizes = {5, 4, 3, 2, 2};
        Random rand = new Random();
        for (int size : sizes) {
            while (true) {
                boolean vertical = rand.nextBoolean();
                int row = rand.nextInt(10 - (vertical ? size : 0));
                int col = rand.nextInt(10 - (vertical ? 0 : size));
                List<int[]> pos = new ArrayList<>();
                boolean fits = true;
                for (int i = 0; i < size; i++) {
                    int r = row + (vertical ? i : 0);
                    int c = col + (vertical ? 0 : i);
                    if (board[r][c] != '-') {
                        fits = false;
                        break;
                    }
                    pos.add(new int[]{r, c});
                }
                if (fits) {
                    for (int[] p : pos) board[p[0]][p[1]] = 'S';
                    ships.add(new Ship(pos));
                    break;
                }
            }
        }
    }
}