import java.awt.*;
import javax.swing.*;

public class ChromeDinosaurView extends JPanel {
    private ChromeDinosaurModel model;

    // image assets
    private Image dinosaurImg;
    private Image dinosaurDeadImg;
    private Image dinosaurJumpImg;
    private Image cactus1Img;
    private Image cactus2Img;
    private Image cactus3Img;

    public ChromeDinosaurView(ChromeDinosaurModel model) {
        this.model = model;

        setPreferredSize(new Dimension(model.boardWidth, model.boardHeight));
        setBackground(Color.lightGray);

        // Preload image assets from the img resource folder
        dinosaurImg = new ImageIcon(getClass().getResource("/img/dino-run.gif")).getImage();
        dinosaurDeadImg = new ImageIcon(getClass().getResource("/img/dino-dead.png")).getImage();
        dinosaurJumpImg = new ImageIcon(getClass().getResource("/img/dino-jump.png")).getImage();
        cactus1Img = new ImageIcon(getClass().getResource("/img/cactus1.png")).getImage();
        cactus2Img = new ImageIcon(getClass().getResource("/img/cactus2.png")).getImage();
        cactus3Img = new ImageIcon(getClass().getResource("/img/cactus3.png")).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        // 1. Draw Dinosaur based on game state
        Image currentDinoImg;
        if (model.gameOver) {
            currentDinoImg = dinosaurDeadImg;
        } else if (model.dinosaur.y < model.dinosaurY) {
            currentDinoImg = dinosaurJumpImg;
        } else {
            currentDinoImg = dinosaurImg;
        }

        g.drawImage(
                currentDinoImg,
                model.dinosaur.x,
                model.dinosaur.y,
                model.dinosaur.width,
                model.dinosaur.height,
                null);

        // 2. Draw Obstacles (Cactuses)
        for (int i = 0; i < model.cactusArray.size(); i++) {
            Block cactus = model.cactusArray.get(i);
            Image currentCactusImg = null;

            if ("cactus1".equals(cactus.type)) {
                currentCactusImg = cactus1Img;
            } else if ("cactus2".equals(cactus.type)) {
                currentCactusImg = cactus2Img;
            } else if ("cactus3".equals(cactus.type)) {
                currentCactusImg = cactus3Img;
            }

            if (currentCactusImg != null) {
                g.drawImage(
                        currentCactusImg,
                        cactus.x,
                        cactus.y,
                        cactus.width,
                        cactus.height,
                        null);
            }
        }

        // 3. Draw Score HUD
        g.setColor(Color.black);
        g.setFont(new Font("Courier", Font.PLAIN, 32));
        if (model.gameOver) {
            g.drawString("Game Over: " + model.score, 10, 35);
        } else {
            g.drawString(String.valueOf(model.score), 10, 35);
        }
    }
}
