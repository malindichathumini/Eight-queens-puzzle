
package solver;

import model.DatabaseManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadedSolver {
    private static final List<String> solutions = new ArrayList<>();
    private static final Object lock = new Object();

    public static void solveAndSave() {
        long start = System.currentTimeMillis();
        solutions.clear();
        ExecutorService exec = Executors.newFixedThreadPool(8);
        List<Future<?>> futures = new ArrayList<>();

        // Launch one thread per first-column placement
        for (int col = 0; col < 8; col++) {
            final int firstCol = col;
            futures.add(exec.submit(() -> {
                int[] board = new int[8];
                board[0] = firstCol;
                placeQueen(board, 1);
            }));
        }

        // Wait for all threads
        for (Future<?> f : futures) {
            try { f.get(); }
            catch (InterruptedException | ExecutionException e) { e.printStackTrace(); }
        }
        exec.shutdown();

        long time = System.currentTimeMillis() - start;
        // Save each solution, tagging as "threaded"
        for (String sol : solutions) {
            DatabaseManager.saveSolution("threaded", sol);
        }

        System.out.println("Threaded: " + solutions.size() +
                " solutions in " + time + "ms");
    }

    private static void placeQueen(int[] board, int row) {
        if (row == 8) {
            synchronized (lock) {
                solutions.add(boardToString(board));
            }
            return;
        }
        for (int col = 0; col < 8; col++) {
            if (isSafe(board, row, col)) {
                board[row] = col;
                placeQueen(board, row + 1);
            }
        }
    }

    private static boolean isSafe(int[] board, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i] == col ||
                    Math.abs(board[i] - col) == Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }

    private static String boardToString(int[] board) {
        StringBuilder sb = new StringBuilder(8);
        for (int c : board) sb.append(c);
        return sb.toString();
    }
}





