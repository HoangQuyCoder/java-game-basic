import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

public class SpaceInvadersController implements ActionListener, KeyListener {
    private SpaceInvadersModel model;
    private SpaceInvadersView view;

    private Timer gameLoop;

    public SpaceInvadersController(SpaceInvadersModel model, SpaceInvadersView view) {
        this.model = model;
        this.view = view;

        // Register controller listeners onto the View
        this.view.addKeyListener(this);
        this.view.setFocusable(true);

        // 60 FPS refresh update loop
        gameLoop = new Timer(1000 / 60, this);
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
        if (model.gameOver) {
            // Any key restarts the game upon Game Over in SpaceInvaders
            model.resetGame();
            view.repaint();
            gameLoop.start();
        } else {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                model.moveShipLeft();
                view.repaint();
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                model.moveShipRight();
                view.repaint();
            } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                model.fireBullet();
                view.repaint();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
