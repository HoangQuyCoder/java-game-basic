import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeView {
    public interface TileClickListener {
        void onTileClicked(int r, int c);
    }

    private final int boardWidth = 600;
    private final int boardHeight = 650;

    private JFrame frame;
    private JLabel textLabel;
    private JPanel texJPanel;
    private JPanel boardPanel;
    private JButton[][] board = new JButton[3][3];

    private TileClickListener tileClickListener;

    public TicTacToeView() {
        initComponents();
    }

    private void initComponents() {
        frame = new JFrame("Tic-Tac-Toe");
        frame.setSize(boardWidth, boardHeight);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        textLabel = new JLabel();
        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        texJPanel = new JPanel();
        texJPanel.setLayout(new BorderLayout());
        texJPanel.add(textLabel);
        frame.add(texJPanel, BorderLayout.NORTH);

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                final int row = r;
                final int col = c;
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                tile.setOpaque(true);
                tile.setBorder(BorderFactory.createLineBorder(Color.gray));

                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (tileClickListener != null) {
                            tileClickListener.onTileClicked(row, col);
                        }
                    }
                });
            }
        }

        frame.setVisible(true);
    }

    public void setTileClickListener(TileClickListener listener) {
        this.tileClickListener = listener;
    }

    public void updateCellText(int r, int c, String text) {
        if (r >= 0 && r < 3 && c >= 0 && c < 3) {
            board[r][c].setText(text);
        }
    }

    public void highlightWinningTiles(int[][] coords, Color bgColor, Color fgColor) {
        if (coords != null) {
            for (int[] coord : coords) {
                int r = coord[0];
                int c = coord[1];
                board[r][c].setBackground(bgColor);
                board[r][c].setForeground(fgColor);
            }
        }
    }

    public void highlightTie(Color bgColor, Color fgColor) {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c].setBackground(bgColor);
                board[r][c].setForeground(fgColor);
            }
        }
    }

    public void setStatusText(String text) {
        textLabel.setText(text);
    }

    public void disableAllTiles() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c].setEnabled(false);
            }
        }
    }

    public void enableAllTiles() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c].setEnabled(true);
                board[r][c].setText("");
                board[r][c].setBackground(Color.darkGray);
                board[r][c].setForeground(Color.white);
            }
        }
    }

    public void resetUI() {
        setStatusText("Tic-Tac-Toe");
        enableAllTiles();
    }
}
