import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class MinesweeperView {
    private final JFrame frame;
    private final JPanel textPanel;
    private final JLabel textLabel;
    private final JPanel boardPanel;
    private final JButton[][] boardTiles;

    private final int tileSize = 70;
    private final int boardWidth;
    private final int boardHeight;

    private final MinesweeperModel model;

    public MinesweeperView(MinesweeperModel model) {
        this.model = model;
        this.boardWidth = model.getNumCols() * tileSize;
        this.boardHeight = model.getNumRows() * tileSize;

        frame = new JFrame("Minesweeper");
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Text HUD at the top
        textLabel = new JLabel();
        textLabel.setFont(new Font("Arial", Font.BOLD, 25));
        textLabel.setText("Minesweeper: " + model.getMineCount());
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setOpaque(true);

        textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        // Grid board in the center
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(model.getNumRows(), model.getNumCols()));
        frame.add(boardPanel, BorderLayout.CENTER);

        // Build JButtons grid
        boardTiles = new JButton[model.getNumRows()][model.getNumCols()];
        for (int r = 0; r < model.getNumRows(); r++) {
            for (int c = 0; c < model.getNumCols(); c++) {
                JButton tile = new JButton();
                tile.setFocusable(false);
                tile.setFont(new Font("Arial Unicode MS", Font.PLAIN, 45));
                tile.setMargin(new Insets(0, 0, 0, 0));
                boardTiles[r][c] = tile;
                boardPanel.add(tile);
            }
        }
    }

    public void show() {
        frame.setVisible(true);
        updateView();
    }

    public void setTileMouseListener(int r, int c, MouseListener listener) {
        boardTiles[r][c].addMouseListener(listener);
    }

    public void updateView() {
        // Update Text HUD
        if (model.isGameOver() && model.isGameWon()) {
            textLabel.setText("Mines Cleared!");
        } else if (model.isGameOver()) {
            textLabel.setText("Game Over");
        } else {
            textLabel.setText("Minesweeper: " + model.getMineCount());
        }

        // Update all tiles
        for (int r = 0; r < model.getNumRows(); r++) {
            for (int c = 0; c < model.getNumCols(); c++) {
                JButton tile = boardTiles[r][c];

                if (model.isRevealed(r, c)) {
                    tile.setEnabled(false);
                    if (model.isMine(r, c)) {
                        tile.setText("💣");
                    } else {
                        int count = model.getSurroundingMines(r, c);
                        if (count > 0) {
                            tile.setText(String.valueOf(count));
                        } else {
                            tile.setText("");
                        }
                    }
                } else {
                    tile.setEnabled(true);
                    if (model.isFlagged(r, c)) {
                        tile.setText("🚩");
                    } else {
                        tile.setText("");
                    }
                }
            }
        }
    }
}
