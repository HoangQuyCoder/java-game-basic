import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class FlappyBirdView extends JPanel {
    private final FlappyBirdModel model;

    private Image backgroundImg;
    private Image birdImg;
    private Image topPipeImg;
    private Image bottomPipeImg;

    public FlappyBirdView(FlappyBirdModel model) {
        this.model = model;

        setPreferredSize(new Dimension(model.getBoardWidth(), model.getBoardHeight()));
        setFocusable(true);

        loadImages();
    }

    private void loadImages() {
        backgroundImg = new ImageIcon(getClass().getResource("/images/flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("/images/flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("/images/toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("/images/bottompipe.png")).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        // Draw background
        g.drawImage(backgroundImg, 0, 0, model.getBoardWidth(), model.getBoardHeight(), null);

        // Draw bird
        Bird bird = model.getBird();
        g.drawImage(birdImg, bird.x, bird.y, bird.width, bird.height, null);

        // Draw pipes
        for (Pipe pipe : model.getPipes()) {
            Image img = pipe.isTop ? topPipeImg : bottomPipeImg;
            g.drawImage(img, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }

        // Draw score HUD
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if (model.isGameOver()) {
            g.drawString("Game OVer: " + (int) model.getScore(), 10, 35);
        } else {
            g.drawString(String.valueOf((int) model.getScore()), 10, 35);
        }
    }
}
