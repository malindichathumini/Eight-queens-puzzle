package controller;

import model.DatabaseManager;
import model.GameModel;
import view.GameView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;

public class GameController {
    private final GameModel model;
    private final GameView view;
    private Timer timer;
    private int timeElapsed;
    private final ImageIcon queenIcon;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;

        // Load queen icon image
        ImageIcon tmpIcon = null;
        try {
            URL url = getClass().getClassLoader().getResource("queen.png");
            if (url != null) {
                Image img = ImageIO.read(url);
                Image scaled = img.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                tmpIcon = new ImageIcon(scaled);
            } else {
                System.err.println("queen.png not found on classpath!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        queenIcon = tmpIcon;

        initController();
    }

    // Initialize controller by setting up listeners for buttons
    public void initController() {
        view.addStartListener(new StartListener());
        view.addRestartListener(new RestartListener());
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                view.addCellListener(r, c, new CellListener(r, c));
            }
        }
    }

    // Listener for the Start button
    private class StartListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = view.nameField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Enter your name.");
                return;
            }
            model.startGame(name);
            timeElapsed = 0;
            view.timeLabel.setText("Time: 0s");
            view.playerLabel.setText("Player: " + name);
            view.queensLabel.setText("Queens Placed: 0");
            view.updateRemainingQueens(8);

            // Enable all buttons and reset board
            for (int r = 0; r < 8; r++) {
                for (int c = 0; c < 8; c++) {
                    JButton btn = view.cells[r][c];
                    btn.setEnabled(true);
                    btn.setIcon(null);
                    btn.setText("");
                }
            }

            // Start timer
            if (timer != null && timer.isRunning()) {
                timer.stop();
            }
            timer = new Timer(1000, ev -> {
                timeElapsed++;
                view.timeLabel.setText("Time: " + timeElapsed + "s");
            });
            timer.start();
        }
    }

    // Listener for the Restart button
    private class RestartListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (timer != null) timer.stop();
            model.startGame("");
            view.nameField.setText("");
            view.playerLabel.setText("Player: ");
            view.queensLabel.setText("Queens Placed: 0");
            view.timeLabel.setText("Time: 0s");
            view.updateRemainingQueens(8);

            // Disable all buttons and reset board
            for (int r = 0; r < 8; r++) {
                for (int c = 0; c < 8; c++) {
                    JButton btn = view.cells[r][c];
                    btn.setEnabled(false);
                    btn.setIcon(null);
                    btn.setText("");
                }
            }
        }
    }

    // Listener for the cells (when a player places a queen)
    private class CellListener implements ActionListener {
        private final int row, col;

        public CellListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void actionPerformed(ActionEvent e) {
            if (model.isCellOccupied(row, col)) return; // Check if the cell is already occupied

            boolean safe = model.placeQueen(row, col); // Try to place the queen
            JButton btn = view.cells[row][col];
            if (queenIcon != null) {
                btn.setIcon(queenIcon); // Use the queen icon if available
            } else {
                btn.setText("Q"); // Otherwise, just display "Q"
            }

            // Update view to reflect number of queens placed
            view.queensLabel.setText("Queens Placed: " + model.getQueensPlaced());
            view.updateRemainingQueens(8 - model.getQueensPlaced());

            // Check if placement is safe, and handle game outcome
            if (!safe) {
                timer.stop();
                DatabaseManager.saveResult(model.getPlayerName(), timeElapsed, "manual", "loss");
                view.showLossMessage(timeElapsed);
                disableBoard(); // Disable all cells when game is over
                return;
            }

            if (model.getQueensPlaced() == 8) {
                timer.stop();
                DatabaseManager.saveResult(model.getPlayerName(), timeElapsed, "manual", "win");
                view.showWinMessage(model.getPlayerName(), timeElapsed);
                disableBoard(); // Disable all cells when game is over
            }
        }
    }

    // Disable all the cells on the board
    private void disableBoard() {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                view.cells[r][c].setEnabled(false); // Disable each button
            }
        }
    }
}

