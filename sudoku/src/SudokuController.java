import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class SudokuController {
    private final SudokuModel model;
    private final SudokuView view;

    public SudokuController(SudokuModel model, SudokuView view) {
        this.model = model;
        this.view = view;

        // Register Action Listeners
        this.view.setBoardTileListener(new BoardTileListener());
        this.view.setNumberButtonListener(new NumberButtonListener());

        // Initialize view with model state
        syncViewWithModel();
    }

    private void syncViewWithModel() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char val = model.getCell(r, c);
                boolean isOriginal = model.isOriginalCell(r, c);
                view.updateTile(r, c, val, isOriginal);
            }
        }
        view.updateErrors(model.getErrors());
        view.selectNumberButton(model.getSelectedNumber());
    }

    private class NumberButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            try {
                int num = Integer.parseInt(source.getText());
                model.setSelectedNumber(num);
                view.selectNumberButton(num);
            } catch (NumberFormatException ex) {
                // Ignore invalid parses if any
            }
        }
    }

    private class BoardTileListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SudokuView.SudokuTile tile = (SudokuView.SudokuTile) e.getSource();
            int r = tile.getRow();
            int c = tile.getCol();

            // Ignore clicks on original pre-loaded cells
            if (model.isOriginalCell(r, c)) {
                return;
            }

            // Ignore clicks if no number is currently selected
            if (model.getSelectedNumber() == -1) {
                return;
            }

            // Make the move in the model and check if it was valid
            boolean correct = model.makeMove(r, c);
            if (correct) {
                char placedValue = model.getCell(r, c);
                view.updateTile(r, c, placedValue, false);
                
                if (model.isSolved()) {
                    view.showGameSolved();
                }
            } else {
                view.updateErrors(model.getErrors());
            }
        }
    }
}
