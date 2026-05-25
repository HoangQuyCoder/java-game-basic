public class App {
    public static void main(String[] args) throws Exception {
        TicTacToeModel model = new TicTacToeModel();
        TicTacToeView view = new TicTacToeView();
        TicTacToeController controller = new TicTacToeController(model, view);
        controller.startGame();
    }
}
