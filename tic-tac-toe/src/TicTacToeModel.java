public class TicTacToeModel {
    public static class WinResult {
        public enum Status { ACTIVE, WIN, TIE }
        private final Status status;
        private final int[][] winningCoords; // e.g. {{0,0}, {0,1}, {0,2}}
        
        public WinResult(Status status, int[][] winningCoords) {
            this.status = status;
            this.winningCoords = winningCoords;
        }
        
        public Status getStatus() { return status; }
        public int[][] getWinningCoords() { return winningCoords; }
    }

    private String[][] boardState = new String[3][3];
    private String currentPlayer;
    private boolean gameOver;
    private int turns;

    public TicTacToeModel() {
        reset();
    }

    public void reset() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                boardState[r][c] = "";
            }
        }
        this.currentPlayer = "X";
        this.gameOver = false;
        this.turns = 0;
    }

    public String getCell(int r, int c) {
        if (r >= 0 && r < 3 && c >= 0 && c < 3) {
            return boardState[r][c];
        }
        return "";
    }

    public void setCell(int r, int c, String val) {
        if (r >= 0 && r < 3 && c >= 0 && c < 3) {
            boardState[r][c] = val;
        }
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchPlayer() {
        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int getTurns() {
        return turns;
    }

    public void incrementTurns() {
        turns++;
    }

    public WinResult checkWinOrTie() {
        // horizontal check
        for (int r = 0; r < 3; r++) {
            if (!boardState[r][0].equals("") &&
                boardState[r][0].equals(boardState[r][1]) &&
                boardState[r][1].equals(boardState[r][2])) {
                return new WinResult(WinResult.Status.WIN, new int[][]{{r, 0}, {r, 1}, {r, 2}});
            }
        }

        // vertical check
        for (int c = 0; c < 3; c++) {
            if (!boardState[0][c].equals("") &&
                boardState[0][c].equals(boardState[1][c]) &&
                boardState[1][c].equals(boardState[2][c])) {
                return new WinResult(WinResult.Status.WIN, new int[][]{{0, c}, {1, c}, {2, c}});
            }
        }

        // diagonal check (top-left to bottom-right)
        if (!boardState[0][0].equals("") &&
            boardState[0][0].equals(boardState[1][1]) &&
            boardState[1][1].equals(boardState[2][2])) {
            return new WinResult(WinResult.Status.WIN, new int[][]{{0, 0}, {1, 1}, {2, 2}});
        }

        // anti-diagonal check (top-right to bottom-left)
        if (!boardState[0][2].equals("") &&
            boardState[0][2].equals(boardState[1][1]) &&
            boardState[1][1].equals(boardState[2][0])) {
            return new WinResult(WinResult.Status.WIN, new int[][]{{0, 2}, {1, 1}, {2, 0}});
        }

        // Tie check
        if (turns == 9) {
            return new WinResult(WinResult.Status.TIE, null);
        }

        return new WinResult(WinResult.Status.ACTIVE, null);
    }
}
