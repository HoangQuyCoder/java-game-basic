import java.awt.Color;

public class TicTacToeController {
    private final TicTacToeModel model;
    private final TicTacToeView view;

    public TicTacToeController(TicTacToeModel model, TicTacToeView view) {
        this.model = model;
        this.view = view;

        initController();
    }

    private void initController() {
        view.setTileClickListener(new TicTacToeView.TileClickListener() {
            @Override
            public void onTileClicked(int r, int c) {
                handleTileClick(r, c);
            }
        });
    }

    public void startGame() {
        model.reset();
        view.resetUI();
    }

    private void handleTileClick(int r, int c) {
        if (model.isGameOver()) {
            return;
        }

        // Only place marker if cell is empty
        if (model.getCell(r, c).equals("")) {
            String player = model.getCurrentPlayer();

            // Update state
            model.setCell(r, c, player);
            view.updateCellText(r, c, player);
            model.incrementTurns();

            // Check game state
            TicTacToeModel.WinResult result = model.checkWinOrTie();

            if (result.getStatus() == TicTacToeModel.WinResult.Status.WIN) {
                model.setGameOver(true);
                view.highlightWinningTiles(result.getWinningCoords(), Color.gray, Color.green);
                view.setStatusText(player + " is the winner!");
            } else if (result.getStatus() == TicTacToeModel.WinResult.Status.TIE) {
                model.setGameOver(true);
                view.highlightTie(Color.gray, Color.darkGray);
                view.setStatusText("Tie!");
            } else {
                // Switch turn
                model.switchPlayer();
                view.setStatusText(model.getCurrentPlayer() + "'s turn.");
            }
        }
    }
}
