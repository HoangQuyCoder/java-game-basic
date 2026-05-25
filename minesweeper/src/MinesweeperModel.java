import java.util.Random;

public class MinesweeperModel {
    private final int numRows = 8;
    private final int numCols = 8;
    private final int mineCount = 10;

    private boolean[][] mines;
    private boolean[][] revealed;
    private boolean[][] flagged;
    private int[][] surroundingMines;

    private int tilesClicked = 0;
    private boolean gameOver = false;
    private boolean gameWon = false;

    private final Random random = new Random();

    public MinesweeperModel() {
        mines = new boolean[numRows][numCols];
        revealed = new boolean[numRows][numCols];
        flagged = new boolean[numRows][numCols];
        surroundingMines = new int[numRows][numCols];
    }

    public void startGame() {
        mines = new boolean[numRows][numCols];
        revealed = new boolean[numRows][numCols];
        flagged = new boolean[numRows][numCols];
        surroundingMines = new int[numRows][numCols];

        tilesClicked = 0;
        gameOver = false;
        gameWon = false;

        // Place mines randomly and uniquely
        int mineLeft = mineCount;
        while (mineLeft > 0) {
            int r = random.nextInt(numRows);
            int c = random.nextInt(numCols);

            if (!mines[r][c]) {
                mines[r][c] = true;
                mineLeft--;
            }
        }

        // Calculate surrounding mine counts
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                if (mines[r][c]) {
                    surroundingMines[r][c] = 0;
                    continue;
                }

                int count = 0;
                for (int dr = -1; dr <= 1; dr++) {
                    for (int dc = -1; dc <= 1; dc++) {
                        int nr = r + dr;
                        int nc = c + dc;
                        if (nr >= 0 && nr < numRows && nc >= 0 && nc < numCols && mines[nr][nc]) {
                            count++;
                        }
                    }
                }
                surroundingMines[r][c] = count;
            }
        }
    }

    public boolean clickTile(int r, int c) {
        if (gameOver || gameWon || r < 0 || r >= numRows || c < 0 || c >= numCols || revealed[r][c] || flagged[r][c]) {
            return false;
        }

        revealed[r][c] = true;
        tilesClicked++;

        if (mines[r][c]) {
            gameOver = true;
            revealAllMines();
            return true;
        }

        if (surroundingMines[r][c] == 0) {
            floodFill(r, c);
        }

        if (tilesClicked == numRows * numCols - mineCount) {
            gameWon = true;
            gameOver = true;
        }

        return true;
    }

    private void floodFill(int r, int c) {
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue;
                int nr = r + dr;
                int nc = c + dc;
                if (nr >= 0 && nr < numRows && nc >= 0 && nc < numCols) {
                    if (!revealed[nr][nc] && !flagged[nr][nc]) {
                        revealed[nr][nc] = true;
                        tilesClicked++;
                        if (surroundingMines[nr][nc] == 0) {
                            floodFill(nr, nc);
                        }
                    }
                }
            }
        }
    }

    public boolean toggleFlag(int r, int c) {
        if (gameOver || gameWon || r < 0 || r >= numRows || c < 0 || c >= numCols || revealed[r][c]) {
            return false;
        }

        flagged[r][c] = !flagged[r][c];
        return true;
    }

    private void revealAllMines() {
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                if (mines[r][c]) {
                    revealed[r][c] = true;
                }
            }
        }
    }

    // Getters
    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public int getMineCount() {
        return mineCount;
    }

    public boolean isMine(int r, int c) {
        return mines[r][c];
    }

    public boolean isRevealed(int r, int c) {
        return revealed[r][c];
    }

    public boolean isFlagged(int r, int c) {
        return flagged[r][c];
    }

    public int getSurroundingMines(int r, int c) {
        return surroundingMines[r][c];
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public int getTilesClicked() {
        return tilesClicked;
    }
}
