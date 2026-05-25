import java.util.ArrayList;
import java.util.Random;

public class SnakeGameModel {
    public int boardWidth;
    public int boardHeight;
    public final int tileSize = 25;

    public int gridCols;
    public int gridRows;

    // snake entities
    public Tile snakeHead;
    public ArrayList<Tile> snakeBody;

    // food entity
    public Tile food;
    private Random random;

    // directional vector speed
    public int velocityX;
    public int velocityY;

    public boolean gameOver = false;

    public SnakeGameModel(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.gridCols = this.boardWidth / tileSize;
        this.gridRows = this.boardHeight / tileSize;
        this.random = new Random();
        resetGame();
    }

    public void resetGame() {
        snakeHead = new Tile(5, 5);
        snakeBody = new ArrayList<Tile>();
        food = new Tile(10, 10);
        placeFood();

        velocityX = 0;
        velocityY = 0;
        gameOver = false;
    }

    public void placeFood() {
        food.x = random.nextInt(gridCols);
        food.y = random.nextInt(gridRows);
    }

    public boolean collision(Tile tile1, Tile tile2) {
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    public void move() {
        if (gameOver) {
            return;
        }

        // Eat food check
        if (collision(snakeHead, food)) {
            snakeBody.add(new Tile(food.x, food.y));
            placeFood();
        }

        // Move body parts
        for (int i = snakeBody.size() - 1; i >= 0; i--) {
            Tile snakePart = snakeBody.get(i);
            if (i == 0) {
                snakePart.x = snakeHead.x;
                snakePart.y = snakeHead.y;
            } else {
                Tile prevSnakePart = snakeBody.get(i - 1);
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }
        }

        // Move head
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;

        // Check self body collision
        for (int i = 0; i < snakeBody.size(); i++) {
            Tile snakePart = snakeBody.get(i);
            if (collision(snakeHead, snakePart)) {
                gameOver = true;
            }
        }

        // Check boundary collision (fixed off-by-one!)
        if (snakeHead.x < 0 || snakeHead.x >= gridCols ||
            snakeHead.y < 0 || snakeHead.y >= gridRows) {
            gameOver = true;
        }
    }
}
