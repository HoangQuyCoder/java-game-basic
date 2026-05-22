import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WhacAMoleView {
    public interface TileClickListener {
        void onTileClicked(int index);
    }

    private final int boardWidth = 600;
    private final int boardHeight = 650;
    private JFrame frame;
    private JLabel textLabel;
    private JPanel textPanel;
    private JPanel boardPanel;
    private JButton[] board = new JButton[9];
    private ImageIcon moleIcon;
    private ImageIcon plantIcon;
    private TileClickListener tileClickListener;

    public WhacAMoleView() {
        initComponents();
    }

    private void initComponents() {
        frame = new JFrame("Mario: Whac A Mole");
        frame.setSize(boardWidth, boardHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        textLabel = new JLabel();
        textLabel.setFont(new Font("Arial", Font.PLAIN, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Score: 0");
        textLabel.setOpaque(true);
        textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));
        frame.add(boardPanel);
        // Load images
        Image plantImg = new ImageIcon(getClass().getResource("/img/piranha.png")).getImage();
        plantIcon = new ImageIcon(plantImg.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH));
        Image moleImg = new ImageIcon(getClass().getResource("/img/monty.png")).getImage();
        moleIcon = new ImageIcon(moleImg.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH));
        for (int i = 0; i < 9; i++) {
            final int index = i;
            JButton tile = new JButton();
            board[i] = tile;
            boardPanel.add(tile);
            tile.setFocusable(false);
            tile.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (tileClickListener != null) {
                        tileClickListener.onTileClicked(index);
                    }
                }
            });
        }
        frame.setVisible(true);
    }

    public void setTileClickListener(TileClickListener listener) {
        this.tileClickListener = listener;
    }

    public void updateScore(int score) {
        textLabel.setText("Score: " + score);
    }

    public void showGameOver(int finalScore) {
        textLabel.setText("Game over: " + finalScore);
    }

    public void setMoleIcon(int index) {
        if (index >= 0 && index < 9) {
            board[index].setIcon(moleIcon);
        }
    }

    public void setPlantIcon(int index) {
        if (index >= 0 && index < 9) {
            board[index].setIcon(plantIcon);
        }
    }

    public void clearTileIcon(int index) {
        if (index >= 0 && index < 9) {
            board[index].setIcon(null);
        }
    }

    public void disableAllTiles() {
        for (int i = 0; i < 9; i++) {
            board[i].setEnabled(false);
        }
    }

    public void enableAllTiles() {
        for (int i = 0; i < 9; i++) {
            board[i].setEnabled(true);
            board[i].setIcon(null);
        }
    }

    public void resetUI() {
        updateScore(0);
        enableAllTiles();
    }
}
