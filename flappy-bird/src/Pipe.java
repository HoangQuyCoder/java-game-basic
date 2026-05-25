public class Pipe {
    public int x;
    public int y;
    public int width;
    public int height;
    public boolean passed = false;
    public boolean isTop;

    public Pipe(int x, int y, int width, int height, boolean isTop) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isTop = isTop;
    }
}
