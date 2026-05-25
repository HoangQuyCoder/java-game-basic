public class SudokuModel {
    private final String[] puzzle = {
            "--74916-5",
            "2---6-3-9",
            "-----7-1-",
            "-586----4",
            "--3----9-",
            "--62--187",
            "9-4-7---2",
            "67-83----",
            "81--45---"
    };

    private final String[] solution = {
            "387491625",
            "241568379",
            "569327418",
            "758619234",
            "123784596",
            "496253187",
            "934176852",
            "675832941",
            "812945763"
    };

    private char[][] currentBoard;
    private int errors;
    private int selectedNumber;

    public SudokuModel() {
        resetGame();
    }

    public void resetGame() {
        currentBoard = new char[9][9];
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                currentBoard[r][c] = puzzle[r].charAt(c);
            }
        }
        errors = 0;
        selectedNumber = -1;
    }

    public char getCell(int r, int c) {
        return currentBoard[r][c];
    }

    public boolean isOriginalCell(int r, int c) {
        return puzzle[r].charAt(c) != '-';
    }

    public int getErrors() {
        return errors;
    }

    public int getSelectedNumber() {
        return selectedNumber;
    }

    public void setSelectedNumber(int num) {
        this.selectedNumber = num;
    }

    public boolean makeMove(int r, int c) {
        if (selectedNumber == -1) {
            return false;
        }
        if (currentBoard[r][c] != '-') {
            return false;
        }

        char solChar = solution[r].charAt(c);
        char inputChar = (char) ('0' + selectedNumber);

        if (solChar == inputChar) {
            currentBoard[r][c] = inputChar;
            return true;
        } else {
            errors++;
            return false;
        }
    }

    public boolean isSolved() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (currentBoard[r][c] != solution[r].charAt(c)) {
                    return false;
                }
            }
        }
        return true;
    }
}
