public class Block {
    public int x;
    public int y;
    public int width;
    public int height;
    public boolean alive = true;
    public boolean used = false;
    public int alienType = 0; // 0: standard, 1: magenta, 2: yellow, 3: cyan

    public Block(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
