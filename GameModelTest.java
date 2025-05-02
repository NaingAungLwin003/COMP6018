package battleShips;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *c when all ships are sunk.
 * 
 * These tests use JUnit 5 and are based Unit test class for the GameModel.
 * 
 * This class contains three unit tests for verifying:
 * - Proper initialization of the game board.
 * - Correct attack handling and hit detection.
 * - Game-over logi on the Model's public API.
 * This test class cannot be run via the IDE so I basically create the .bat file
 * to run the testings externally.
 * 
 * @author Naing Aung Lwin(19337512)
 */
public class GameModelTest {

    private GameModel model;

    /**
     * Sets up a instance of the GameModel before each test.
     */
    @BeforeEach
    public void setup() {
        model = new GameModel();
    }

    /**
     * Test 1: Ensures the board is initialized correctly with valid dimensions
     * and that at least one ship ('S') has been placed.
     */
    @Test
    public void testBoardInitialization() {
        model.init(); // Randomly place ships

        char[][] board = model.getBoard();
        assertEquals(10, board.length, "Board should have 10 rows.");
        assertEquals(10, board[0].length, "Each row should have 10 columns.");

        // Check if at least one ship part is present
        boolean foundShip = false;
        for (int i = 0; i < 10 && !foundShip; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j] == 'S') {
                    foundShip = true;
                    break;
                }
            }
        }
        assertTrue(foundShip, "There should be at least one ship placed on the board.");
    }

    /**
     * Test 2: Verifies that attacking a known ship location results in a hit.
     * It scans the board for a ship ('S'), attacks it, and checks that the
     * result starts with "Hit".
     */
    @Test
    public void testAttackHitShip() {
        model.init(); // Initialize with random ships

        String target = null;

        // Find a position on the board that contains a ship
        for (char col = 'A'; col <= 'J'; col++) {
            for (int row = 1; row <= 10; row++) {
                if (model.getBoard()[row - 1][col - 'A'] == 'S') {
                    target = "" + col + row;
                    break;
                }
            }
            if (target != null) break;
        }

        assertNotNull(target, "Expected to find a ship to attack.");

        String result = model.attack(target);
        assertTrue(result.startsWith("Hit"), "Expected a hit result when attacking a ship part.");
    }

    /**
     * Test 3: Simulates a full game by attacking every cell on the board
     * and verifies that all ships are eventually sunk.
     */
    @Test
    public void testWinAfterAllShipsSunk() {
        model.init(); // Start new model

        // Attack all 100 positions on the board
        for (char col = 'A'; col <= 'J'; col++) {
            for (int row = 1; row <= 10; row++) {
                model.attack("" + col + row);
            }
        }

        assertTrue(model.allSunk(), "After attacking every cell, all ships should be sunk.");
    }
}
