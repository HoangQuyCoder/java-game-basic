import java.util.ArrayList;
import java.util.Random;

public class SpaceInvadersModel {
    public final int tileSize = 32;
    public final int rows = 16;
    public final int cols = 16;
    public final int boardWidth = tileSize * cols;
    public final int boardHeight = tileSize * rows;

    // ship specifications
    public final int shipWidth = tileSize * 2;
    public final int shipHeight = tileSize;
    public final int shipX = tileSize * (cols / 2) - tileSize;
    public final int shipY = boardHeight - tileSize * 2;
    public final int shipVelocity = tileSize;
    public Block ship;

    // aliens specifications
    public final int alienWidth = tileSize * 2;
    public final int alienHeight = tileSize;
    public final int alienX = tileSize;
    public final int alienY = tileSize;
    public int alienRows = 2;
    public int alienColumns = 3;
    public int alienCount = 0;
    public int alienVelocityX = 1;
    public ArrayList<Block> alienArray;

    // bullets specifications
    public final int bulletWidth = tileSize / 8;
    public final int bulletHeight = tileSize / 2;
    public final int bulletVelocityY = -10;
    public ArrayList<Block> bulletArray;

    public int score = 0;
    public boolean gameOver = false;

    private Random random;

    public SpaceInvadersModel() {
        this.random = new Random();
        resetGame();
    }

    public void resetGame() {
        score = 0;
        gameOver = false;
        alienColumns = 3;
        alienRows = 2;
        alienVelocityX = 1;

        // initialize ship block
        ship = new Block(shipX, shipY, shipWidth, shipHeight);

        // initialize collections
        alienArray = new ArrayList<Block>();
        bulletArray = new ArrayList<Block>();

        createAliens();
    }

    public void createAliens() {
        for (int r = 0; r < alienRows; r++) {
            for (int c = 0; c < alienColumns; c++) {
                Block alien = new Block(alienX + c * alienWidth, alienY + r * alienHeight, alienWidth, alienHeight);
                alien.alienType = random.nextInt(4); // 0 to 3
                alienArray.add(alien);
            }
        }
        alienCount = alienArray.size();
    }

    public void moveShipLeft() {
        if (!gameOver && ship.x - shipVelocity >= 0) {
            ship.x -= shipVelocity;
        }
    }

    public void moveShipRight() {
        if (!gameOver && ship.x + shipVelocity + ship.width <= boardWidth) {
            ship.x += shipVelocity;
        }
    }

    public void fireBullet() {
        if (!gameOver) {
            Block bullet = new Block(ship.x + shipWidth * 15 / 32, ship.y, bulletWidth, bulletHeight);
            bulletArray.add(bullet);
        }
    }

    public void move() {
        if (gameOver) {
            return;
        }

        // 1. Move Aliens and check boundaries
        boolean touchedBorder = false;
        for (int i = 0; i < alienArray.size(); i++) {
            Block alien = alienArray.get(i);
            if (alien.alive) {
                alien.x += alienVelocityX;

                // border hits
                if (alien.x + alien.width >= boardWidth || alien.x <= 0) {
                    touchedBorder = true;
                }

                // descent threshold hits ship y
                if (alien.y >= ship.y) {
                    gameOver = true;
                }
            }
        }

        // if any alien hits screen edges, rebound direction and descend all aliens
        if (touchedBorder) {
            alienVelocityX *= -1;
            for (int i = 0; i < alienArray.size(); i++) {
                Block alien = alienArray.get(i);
                alien.x += alienVelocityX * 2;
                alien.y += alienHeight;
            }
        }

        // 2. Move Bullets
        for (int i = 0; i < bulletArray.size(); i++) {
            Block bullet = bulletArray.get(i);
            bullet.y += bulletVelocityY;

            // check collisions against alive aliens
            for (int j = 0; j < alienArray.size(); j++) {
                Block alien = alienArray.get(j);
                if (!bullet.used && alien.alive && detectCollision(bullet, alien)) {
                    bullet.used = true;
                    alien.alive = false;
                    alienCount--;
                    score += 100;
                }
            }
        }

        // Clear out-of-bounds or used bullets
        while (bulletArray.size() > 0 && (bulletArray.get(0).used || bulletArray.get(0).y < 0)) {
            bulletArray.remove(0);
        }

        // 3. Check for next level wave
        if (alienCount == 0) {
            score += alienColumns * alienRows * 100; // level completion bonus
            alienColumns = Math.min(alienColumns + 1, cols / 2 - 2); // cap at 6
            alienRows = Math.min(alienRows + 1, rows - 6); // cap at 10
            alienArray.clear();
            bulletArray.clear();
            createAliens();
        }
    }

    public boolean detectCollision(Block a, Block b) {
        return a.x < b.x + b.width && // a's top-left doesn't reach b's top-right
               a.x + a.width > b.x && // a's top-right passes b's top-left
               a.y < b.y + b.height && // a's top-left doesn't reach b's bottom-left
               a.y + a.height > b.y; // a's bottom-left passes b's top-left
    }
}
