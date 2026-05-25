import java.util.ArrayList;

public class MatchCardsModel {
    private final String[] cardNames = {
        "darkness", "double", "fairy", "fighting", "fire",
        "grass", "lightning", "metal", "psychic", "water"
    };

    private final int rows = 4;
    private final int columns = 5;
    private final int totalCards = rows * columns;

    private ArrayList<Card> cardSet;
    private boolean[] matched;
    private boolean[] revealed;

    private int card1Index = -1;
    private int card2Index = -1;

    private int errorCount = 0;
    private boolean gameReady = false;

    public MatchCardsModel() {
        cardSet = new ArrayList<>();
        matched = new boolean[totalCards];
        revealed = new boolean[totalCards];
    }

    public void resetGame() {
        // Build card set (10 card types, duplicated to make 20)
        cardSet = new ArrayList<>();
        for (String name : cardNames) {
            cardSet.add(new Card(name));
            cardSet.add(new Card(name));
        }

        shuffleCards();

        // Initially reveal all cards face-up
        for (int i = 0; i < totalCards; i++) {
            matched[i] = false;
            revealed[i] = true;
        }

        card1Index = -1;
        card2Index = -1;
        errorCount = 0;
        gameReady = false;
    }

    public void shuffleCards() {
        for (int i = 0; i < cardSet.size(); i++) {
            int j = (int) (Math.random() * cardSet.size());
            Card temp = cardSet.get(i);
            cardSet.set(i, cardSet.get(j));
            cardSet.set(j, temp);
        }
    }

    public boolean selectCard(int index) {
        if (!gameReady || matched[index] || index == card1Index || index == card2Index) {
            return false;
        }

        if (card1Index == -1) {
            card1Index = index;
            revealed[index] = true;
            return false;
        } else if (card2Index == -1) {
            card2Index = index;
            revealed[index] = true;

            // Check match using logical names
            if (cardSet.get(card1Index).getCardName().equals(cardSet.get(card2Index).getCardName())) {
                matched[card1Index] = true;
                matched[card2Index] = true;
                card1Index = -1;
                card2Index = -1;
                return false;
            } else {
                errorCount++;
                return true; // Indicates mismatch, timer should run
            }
        }

        return false;
    }

    public void hideSelectedCards() {
        if (card1Index != -1 && card2Index != -1) {
            revealed[card1Index] = false;
            revealed[card2Index] = false;
            card1Index = -1;
            card2Index = -1;
        }
    }

    public void hideAllCards() {
        for (int i = 0; i < totalCards; i++) {
            revealed[i] = false;
        }
        gameReady = true;
    }

    public void revealAllCards() {
        for (int i = 0; i < totalCards; i++) {
            revealed[i] = true;
        }
    }

    // Getters and Setters
    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getTotalCards() {
        return totalCards;
    }

    public Card getCard(int index) {
        return cardSet.get(index);
    }

    public boolean isMatched(int index) {
        return matched[index];
    }

    public boolean isRevealed(int index) {
        return revealed[index];
    }

    public int getCard1Index() {
        return card1Index;
    }

    public int getCard2Index() {
        return card2Index;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public boolean isGameReady() {
        return gameReady;
    }

    public void setGameReady(boolean gameReady) {
        this.gameReady = gameReady;
    }

    public ArrayList<Card> getCardSet() {
        return cardSet;
    }
}
