package model;

public class GameModel {
    private final boolean[][] board = new boolean[8][8];
    private int queensPlaced;
    private String playerName;

    /** Start or restart the game */
    public void startGame(String playerName) {
        this.playerName = playerName;
        queensPlaced = 0;
        for (int r = 0; r < 8; r++)
            for (int c = 0; c < 8; c++)
                board[r][c] = false;
    }

    /** Check if a cell already has a queen */
    public boolean isCellOccupied(int row, int col) {
        return board[row][col];
    }

    /**
     * Place a queen at (row,col).
     * Returns false if this creates any conflict.
     */
    public boolean placeQueen(int row, int col) {
        board[row][col] = true;
        queensPlaced++;
        if (hasConflict()) {
            return false;
        }
        return true;
    }

    public int getQueensPlaced() {
        return queensPlaced;
    }

    public String getPlayerName() {
        return playerName;
    }

    /** Scan all queen pairs to see if any threaten each other */
    private boolean hasConflict() {
        for (int r1 = 0; r1 < 8; r1++) {
            for (int c1 = 0; c1 < 8; c1++) {
                if (!board[r1][c1]) continue;
                for (int r2 = 0; r2 < 8; r2++) {
                    for (int c2 = 0; c2 < 8; c2++) {
                        if ((r1 != r2 || c1 != c2) && board[r2][c2]) {
                            if (threatens(r1, c1, r2, c2)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /** True if two queens threaten: same row/col or diagonal */
    private boolean threatens(int r1, int c1, int r2, int c2) {
        return r1 == r2
                || c1 == c2
                || Math.abs(r1 - r2) == Math.abs(c1 - c2);
    }

    public int findSolutionsSequentially() {
        return 0;
    }

    public boolean isSafe(int i, int i1) {
        return false;
    }

    public int[][] getBoard() {
        return new int[0][];
    }
}
