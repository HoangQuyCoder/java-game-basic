import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MatchCardsView {
    private final JFrame frame;
    private final JLabel textLabel;
    private final JPanel textJPanel;
    private final JPanel boardJPanel;
    private final JPanel restartGamePanel;
    private final JButton restartButton;
    private final ArrayList<JButton> boardTiles;

    private final int cardWidth = 90;
    private final int cardHeight = 128;
    private final int boardWidth;
    private final int boardHeight;

    private final MatchCardsModel model;

    // Cache preloaded scaled image icons
    private final Map<String, ImageIcon> cardImageIcons;
    private ImageIcon cardBackImageIcon;

    public MatchCardsView(MatchCardsModel model) {
        this.model = model;
        this.boardWidth = model.getColumns() * cardWidth;
        this.boardHeight = model.getRows() * cardHeight;

        cardImageIcons = new HashMap<>();
        loadAndScaleAssets();

        // 1. JFrame setup
        frame = new JFrame("Pokemon Match Cards");
        frame.setResizable(false);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // 2. Error status text panel at North
        textLabel = new JLabel("Error: 0");
        textLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        textLabel.setHorizontalAlignment(JLabel.CENTER);

        textJPanel = new JPanel();
        textJPanel.setPreferredSize(new Dimension(boardWidth, 30));
        textJPanel.add(textLabel);
        frame.add(textJPanel, BorderLayout.NORTH);

        // 3. Grid board in Center
        boardTiles = new ArrayList<>();
        boardJPanel = new JPanel();
        boardJPanel.setLayout(new GridLayout(model.getRows(), model.getColumns()));

        for (int i = 0; i < model.getTotalCards(); i++) {
            JButton tile = new JButton();
            tile.setPreferredSize(new Dimension(cardWidth, cardHeight));
            tile.setOpaque(true);
            tile.setFocusable(false);
            // Initially set it to back card or front depending on state
            tile.setIcon(cardBackImageIcon);
            boardTiles.add(tile);
            boardJPanel.add(tile);
        }
        frame.add(boardJPanel, BorderLayout.CENTER);

        // 4. Restart panel at South
        restartButton = new JButton("Restart Game");
        restartButton.setFont(new Font("Arial", Font.PLAIN, 16));
        restartButton.setFocusable(false);
        restartButton.setPreferredSize(new Dimension(boardWidth, 30));
        restartButton.setEnabled(false);

        restartGamePanel = new JPanel();
        restartGamePanel.add(restartButton);
        frame.add(restartGamePanel, BorderLayout.SOUTH);

        frame.pack(); // Adjust window sizing perfectly
    }

    private void loadAndScaleAssets() {
        try {
            // Load and scale front card assets
            String[] cardList = {
                    "darkness", "double", "fairy", "fighting", "fire",
                    "grass", "lightning", "metal", "psychic", "water"
            };

            for (String name : cardList) {
                java.net.URL imgUrl = getClass().getResource("/images/" + name + ".jpg");
                if (imgUrl != null) {
                    Image img = new ImageIcon(imgUrl).getImage();
                    Image scaledImg = img.getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH);
                    cardImageIcons.put(name, new ImageIcon(scaledImg));
                }
            }

            // Load and scale back card asset
            java.net.URL backUrl = getClass().getResource("/images/back.jpg");
            if (backUrl != null) {
                Image backImg = new ImageIcon(backUrl).getImage();
                Image scaledBackImg = backImg.getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH);
                cardBackImageIcon = new ImageIcon(scaledBackImg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void show() {
        frame.setVisible(true);
        updateView();
    }

    public void setTileListeners(ArrayList<ActionListener> listeners) {
        for (int i = 0; i < boardTiles.size(); i++) {
            boardTiles.get(i).addActionListener(listeners.get(i));
        }
    }

    public void setRestartListener(ActionListener listener) {
        restartButton.addActionListener(listener);
    }

    public void updateView() {
        // 1. Update text label score
        textLabel.setText("Error: " + model.getErrorCount());

        // 2. Update all grid tiles
        for (int i = 0; i < boardTiles.size(); i++) {
            JButton tile = boardTiles.get(i);
            if (model.isRevealed(i)) {
                String cardName = model.getCard(i).getCardName();
                tile.setIcon(cardImageIcons.get(cardName));
            } else {
                tile.setIcon(cardBackImageIcon);
            }
        }

        // 3. Update restart button enabled state
        restartButton.setEnabled(model.isGameReady());
    }
}
