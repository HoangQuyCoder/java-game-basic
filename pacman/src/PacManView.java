import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class PacManView extends JPanel {
    private final PacManModel model;

    private Image wallImage;
    private Image blueGhostImage;
    private Image orangeGhostImage;
    private Image pinkGhostImage;
    private Image redGhostImage;

    private Image pacmanUpImage;
    private Image pacmanDownImage;
    private Image pacmanLeftImage;
    private Image pacmanRightImage;

    public PacManView(PacManModel model) {
        this.model = model;

        setPreferredSize(new Dimension(model.getBoardWidth(), model.getBoardHeight()));
        setBackground(Color.black);
        setFocusable(true);

        loadImages();
    }

    private void loadImages() {
        wallImage = new ImageIcon(getClass().getResource("/images/wall.png")).getImage();
        blueGhostImage = new ImageIcon(getClass().getResource("/images/blueGhost.png")).getImage();
        orangeGhostImage = new ImageIcon(getClass().getResource("/images/orangeGhost.png")).getImage();
        pinkGhostImage = new ImageIcon(getClass().getResource("/images/pinkGhost.png")).getImage();
        redGhostImage = new ImageIcon(getClass().getResource("/images/redGhost.png")).getImage();

        pacmanUpImage = new ImageIcon(getClass().getResource("/images/pacmanUp.png")).getImage();
        pacmanDownImage = new ImageIcon(getClass().getResource("/images/pacmanDown.png")).getImage();
        pacmanLeftImage = new ImageIcon(getClass().getResource("/images/pacmanLeft.png")).getImage();
        pacmanRightImage = new ImageIcon(getClass().getResource("/images/pacmanRight.png")).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        // Draw Pac-man
        PacManBlock pacman = model.getPacman();
        Image pacmanImg = getPacmanImage(pacman.direction);
        g.drawImage(pacmanImg, pacman.x, pacman.y, pacman.width, pacman.height, null);

        // Draw Foods
        g.setColor(Color.WHITE);
        for (PacManBlock food : model.getFoods()) {
            g.fillRect(food.x, food.y, food.width, food.height);
        }

        // Draw Ghosts
        for (PacManBlock ghost : model.getGhosts()) {
            Image ghostImg = getGhostImage(ghost.type);
            g.drawImage(ghostImg, ghost.x, ghost.y, ghost.width, ghost.height, null);
        }

        // Draw Walls
        for (PacManBlock wall : model.getWalls()) {
            g.drawImage(wallImage, wall.x, wall.y, wall.width, wall.height, null);
        }

        // Draw HUD Text
        g.setFont(new Font("Arial", Font.PLAIN, 25));
        g.setColor(Color.WHITE);
        if (!model.isGameOver()) {
            g.drawString("x = " + model.getLives() + " Score: " + model.getScore(), model.getTileSize() / 2,
                    model.getTileSize() / 2);
        } else {
            g.drawString("Game Over: " + model.getScore(), model.getTileSize() / 2, model.getTileSize() / 2);
        }
    }

    private Image getPacmanImage(char direction) {
        if (direction == 'U')
            return pacmanUpImage;
        if (direction == 'D')
            return pacmanDownImage;
        if (direction == 'L')
            return pacmanLeftImage;
        return pacmanRightImage; // default or 'R'
    }

    private Image getGhostImage(char type) {
        if (type == 'b')
            return blueGhostImage;
        if (type == 'o')
            return orangeGhostImage;
        if (type == 'p')
            return pinkGhostImage;
        return redGhostImage; // default or 'r'
    }
}
