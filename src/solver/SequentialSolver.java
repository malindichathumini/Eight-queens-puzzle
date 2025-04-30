
package solver;

import java.util.ArrayList;
import java.util.List;

public class SequentialSolver {
    private List<String> solutions;

    public SequentialSolver() {
        solutions = new ArrayList<>();
    }

    public List<String> solve() {
        solutions.clear();
        int[] board = new int[8];
        placeQueen(board, 0);
        return solutions;// This returns all valid solutions found sequentially
    }

    private void placeQueen(int[] board, int row) {
        if (row == 8) {
            solutions.add(boardToString(board));
            return;
        }
        for (int col = 0; col < 8; col++) {
            if (isSafe(board, row, col)) {
                board[row] = col;
                placeQueen(board, row + 1);
            }
        }
    }

    private boolean isSafe(int[] board, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i] == col || Math.abs(board[i] - col) == Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }

    private String boardToString(int[] board) {
        StringBuilder sb = new StringBuilder();
        for (int row : board) {
            sb.append(row);
        }
        return sb.toString();
    }
}


