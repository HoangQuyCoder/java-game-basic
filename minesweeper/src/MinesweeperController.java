import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MinesweeperController {
    private final MinesweeperModel model;
    private final MinesweeperView view;

    public MinesweeperController(MinesweeperModel model, MinesweeperView view) {
        this.model = model;
        this.view = view;

        // Register MouseListeners on all 8x8 buttons
        for (int r = 0; r < model.getNumRows(); r++) {
            for (int c = 0; c < model.getNumCols(); c++) {
                final int row = r;
                final int col = c;
                this.view.setTileMouseListener(row, col, new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        handleMousePress(row, col, e);
                    }
                });
            }
        }

        // Initialize and launch game
        startGame();
    }

    private void startGame() {
        model.startGame();
        view.show();
    }

    private void handleMousePress(int r, int c, MouseEvent e) {
        if (model.isGameOver()) {
            return;
        }

        boolean stateChanged = false;

        // Left Click: Reveal tile
        if (e.getButton() == MouseEvent.BUTTON1) {
            stateChanged = model.clickTile(r, c);
        }
        // Right Click: Toggle flag
        else if (e.getButton() == MouseEvent.BUTTON3) {
            stateChanged = model.toggleFlag(r, c);
        }

        if (stateChanged) {
            view.updateView();
        }
    }
}
