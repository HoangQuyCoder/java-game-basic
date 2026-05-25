import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BlackJackView {
    private final JFrame frame;
    private final JPanel gamePanel;
    private final JPanel buttonPanel;
    private final JButton hitButton;
    private final JButton stayButton;

    private final int boardWidth = 600;
    private final int boardHeight = 600;
    private final int cardWidth = 110;
    private final int cardHeight = 154;

    private final BlackJackModel model;

    public BlackJackView(BlackJackModel model) {
        this.model = model;

        frame = new JFrame("Black Jack");
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGame(g);
            }
        };
        gamePanel.setLayout(new BorderLayout());
        gamePanel.setBackground(new Color(53, 101, 77));
        frame.add(gamePanel);

        buttonPanel = new JPanel();
        hitButton = new JButton("Hit");
        stayButton = new JButton("Stay");

        hitButton.setFocusable(false);
        stayButton.setFocusable(true);

        buttonPanel.add(hitButton);
        buttonPanel.add(stayButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void show() {
        frame.setVisible(true);
        repaint();
    }

    public void setButtonListeners(ActionListener hitListener, ActionListener stayListener) {
        hitButton.addActionListener(hitListener);
        stayButton.addActionListener(stayListener);
    }

    public void setButtonsEnabled(boolean hitEnabled, boolean stayEnabled) {
        hitButton.setEnabled(hitEnabled);
        stayButton.setEnabled(stayEnabled);
    }

    public void repaint() {
        gamePanel.repaint();
    }

    private void drawGame(Graphics g) {
        try {
            // Draw hidden card
            Image hiddenCardImg;
            if (!model.isStayPressed()) {
                hiddenCardImg = new ImageIcon(getClass().getResource("/cards/BACK.png")).getImage();
            } else {
                Card hiddenCard = model.getHiddenCard();
                hiddenCardImg = new ImageIcon(getClass().getResource("/cards/" + hiddenCard.toString() + ".png"))
                        .getImage();
            }
            g.drawImage(hiddenCardImg, 20, 20, cardWidth, cardHeight, null);

            // Draw dealer's hand
            java.util.ArrayList<Card> dealerHand = model.getDealerHand();
            for (int i = 0; i < dealerHand.size(); i++) {
                Card card = dealerHand.get(i);
                Image cardImg = new ImageIcon(getClass().getResource("/cards/" + card.toString() + ".png")).getImage();
                g.drawImage(cardImg, cardWidth + 25 + (cardWidth + 5) * i, 20, cardWidth, cardHeight, null);
            }

            // Draw player's hand
            java.util.ArrayList<Card> playerHand = model.getPlayerHand();
            for (int i = 0; i < playerHand.size(); i++) {
                Card card = playerHand.get(i);
                Image cardImg = new ImageIcon(getClass().getResource("/cards/" + card.toString() + ".png")).getImage();
                g.drawImage(cardImg, 20 + (cardWidth + 5) * i, 320, cardWidth, cardHeight, null);
            }

            // Draw game over message
            if (model.isStayPressed() || model.getPlayerSum() > 21) {
                String message = model.determineWinner();
                g.setFont(new Font("Arial", Font.PLAIN, 30));
                g.setColor(Color.white);
                g.drawString(message, 220, 250);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
