package battleShips;

import java.util.Scanner;

/**
 * The GameCLI class provides a command-line interface for playing the Battleships game.
 * It supports random ship placement and loading ship configurations from a file.
 * It acts as an alternative View to the GUI and directly interacts with the GameModel.
 * 
 * @author Naing Aung Lwin(19337512)
 */
class GameCLI {
    private final GameModel model;
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Constructs the CLI using the provided game model.
     *
     * @param model the game model containing board state and logic
     */
    public GameCLI(GameModel model) {
        this.model = model;
    }

    /**
     * Starts the game loop in the command-line interface.
     * Prompts the user to attack or load ships from a file, until all ships are sunk.
     */
    public void start() {
        System.out.println("Welcome to Battleships CLI!");
        model.init(); // Default: place ships randomly
        printBoard();

        while (!model.allSunk()) {
            System.out.print("Type for once LOAD to run from the file!!!\n");
            System.out.print("Enter attack position (e.g. E4 or LOAD): ");
            String input = scanner.nextLine().toUpperCase();

            if (input.equals("LOAD")) {
                System.out.print("Enter filename: ");
                String filename = scanner.nextLine();
                if (model.loadFromFile(filename)) {
                    System.out.println("Ships loaded from file.");
                } else {
                    System.out.println("Invalid file format.");
                }
            } else {
                String result = model.attack(input);
                System.out.println(result);
                printBoard();
            }
        }

        System.out.println("Congratulations! You sunk all the ships in " + model.getTries() + " tries.");
    }

    /**
     * Prints the current board to the console.
     * Ship locations are hidden; only hits and misses are shown.
     */
    private void printBoard() {
        char[][] board = model.getBoard();
        System.out.println("  A B C D E F G H I J");
        for (int i = 0; i < 10; i++) {
            System.out.print((i + 1) + (i == 9 ? "" : " "));
            for (int j = 0; j < 10; j++) {
                char c = board[i][j];
                if (c == 'H' || c == 'M') System.out.print(c + " ");
                else System.out.print("- ");
            }
            System.out.println();
        }
    }
}