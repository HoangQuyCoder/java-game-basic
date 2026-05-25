import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class PacManModel {
    private final int rowCount = 21;
    private final int columnCount = 19;
    private final int tileSize = 32;
    private final int boardWidth = columnCount * tileSize;
    private final int boardHeight = rowCount * tileSize;

    private final String[] tileMaps = {
        "XXXXXXXXXXXXXXXXXXX",
        "X        X        X",
        "X XX XXX X XXX XX X",
        "X                 X",
        "X XX X XXXXX X XX X",
        "X    X       X    X",
        "XXXX XXXX XXXX XXXX",
        "OOOX X       X XOOO",
        "XXXX X XXrXX X XXXX",
        "O       bpo       O",
        "XXXX X XXXXX X XXXX",
        "OOOX X       X XOOO",
        "XXXX X XXXXX X XXXX",
        "X        X        X",
        "X XX XXX X XXX XX X",
        "X  X     P     X  X",
        "XX X X XXXXX X X XX",
        "X    X   X   X    X",
        "X XXXXXX X XXXXXX X",
        "X                 X",
        "XXXXXXXXXXXXXXXXXXX"
    };

    private final Set<PacManBlock> walls = new HashSet<>();
    private final Set<PacManBlock> foods = new HashSet<>();
    private final Set<PacManBlock> ghosts = new HashSet<>();
    private PacManBlock pacman;

    private final Random random = new Random();
    private final char[] directions = { 'U', 'D', 'L', 'R' };

    private int lives = 3;
    private int score = 0;
    private boolean gameOver = false;

    public PacManModel() {
        loadMap();
    }

    public void loadMap() {
        walls.clear();
        foods.clear();
        ghosts.clear();

        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < columnCount; c++) {
                String row = tileMaps[r];
                char tileMap = row.charAt(c);

                int x = c * tileSize;
                int y = r * tileSize;

                if (tileMap == 'X') {
                    PacManBlock wallBlock = new PacManBlock('X', x, y, tileSize, tileSize);
                    walls.add(wallBlock);
                } else if (tileMap == 'b') {
                    PacManBlock ghostBlock = new PacManBlock('b', x, y, tileSize, tileSize);
                    ghosts.add(ghostBlock);
                } else if (tileMap == 'o') {
                    PacManBlock ghostBlock = new PacManBlock('o', x, y, tileSize, tileSize);
                    ghosts.add(ghostBlock);
                } else if (tileMap == 'r') {
                    PacManBlock ghostBlock = new PacManBlock('r', x, y, tileSize, tileSize);
                    ghosts.add(ghostBlock);
                } else if (tileMap == 'p') {
                    PacManBlock ghostBlock = new PacManBlock('p', x, y, tileSize, tileSize);
                    ghosts.add(ghostBlock);
                } else if (tileMap == 'P') {
                    pacman = new PacManBlock('P', x, y, tileSize, tileSize);
                    pacman.direction = 'R'; // Starts moving right by default
                } else if (tileMap == ' ') {
                    PacManBlock foodBlock = new PacManBlock(' ', x + 14, y + 14, 4, 4);
                    foods.add(foodBlock);
                }
            }
        }

        // Initialize ghost directions
        for (PacManBlock ghost : ghosts) {
            char newDirection = directions[random.nextInt(4)];
            ghost.updateDirection(newDirection, walls, tileSize);
        }
    }

    public void resetPositions() {
        pacman.reset();
        for (PacManBlock ghost : ghosts) {
            ghost.reset();
            char newDirection = directions[random.nextInt(4)];
            ghost.updateDirection(newDirection, walls, tileSize);
        }
    }

    public void move() {
        if (gameOver) {
            return;
        }

        pacman.x += pacman.velocityX;
        pacman.y += pacman.velocityY;

        // Check wall collisions
        for (PacManBlock wall : walls) {
            if (pacman.collision(wall)) {
                pacman.x -= pacman.velocityX;
                pacman.y -= pacman.velocityY;
                break;
            }
        }

        // Check ghost collisions
        for (PacManBlock ghost : ghosts) {
            if (pacman.collision(ghost)) {
                lives--;
                if (lives == 0) {
                    gameOver = true;
                    return;
                }
                resetPositions();
            }

            if (ghost.y == tileSize * 9 && ghost.direction != 'U' && ghost.direction != 'D') {
                ghost.updateDirection('U', walls, tileSize);
            }

            ghost.x += ghost.velocityX;
            ghost.y += ghost.velocityY;

            for (PacManBlock wall : walls) {
                if (ghost.collision(wall) || ghost.x <= 0 || ghost.x + ghost.width >= boardWidth) {
                    ghost.x -= ghost.velocityX;
                    ghost.y -= ghost.velocityY;
                    char newDirection = directions[random.nextInt(4)];
                    ghost.updateDirection(newDirection, walls, tileSize);
                }
            }
        }

        // Check food collision
        PacManBlock foodEaten = null;
        for (PacManBlock food : foods) {
            if (pacman.collision(food)) {
                foodEaten = food;
                score += 10;
            }
        }
        if (foodEaten != null) {
            foods.remove(foodEaten);
        }

        if (foods.isEmpty()) {
            loadMap();
            resetPositions();
        }
    }

    // Getters and Setters
    public int getRowCount() { return rowCount; }
    public int getColumnCount() { return columnCount; }
    public int getTileSize() { return tileSize; }
    public int getBoardWidth() { return boardWidth; }
    public int getBoardHeight() { return boardHeight; }
    
    public Set<PacManBlock> getWalls() { return walls; }
    public Set<PacManBlock> getFoods() { return foods; }
    public Set<PacManBlock> getGhosts() { return ghosts; }
    public PacManBlock getPacman() { return pacman; }

    public int getLives() { return lives; }
    public void setLives(int lives) { this.lives = lives; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public boolean isGameOver() { return gameOver; }
    public void setGameOver(boolean gameOver) { this.gameOver = gameOver; }
}
