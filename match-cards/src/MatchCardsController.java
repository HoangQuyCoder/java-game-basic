import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MatchCardsController {
    private final MatchCardsModel model;
    private final MatchCardsView view;
    private final Timer hideCardTimer;

    public MatchCardsController(MatchCardsModel model, MatchCardsView view) {
        this.model = model;
        this.view = view;

        // 1. Instantiate the 1.5-second flip timer
        hideCardTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleTimerTick();
            }
        });
        hideCardTimer.setRepeats(false);

        // 2. Wire tile listeners
        ArrayList<ActionListener> tileListeners = new ArrayList<>();
        for (int i = 0; i < model.getTotalCards(); i++) {
            final int index = i;
            tileListeners.add(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleTileClick(index);
                }
            });
        }
        this.view.setTileListeners(tileListeners);

        // 3. Wire restart listener
        this.view.setRestartListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRestart();
            }
        });

        // 4. Start the initial game state
        startGame();
    }

    private void startGame() {
        model.resetGame();
        view.show();
        hideCardTimer.start();
    }

    private void handleTileClick(int index) {
        if (!model.isGameReady()) {
            return;
        }

        // Only allow clicking if we don't already have a pending mismatch flip
        if (model.getCard1Index() != -1 && model.getCard2Index() != -1) {
            return;
        }

        boolean mismatchOccurred = model.selectCard(index);
        view.updateView();

        if (mismatchOccurred) {
            hideCardTimer.start();
        }
    }

    private void handleRestart() {
        if (!model.isGameReady()) {
            return;
        }

        model.resetGame();
        view.updateView();
        hideCardTimer.start();
    }

    private void handleTimerTick() {
        if (model.getCard1Index() != -1 && model.getCard2Index() != -1) {
            // Hides the selected mismatching card pair
            model.hideSelectedCards();
        } else {
            // Initial/Restart timer: Hides all cards face-down
            model.hideAllCards();
        }
        view.updateView();
    }
}
