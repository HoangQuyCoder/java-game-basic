public class App {
    public static void main(String[] args) throws Exception {
        WhacAMoleModel model = new WhacAMoleModel();
        WhacAMoleView view = new WhacAMoleView();
        WhacAMoleController controller = new WhacAMoleController(model, view);
        controller.startGame();
    }
}