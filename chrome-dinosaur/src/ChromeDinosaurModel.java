import java.util.ArrayList;

public class ChromeDinosaurModel {
    public final int boardWidth = 750;
    public final int boardHeight = 250;

    // dinosaur dimensions
    public final int dinosaurWidth = 88;
    public final int dinosaurHeight = 94;
    public final int dinosaurX = 50;
    public final int dinosaurY = boardHeight - dinosaurHeight;

    public Block dinosaur;

    // cactus dimensions
    public final int cactus1Width = 34;
    public final int cactus2Width = 69;
    public final int cactus3Width = 102;
    public final int cactusHeight = 70;
    public final int cactusX = 700;
    public final int cactusY = boardHeight - cactusHeight;

    public ArrayList<Block> cactusArray;

    // physics parameters
    public int velocityX = -12; // cactus scrolling speed
    public int velocityY = 0;   // dinosaur jump vertical speed
    public final int gravity = 1;

    public boolean gameOver = false;
    public int score = 0;

    public ChromeDinosaurModel() {
        dinosaur = new Block(dinosaurX, dinosaurY, dinosaurWidth, dinosaurHeight, "dino");
        cactusArray = new ArrayList<Block>();
        resetGame();
    }

    public void resetGame() {
        dinosaur.y = dinosaurY;
        velocityY = 0;
        cactusArray.clear();
        score = 0;
        gameOver = false;
    }

    public void jump() {
        if (dinosaur.y == dinosaurY && !gameOver) {
            velocityY = -17;
        }
    }

    public void placeCactus() {
        if (gameOver) {
            return;
        }

        double placeCactusChance = Math.random(); // 0 - 0.9999
        if (placeCactusChance > 0.90) { // 10% chance
            Block cactus = new Block(cactusX, cactusY, cactus3Width, cactusHeight, "cactus3");
            cactusArray.add(cactus);
        } else if (placeCactusChance > 0.70) { // 20% chance
            Block cactus = new Block(cactusX, cactusY, cactus2Width, cactusHeight, "cactus2");
            cactusArray.add(cactus);
        } else if (placeCactusChance > 0.50) { // 20% chance
            Block cactus = new Block(cactusX, cactusY, cactus1Width, cactusHeight, "cactus1");
            cactusArray.add(cactus);
        }

        if (cactusArray.size() > 10) {
            cactusArray.remove(0); // remove the oldest offscreen cactus
        }
    }

    public void move() {
        if (gameOver) {
            return;
        }

        // dinosaur physics
        velocityY += gravity;
        dinosaur.y += velocityY;

        if (dinosaur.y > dinosaurY) { // landing boundary
            dinosaur.y = dinosaurY;
            velocityY = 0;
        }

        // cactus scroll and collision
        for (int i = 0; i < cactusArray.size(); i++) {
            Block cactus = cactusArray.get(i);
            cactus.x += velocityX;

            if (collision(dinosaur, cactus)) {
                gameOver = true;
            }
        }

        // score ticks
        score++;
    }

    public boolean collision(Block a, Block b) {
        return a.x < b.x + b.width && // a's top-left corner doesn't reach b's top-right corner
               a.x + a.width > b.x && // a's top-right corner passes b's top-left corner
               a.y < b.y + b.height && // a's top-left corner doesn't reach b's bottom-left corner
               a.y + a.height > b.y; // a's bottom-left corner passes b's top-left corner
    }
}
