package battleShips;

import java.util.*;

/**
 * The ShipPlacer class provides logic for placing ships randomly on the game board.
 * It ensures that ships do not overlap and that they fit within board boundaries.
 * This class supports the default ship placement mode.
 * 
 * @author Naing Aung Lwin(19337512)
 */
class ShipPlacer {

    /**
     * Randomly places a set of ships on the board, ensuring they do not overlap.
     *
     * @param ships list to store the placed ships
     * @param board the game board where ships will be placed
     */
    public static void placeShips(List<Ship> ships, char[][] board) {
        int[] sizes = {5, 4, 3, 2, 2}; // Sizes of ships to place
        Random rand = new Random();

        for (int size : sizes) {
            while (true) {
                boolean vertical = rand.nextBoolean();
                int row = rand.nextInt(10 - (vertical ? size : 0));
                int col = rand.nextInt(10 - (vertical ? 0 : size));

                List<int[]> pos = new ArrayList<>();
                boolean fits = true;

                // Check if the ship fits in the chosen direction
                for (int i = 0; i < size; i++) {
                    int r = row + (vertical ? i : 0);
                    int c = col + (vertical ? 0 : i);

                    if (board[r][c] != '-') {
                        fits = false; // Conflict with another ship
                        break;
                    }

                    pos.add(new int[]{r, c});
                }

                // Place the ship if there's no conflict
                if (fits) {
                    for (int[] p : pos) {
                        board[p[0]][p[1]] = 'S';
                    }
                    ships.add(new Ship(pos));
                    break;
                }
            }
        }
    }
}