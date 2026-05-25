import java.util.ArrayList;
import java.util.Random;

public class BlackJackModel {
    private ArrayList<Card> deck;
    private final Random random = new Random();

    // dealer
    private Card hiddenCard;
    private ArrayList<Card> dealerHand;
    private int dealerSum;
    private int dealerAceCount;

    // player
    private ArrayList<Card> playerHand;
    private int playerSum;
    private int playerAceCount;

    private boolean stayPressed = false;

    public BlackJackModel() {
        deck = new ArrayList<>();
        dealerHand = new ArrayList<>();
        playerHand = new ArrayList<>();
    }

    public void startGame() {
        buildDeck();
        shuffleDeck();

        dealerHand = new ArrayList<>();
        dealerSum = 0;
        dealerAceCount = 0;

        hiddenCard = deck.remove(deck.size() - 1);
        dealerSum += hiddenCard.getValue();
        dealerAceCount += hiddenCard.isAce() ? 1 : 0;

        Card card = deck.remove(deck.size() - 1);
        dealerSum += card.getValue();
        dealerAceCount += card.isAce() ? 1 : 0;
        dealerHand.add(card);

        playerHand = new ArrayList<>();
        playerSum = 0;
        playerAceCount = 0;

        for (int i = 0; i < 2; i++) {
            card = deck.remove(deck.size() - 1);
            playerSum += card.getValue();
            playerAceCount += card.isAce() ? 1 : 0;
            playerHand.add(card);
        }

        stayPressed = false;
    }

    public void buildDeck() {
        deck = new ArrayList<>();
        String[] values = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
        String[] types = { "C", "D", "H", "S" };

        for (String value : values) {
            for (String type : types) {
                deck.add(new Card(value, type));
            }
        }
    }

    public void shuffleDeck() {
        for (int i = 0; i < deck.size(); i++) {
            int j = random.nextInt(deck.size());
            Card currCard = deck.get(i);
            Card randomCard = deck.get(j);
            deck.set(i, randomCard);
            deck.set(j, currCard);
        }
    }

    public void hitPlayer() {
        if (deck.isEmpty()) return;
        Card card = deck.remove(deck.size() - 1);
        playerSum += card.getValue();
        playerAceCount += card.isAce() ? 1 : 0;
        playerHand.add(card);
    }

    public void dealerPlay() {
        while (reduceDealerAce() < 17) {
            if (deck.isEmpty()) break;
            Card card = deck.remove(deck.size() - 1);
            dealerSum += card.getValue();
            dealerAceCount += card.isAce() ? 1 : 0;
            dealerHand.add(card);
        }
    }

    public int reducePlayerAce() {
        while (playerSum > 21 && playerAceCount > 0) {
            playerSum -= 10;
            playerAceCount -= 1;
        }
        return playerSum;
    }

    public int reduceDealerAce() {
        while (dealerSum > 21 && dealerAceCount > 0) {
            dealerSum -= 10;
            dealerAceCount -= 1;
        }
        return dealerSum;
    }

    // Getters and setters
    public ArrayList<Card> getDealerHand() {
        return dealerHand;
    }

    public Card getHiddenCard() {
        return hiddenCard;
    }

    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    public int getPlayerSum() {
        return reducePlayerAce();
    }

    public int getDealerSum() {
        return reduceDealerAce();
    }

    public boolean isStayPressed() {
        return stayPressed;
    }

    public void setStayPressed(boolean stayPressed) {
        this.stayPressed = stayPressed;
    }

    public String determineWinner() {
        int finalPlayerSum = getPlayerSum();
        int finalDealerSum = getDealerSum();

        if (finalPlayerSum > 21) {
            return "You Lose!";
        } else if (finalDealerSum > 21) {
            return "You Win!";
        } else if (finalPlayerSum == finalDealerSum) {
            return "Tie!";
        } else if (finalPlayerSum > finalDealerSum) {
            return "You Win!";
        } else {
            return "You Lose!";
        }
    }
}
