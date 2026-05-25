import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlackJackController {
    private final BlackJackModel model;
    private final BlackJackView view;

    public BlackJackController(BlackJackModel model, BlackJackView view) {
        this.model = model;
        this.view = view;

        // Bind button actions in View to our Listeners
        this.view.setButtonListeners(new HitListener(), new StayListener());

        // Launch the initial game state
        this.model.startGame();
        this.view.show();
    }

    private class HitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.hitPlayer();
            if (model.getPlayerSum() > 21) {
                // Player busted, end game immediately
                view.setButtonsEnabled(false, false);
                model.setStayPressed(true);
            }
            view.repaint();
        }
    }

    private class StayListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Player stands, disable buttons and run dealer play loop
            view.setButtonsEnabled(false, false);
            model.setStayPressed(true);
            model.dealerPlay();
            view.repaint();
        }
    }
}
