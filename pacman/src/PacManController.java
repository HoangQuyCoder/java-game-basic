import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PacManController implements KeyListener, ActionListener {
    private final PacManModel model;
    private final PacManView view;
    private Timer gameLoop;

    public PacManController(PacManModel model, PacManView view) {
        this.model = model;
        this.view = view;

        initController();
    }

    private void initController() {
        view.addKeyListener(this);
        gameLoop = new Timer(50, this);
    }

    public void startGame() {
        gameLoop.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.move();
        view.repaint();
        if (model.isGameOver()) {
            gameLoop.stop();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (model.isGameOver()) {
            model.loadMap();
            model.resetPositions();
            model.setGameOver(false);
            model.setLives(3);
            model.setScore(0);
            gameLoop.start();
        } else {
            char newDir = ' ';
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                newDir = 'U';
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                newDir = 'D';
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                newDir = 'L';
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                newDir = 'R';
            }

            if (newDir != ' ') {
                model.getPacman().updateDirection(newDir, model.getWalls(), model.getTileSize());
            }
        }
    }
}
