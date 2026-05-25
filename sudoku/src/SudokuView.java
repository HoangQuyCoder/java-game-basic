import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class SudokuView extends JPanel {
    public static class SudokuTile extends JButton {
        private final int r;
        private final int c;

        public SudokuTile(int r, int c) {
            this.r = r;
            this.c = c;
            setFocusable(false);
            setFocusPainted(false);
            setOpaque(true);
        }

        public int getRow() {
            return r;
        }

        public int getCol() {
            return c;
        }
    }

    private final JLabel textLabel = new JLabel();
    private final JPanel boardPanel = new JPanel();
    private final JPanel buttonsPanel = new JPanel();
    
    private final SudokuTile[][] tiles = new SudokuTile[9][9];
    private final JButton[] numButtons = new JButton[9];

    // Premium Color Palette
    private final Color COLOR_BG = new Color(245, 246, 248);
    private final Color COLOR_HEADER_BG = new Color(255, 255, 255);
    private final Color COLOR_TILE_EMPTY = new Color(255, 255, 255);
    private final Color COLOR_TILE_ORIGINAL = new Color(232, 236, 241);
    private final Color COLOR_TILE_TEXT_ORIGINAL = new Color(32, 33, 36);
    private final Color COLOR_TILE_TEXT_PLACED = new Color(26, 115, 232); // Premium Google Blue
    private final Color COLOR_GRID_BORDER = new Color(180, 184, 188);
    private final Color COLOR_GRID_SUBDIVIDER = new Color(32, 33, 36); // Dark color for 3x3 borders
    private final Color COLOR_SELECT_DEFAULT_BG = new Color(255, 255, 255);
    private final Color COLOR_SELECT_DEFAULT_TEXT = new Color(60, 64, 67);
    private final Color COLOR_SELECT_ACTIVE_BG = new Color(26, 115, 232);
    private final Color COLOR_SELECT_ACTIVE_TEXT = new Color(255, 255, 255);

    public SudokuView() {
        setPreferredSize(new Dimension(600, 650));
        setLayout(new BorderLayout());
        setBackground(COLOR_BG);

        // Header Panel
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBackground(COLOR_HEADER_BG);
        textPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(218, 220, 224)));
        textPanel.setPreferredSize(new Dimension(600, 60));

        textLabel.setText("Sudoku: 0");
        textLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        textLabel.setForeground(new Color(32, 33, 36));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textPanel.add(textLabel, BorderLayout.CENTER);

        // Board Panel
        boardPanel.setLayout(new GridLayout(9, 9));
        boardPanel.setBackground(COLOR_BG);
        boardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        initializeBoard();

        // Buttons Panel
        buttonsPanel.setLayout(new GridLayout(1, 9, 4, 0));
        buttonsPanel.setBackground(COLOR_HEADER_BG);
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        initializeNumbersPanel();

        add(textPanel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void initializeBoard() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                SudokuTile tile = new SudokuTile(r, c);
                tile.setFont(new Font("SansSerif", Font.BOLD, 20));
                
                // Border design to delineate 3x3 blocks
                int top = 1, left = 1, bottom = 1, right = 1;
                
                // Outer boundaries of the 9x9 grid
                if (r == 0) top = 2;
                if (c == 0) left = 2;
                if (r == 8) bottom = 2;
                if (c == 8) right = 2;
                
                // Inner 3x3 grid boundaries (thick dividers)
                if (r == 2 || r == 5) bottom = 4;
                if (c == 2 || c == 5) right = 4;
                if (r == 3 || r == 6) top = 4;
                if (c == 3 || c == 6) left = 4;

                Border cellBorder = BorderFactory.createMatteBorder(
                    top, left, bottom, right, 
                    (top >= 4 || left >= 4 || bottom >= 4 || right >= 4) ? COLOR_GRID_SUBDIVIDER : COLOR_GRID_BORDER
                );
                tile.setBorder(cellBorder);
                
                tiles[r][c] = tile;
                boardPanel.add(tile);
            }
        }
    }

    private void initializeNumbersPanel() {
        for (int i = 0; i < 9; i++) {
            JButton btn = new JButton(String.valueOf(i + 1));
            btn.setFont(new Font("SansSerif", Font.BOLD, 18));
            btn.setFocusable(false);
            btn.setFocusPainted(false);
            btn.setOpaque(true);
            btn.setBackground(COLOR_SELECT_DEFAULT_BG);
            btn.setForeground(COLOR_SELECT_DEFAULT_TEXT);
            btn.setBorder(BorderFactory.createLineBorder(new Color(218, 220, 224), 1));
            numButtons[i] = btn;
            buttonsPanel.add(btn);
        }
    }

    public void setBoardTileListener(ActionListener listener) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                tiles[r][c].addActionListener(listener);
            }
        }
    }

    public void setNumberButtonListener(ActionListener listener) {
        for (int i = 0; i < 9; i++) {
            numButtons[i].addActionListener(listener);
        }
    }

    public void updateTile(int r, int c, char val, boolean isOriginal) {
        SudokuTile tile = tiles[r][c];
        if (val == '-') {
            tile.setText("");
            tile.setBackground(COLOR_TILE_EMPTY);
        } else {
            tile.setText(String.valueOf(val));
            if (isOriginal) {
                tile.setBackground(COLOR_TILE_ORIGINAL);
                tile.setForeground(COLOR_TILE_TEXT_ORIGINAL);
            } else {
                tile.setBackground(COLOR_TILE_EMPTY);
                tile.setForeground(COLOR_TILE_TEXT_PLACED);
            }
        }
    }

    public void selectNumberButton(int selectedNum) {
        for (int i = 0; i < 9; i++) {
            int buttonVal = i + 1;
            if (buttonVal == selectedNum) {
                numButtons[i].setBackground(COLOR_SELECT_ACTIVE_BG);
                numButtons[i].setForeground(COLOR_SELECT_ACTIVE_TEXT);
            } else {
                numButtons[i].setBackground(COLOR_SELECT_DEFAULT_BG);
                numButtons[i].setForeground(COLOR_SELECT_DEFAULT_TEXT);
            }
        }
    }

    public void updateErrors(int errorCount) {
        if (errorCount > 0) {
            textLabel.setText("Sudoku: " + errorCount + " Error" + (errorCount > 1 ? "s" : ""));
            textLabel.setForeground(new Color(217, 48, 37)); // Premium Red
        } else {
            textLabel.setText("Sudoku: 0");
            textLabel.setForeground(new Color(32, 33, 36));
        }
    }

    public void showGameSolved() {
        textLabel.setText("🎉 Sudoku Hoàn Thành!");
        textLabel.setForeground(new Color(30, 142, 62)); // Premium Green
    }
}
