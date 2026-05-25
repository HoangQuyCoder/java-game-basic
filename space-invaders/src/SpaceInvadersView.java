import java.awt.*;
import javax.swing.*;

public class SpaceInvadersView extends JPanel {
    private SpaceInvadersModel model;

    // image assets
    private Image shipImg;
    private Image[] alienImages;

    public SpaceInvadersView(SpaceInvadersModel model) {
        this.model = model;

        setPreferredSize(new Dimension(model.boardWidth, model.boardHeight));
        setBackground(Color.black);

        // Preload image assets from the img resource folder
        shipImg = new ImageIcon(getClass().getResource("/images/ship.png")).getImage();

        alienImages = new Image[4];
        alienImages[0] = new ImageIcon(getClass().getResource("/images/alien.png")).getImage();
        alienImages[1] = new ImageIcon(getClass().getResource("/images/alien-magenta.png")).getImage();
        alienImages[2] = new ImageIcon(getClass().getResource("/images/alien-yellow.png")).getImage();
        alienImages[3] = new ImageIcon(getClass().getResource("/images/alien-cyan.png")).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        // 1. Draw Ship
        g.drawImage(
                shipImg,
                model.ship.x,
                model.ship.y,
                model.ship.width,
                model.ship.height,
                null);

        // 2. Draw Active Aliens mapping types to correct preloaded images
        for (int i = 0; i < model.alienArray.size(); i++) {
            Block alien = model.alienArray.get(i);
            if (alien.alive) {
                Image currentAlienImg = alienImages[alien.alienType];
                g.drawImage(
                        currentAlienImg,
                        alien.x,
                        alien.y,
                        alien.width,
                        alien.height,
                        null);
            }
        }

        // 3. Draw Bullets (White rectangles)
        g.setColor(Color.white);
        for (int i = 0; i < model.bulletArray.size(); i++) {
            Block bullet = model.bulletArray.get(i);
            if (!bullet.used) {
                g.fillRect(
                        bullet.x,
                        bullet.y,
                        bullet.width,
                        bullet.height);
            }
        }

        // 4. Draw Score HUD
        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if (model.gameOver) {
            g.drawString("Game Over: " + model.score, 10, 35);
        } else {
            g.drawString(String.valueOf(model.score), 10, 35);
        }
    }
}
