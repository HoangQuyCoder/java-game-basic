import java.awt.*;
import javax.swing.*;

public class SnakeGameView extends JPanel {
    private SnakeGameModel model;

    public SnakeGameView(SnakeGameModel model) {
        this.model = model;
        setPreferredSize(new Dimension(model.boardWidth, model.boardHeight));
        setBackground(Color.black);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        // --- OPTIONAL GRID BACKGROUND (Keep commented out as in original) ---
        // g.setColor(new Color(50, 50, 50));
        // for (int i = 0; i < model.gridCols; i++) {
        //     g.drawLine(i * model.tileSize, 0, i * model.tileSize, model.boardHeight);
        //     g.drawLine(0, i * model.tileSize, model.boardWidth, i * model.tileSize);
        // }

        // 1. Draw Food (Red 3D rectangle)
        g.setColor(Color.red);
        g.fill3DRect(
            model.food.x * model.tileSize,
            model.food.y * model.tileSize,
            model.tileSize,
            model.tileSize,
            true
        );

        // 2. Draw Snake Head (Green 3D rectangle)
        g.setColor(Color.green);
        g.fill3DRect(
            model.snakeHead.x * model.tileSize,
            model.snakeHead.y * model.tileSize,
            model.tileSize,
            model.tileSize,
            true
        );

        // 3. Draw Snake Body segments (Green 3D rectangles)
        for (int i = 0; i < model.snakeBody.size(); i++) {
            Tile segment = model.snakeBody.get(i);
            g.fill3DRect(
                segment.x * model.tileSize,
                segment.y * model.tileSize,
                model.tileSize,
                model.tileSize,
                true
            );
        }

        // 4. Draw Score HUD
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        if (model.gameOver) {
            g.setColor(Color.red);
            g.drawString("Game Over: " + model.snakeBody.size(), model.tileSize - 16, model.tileSize);
        } else {
            g.setColor(Color.green);
            g.drawString("Score: " + model.snakeBody.size(), model.tileSize - 16, model.tileSize);
        }
    }
}
