import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

public class SnakeGameController implements ActionListener, KeyListener {
    private SnakeGameModel model;
    private SnakeGameView view;

    private Timer gameLoop;

    public SnakeGameController(SnakeGameModel model, SnakeGameView view) {
        this.model = model;
        this.view = view;

        // Register controller listeners onto the View
        this.view.addKeyListener(this);
        this.view.setFocusable(true);

        // 100ms game update loop
        gameLoop = new Timer(100, this);
        gameLoop.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.move();
        view.repaint();

        if (model.gameOver) {
            gameLoop.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Space bar restarts the game when it's over
        if (model.gameOver && e.getKeyCode() == KeyEvent.VK_SPACE) {
            model.resetGame();
            view.repaint();
            gameLoop.start();
            return;
        }

        // Standard direction controls with reverse prevention
        if (e.getKeyCode() == KeyEvent.VK_UP && model.velocityY != 1) {
            model.velocityX = 0;
            model.velocityY = -1;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && model.velocityY != -1) {
            model.velocityX = 0;
            model.velocityY = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && model.velocityX != 1) {
            model.velocityX = -1;
            model.velocityY = 0;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && model.velocityX != -1) {
            model.velocityX = 1;
            model.velocityY = 0;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
