package test.java;

import model.GameModel;
import model.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

public class TestGame {
    private GameModel model;

    @BeforeEach
    public void setup() {
        model = new GameModel();
    }

    @Test
    public void testIsSafeMethod() {
        model.getBoard()[0][0] = 1;
        assertFalse(model.isSafe(0, 1), "Column threat not detected.");
        assertFalse(model.isSafe(1, 0), "Row threat not detected.");
        assertFalse(model.isSafe(1, 1), "Diagonal threat not detected.");
    }

    @Test
    public void testSaveSolutionAndCheckDuplicate() {
        String solution = "[0, 4, 7, 5, 2, 6, 1, 3]";
        DatabaseManager.savePlayerAttempt("Alice", solution);
        assertTrue((BooleanSupplier) DatabaseManager.isDuplicateSolution(solution), "Should detect duplicate solution.");
    }

    @Test
    public void testFindSolutionsSequentially() {
        int count = model.findSolutionsSequentially();
        assertEquals(92, count, "Sequential should find 92 solutions.");
    }
}
