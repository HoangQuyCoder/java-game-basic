public class Block {
    public int x;
    public int y;
    public int width;
    public int height;
    public String type; // "dino", "cactus1", "cactus2", "cactus3"

    public Block(int x, int y, int width, int height, String type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
    }
}
