import java.util.ArrayList;
import java.util.List;

public class FlappyBirdModel {
    private final int boardWidth = 340;
    private final int boardHeight = 640;

    // Bird positioning
    private final int birdX = boardWidth / 8;
    private final int birdY = boardHeight / 2;
    private final int birdWidth = 34;
    private final int birdHeight = 24;

    // Pipe sizes
    private final int pipeX = boardWidth;
    private final int pipeY = 0;
    private final int pipeWidth = 64;
    private final int pipeHeight = 512;

    private Bird bird;
    private List<Pipe> pipes;

    private int velocityX = -4; // move pipe to the left speed
    private int velocityY = 0;  // move bird up/down speed
    private int gravity = 1;

    private double score = 0;
    private boolean gameOver = false;

    public FlappyBirdModel() {
        bird = new Bird(birdX, birdY, birdWidth, birdHeight);
        pipes = new ArrayList<>();
    }

    public void reset() {
        bird.x = birdX;
        bird.y = birdY;
        velocityY = 0;
        pipes.clear();
        score = 0;
        gameOver = false;
    }

    public void placePipes() {
        int randomPipeY = (int) (pipeY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int openingSpace = boardHeight / 4;

        Pipe topPipe = new Pipe(pipeX, randomPipeY, pipeWidth, pipeHeight, true);
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(pipeX, openingSpace + topPipe.y + pipeHeight, pipeWidth, pipeHeight, false);
        pipes.add(bottomPipe);
    }

    public void move() {
        if (gameOver) {
            return;
        }

        // bird physics
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0);

        // pipes scrolling
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.x += velocityX;

            if (!pipe.passed && bird.x > pipe.width + pipe.x) {
                pipe.passed = true;
                score += 0.5; // because two pipes (top and bottom)
            }

            if (collision(bird, pipe)) {
                gameOver = true;
            }
        }

        // ground hit
        if (bird.y > boardHeight) {
            gameOver = true;
        }
    }

    public boolean collision(Bird a, Pipe b) {
        return a.x < b.x + b.width &&
               a.x + a.width > b.x &&
               a.y < b.y + b.height &&
               a.y + a.height > b.y;
    }

    // Getters and Setters
    public int getBoardWidth() { return boardWidth; }
    public int getBoardHeight() { return boardHeight; }

    public Bird getBird() { return bird; }
    public List<Pipe> getPipes() { return pipes; }

    public int getVelocityY() { return velocityY; }
    public void setVelocityY(int velocityY) { this.velocityY = velocityY; }

    public double getScore() { return score; }
    public boolean isGameOver() { return gameOver; }
}
