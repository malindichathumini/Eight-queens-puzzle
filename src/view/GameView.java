
package view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameView extends JFrame {
    public final JTextField nameField = new JTextField(15);
    public final JButton startButton = new JButton("Start");
    public final JButton restartButton = new JButton("Restart");
    public final JButton[][] cells = new JButton[8][8];
    public final JLabel playerLabel = new JLabel("Player: ");
    public final JLabel timeLabel = new JLabel("Time: 0s");
    public final JLabel queensLabel = new JLabel("Queens Placed: 0");
    public final JLabel remainingLabel = new JLabel("Remaining Queens: 8");
    public final JTextArea adviceArea = new JTextArea(
            "Place 8 queens on the chessboard such that no queen threatens another.\n" +
                    "Queens can attack horizontally, vertically, or diagonally."
    );

    private final JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

    private static final Color BG_MAIN = new Color(255, 239, 213);
    private static final Color PANEL_SIDEBAR = new Color(255, 228, 225);
    private static final Color BUTTON_COLOR = new Color(70, 130, 180);
    private static final Color BUTTON_TEXT_COLOR = Color.WHITE;
    private static final Color DARK_CELL = new Color(50, 50, 50);
    private static final Color LIGHT_CELL = new Color(230, 230, 230);

    private static final int CELL_SIZE = 70;

    public GameView() {
        super("8-Queens Puzzle");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);

        mainPanel.setBackground(BG_MAIN);
        setContentPane(mainPanel);

        createMenuBar();
        buildTopPanel();
        buildBoard();
        buildLeftPanel();

        setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar mb = new JMenuBar();
        mb.setBackground(BUTTON_COLOR);
        mb.setForeground(BUTTON_TEXT_COLOR);

        JMenu gameMenu = new JMenu("Game");
        gameMenu.setForeground(BUTTON_TEXT_COLOR);

        gameMenu.add(new JMenuItem("Solve Sequentially"));
        gameMenu.add(new JMenuItem("Solve Using Threads"));
        gameMenu.add(new JMenuItem("Compare Results"));
        gameMenu.add(new JMenuItem("Submit Solution"));
        mb.add(gameMenu);
        setJMenuBar(mb);
    }

    private void buildTopPanel() {
        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        top.setBackground(PANEL_SIDEBAR);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        top.add(nameLabel);

        top.add(nameField);

        stylizeButton(startButton);
        top.add(startButton);

        mainPanel.add(top, BorderLayout.NORTH);
    }

    private void buildBoard() {
        JPanel boardGrid = new JPanel(new GridLayout(8, 8));
        boardGrid.setOpaque(false);

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                JButton b = new JButton();
                b.setEnabled(false);
                b.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
                b.setBorder(new LineBorder(Color.GRAY));
                if ((r + c) % 2 == 0) {
                    b.setBackground(LIGHT_CELL);
                } else {
                    b.setBackground(DARK_CELL);
                }
                b.setFocusPainted(false);
                cells[r][c] = b;
                boardGrid.add(b);
            }
        }

        JPanel topLabels = new JPanel(new GridLayout(1, 8));
        topLabels.setOpaque(false);
        String[] cols = {"A", "B", "C", "D", "E", "F", "G", "H"};
        for (String col : cols) {
            JLabel label = new JLabel(col, SwingConstants.CENTER);
            label.setFont(new Font("SansSerif", Font.BOLD, 14));
            topLabels.add(label);
        }

        JPanel leftLabels = new JPanel(new GridLayout(8, 1));
        leftLabels.setOpaque(false);
        for (int i = 1; i <= 8; i++) {
            JLabel label = new JLabel(String.valueOf(i), SwingConstants.CENTER);
            label.setFont(new Font("SansSerif", Font.BOLD, 14));
            leftLabels.add(label);
        }

        JPanel boardWithLeft = new JPanel(new BorderLayout());
        boardWithLeft.setOpaque(false);
        boardWithLeft.add(leftLabels, BorderLayout.WEST);
        boardWithLeft.add(boardGrid, BorderLayout.CENTER);

        JPanel boardWithTop = new JPanel(new BorderLayout());
        boardWithTop.setOpaque(false);
        boardWithTop.add(topLabels, BorderLayout.NORTH);
        boardWithTop.add(boardWithLeft, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        centerPanel.add(boardWithTop);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
    }

    private void buildLeftPanel() {
        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setBackground(PANEL_SIDEBAR);
        left.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        left.setPreferredSize(new Dimension(300, getHeight()));

        addLeftComponent(left, playerLabel);
        addLeftComponent(left, timeLabel);
        addLeftComponent(left, queensLabel);
        addLeftComponent(left, remainingLabel);

        left.add(Box.createVerticalStrut(20));

        stylizeButton(restartButton);
        left.add(restartButton);

        left.add(Box.createVerticalStrut(20));

        adviceArea.setEditable(false);
        adviceArea.setLineWrap(true);
        adviceArea.setWrapStyleWord(true);
        adviceArea.setFont(new Font("SansSerif", Font.ITALIC, 13));
        adviceArea.setBackground(PANEL_SIDEBAR);
        adviceArea.setMaximumSize(new Dimension(250, 150));
        adviceArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        adviceArea.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1, true),
                "Advice",
                0, 0,
                new Font("SansSerif", Font.BOLD, 13),
                Color.DARK_GRAY
        ));
        left.add(adviceArea);

        mainPanel.add(left, BorderLayout.WEST);
    }

    private void addLeftComponent(JPanel panel, JComponent comp) {
        comp.setFont(new Font("SansSerif", Font.BOLD, 14));
        comp.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(comp);
        panel.add(Box.createVerticalStrut(10));
    }

    private void stylizeButton(JButton button) {
        button.setBackground(BUTTON_COLOR);
        button.setForeground(BUTTON_TEXT_COLOR);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBorder(new LineBorder(Color.BLACK, 1, true));
        button.setPreferredSize(new Dimension(150, 40));
    }

    // Controller hooks
    public void addStartListener(ActionListener l) {
        startButton.addActionListener(l);
    }

    public void addRestartListener(ActionListener l) {
        restartButton.addActionListener(l);
    }

    public void addCellListener(int r, int c, ActionListener l) {
        cells[r][c].addActionListener(l);
    }

    // Update methods
    public void updateRemainingQueens(int rem) {
        remainingLabel.setText("Remaining Queens: " + rem);
    }

    public void showWinMessage(String playerName, long t) {
        JOptionPane.showMessageDialog(this,
                "Congratulations " + playerName + "!\n" +
                        "You placed all 8 queens safely.\n" +
                        "You won in " + t + " seconds!",
                "You Won!", JOptionPane.INFORMATION_MESSAGE
        );
    }

    public void showLossMessage(long t) {
        JOptionPane.showMessageDialog(this,
                "Game Over!\n" +
                        "A queen threatened another queen.\n" +
                        "You lost!\n" +
                        "Time spent: " + t + " seconds.",
                "You Lost!", JOptionPane.ERROR_MESSAGE
        );
    }
}


