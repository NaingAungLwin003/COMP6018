package Battleships;

import java.util.Scanner;

class GameCLI {
    private final GameModel model;
    private final Scanner scanner = new Scanner(System.in);

    public GameCLI(GameModel model) {
        this.model = model;
    }

    public void start() {
        System.out.println("Welcome to Battleships CLI!");
        model.init();
        printBoard();

        while (!model.allSunk()) {
            System.out.print("Enter attack position (e.g. A5): ");
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