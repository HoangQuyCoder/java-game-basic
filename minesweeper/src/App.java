public class App {
    public static void main(String[] args) throws Exception {
        MinesweeperModel model = new MinesweeperModel();
        MinesweeperView view = new MinesweeperView(model);
        new MinesweeperController(model, view);
    }
}
