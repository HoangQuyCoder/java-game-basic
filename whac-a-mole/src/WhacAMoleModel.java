public class WhacAMoleModel {
    public static final int NUM_TILES = 9;

    private int score;
    private boolean gameOver;
    private int moleIndex; // -1 means no mole
    private int plantIndex; // -1 means no plant

    public WhacAMoleModel() {
        reset();
    }

    public void reset() {
        this.score = 0;
        this.gameOver = false;
        this.moleIndex = -1;
        this.plantIndex = -1;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        this.score += points;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int getMoleIndex() {
        return moleIndex;
    }

    public void setMoleIndex(int index) {
        this.moleIndex = index;
    }

    public int getPlantIndex() {
        return plantIndex;
    }

    public void setPlantIndex(int index) {
        this.plantIndex = index;
    }

    public boolean isMole(int index) {
        return this.moleIndex == index;
    }

    public boolean isPlant(int index) {
        return this.plantIndex == index;
    }
}