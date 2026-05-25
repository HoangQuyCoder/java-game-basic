import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FlappyBirdController implements KeyListener, ActionListener {
    private final FlappyBirdModel model;
    private final FlappyBirdView view;

    private Timer gameLoop;
    private Timer placePipeTimer;

    public FlappyBirdController(FlappyBirdModel model, FlappyBirdView view) {
        this.model = model;
        this.view = view;

        initController();
    }

    private void initController() {
        view.addKeyListener(this);

        // place pipes timer (every 1500ms)
        placePipeTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.placePipes();
            }
        });

        // 60fps game loop timer
        gameLoop = new Timer(1000 / 60, this);
    }

    public void startGame() {
        placePipeTimer.start();
        gameLoop.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.move();
        view.repaint();
        if (model.isGameOver()) {
            placePipeTimer.stop();
            gameLoop.stop();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            model.setVelocityY(-9);

            if (model.isGameOver()) {
                model.reset();
                gameLoop.start();
                placePipeTimer.start();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
