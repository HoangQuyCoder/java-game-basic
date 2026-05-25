import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

public class ChromeDinosaurController implements ActionListener, KeyListener {
    private ChromeDinosaurModel model;
    private ChromeDinosaurView view;

    private Timer gameLoop;
    private Timer placeCactusTimer;

    public ChromeDinosaurController(ChromeDinosaurModel model, ChromeDinosaurView view) {
        this.model = model;
        this.view = view;

        // Register controller listeners onto the View
        this.view.addKeyListener(this);
        this.view.setFocusable(true);

        // game loop timer (60 FPS)
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();

        // cactus placement spawner timer (1500ms)
        placeCactusTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.placeCactus();
                view.repaint();
            }
        });
        placeCactusTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.move();
        view.repaint();

        if (model.gameOver) {
            gameLoop.stop();
            placeCactusTimer.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (model.gameOver) {
                // restart game by resetting conditions in model and restarting loops
                model.resetGame();
                view.repaint();
                gameLoop.start();
                placeCactusTimer.start();
            } else {
                model.jump();
                view.repaint();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
