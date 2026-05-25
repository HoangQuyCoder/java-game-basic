public class PacManBlock {
    public int x;
    public int y;
    public int width;
    public int height;
    public int startX;
    public int startY;
    public char type; // 'X' for wall, 'P' for Pac-Man, 'b'/'o'/'p'/'r' for ghosts, ' ' for food
    public char direction = 'U';
    public int velocityX = 0;
    public int velocityY = 0;

    public PacManBlock(char type, int x, int y, int width, int height) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.startX = x;
        this.startY = y;
    }

    public void updateDirection(char direction, java.util.Collection<PacManBlock> walls, int tileSize) {
        char prevDirection = this.direction;
        this.direction = direction;
        updateVelocity(tileSize);
        this.x += this.velocityX;
        this.y += this.velocityY;

        for (PacManBlock wall : walls) {
            if (collision(wall)) {
                this.x -= this.velocityX;
                this.y -= this.velocityY;
                this.direction = prevDirection;
                updateVelocity(tileSize);
                break;
            }
        }
    }

    public void updateVelocity(int tileSize) {
        if (this.direction == 'U') {
            this.velocityX = 0;
            this.velocityY = -tileSize / 4;
        } else if (this.direction == 'D') {
            this.velocityX = 0;
            this.velocityY = tileSize / 4;
        } else if (this.direction == 'L') {
            this.velocityX = -tileSize / 4;
            this.velocityY = 0;
        } else if (this.direction == 'R') {
            this.velocityX = tileSize / 4;
            this.velocityY = 0;
        }
    }

    public boolean collision(PacManBlock other) {
        return this.x < other.x + other.width &&
               this.x + this.width > other.x &&
               this.y < other.y + other.height &&
               this.y + this.height > other.y;
    }

    public void reset() {
        this.x = startX;
        this.y = startY;
        this.velocityX = 0;
        this.velocityY = 0;
    }
}
