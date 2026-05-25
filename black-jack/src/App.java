public class App {
    public static void main(String[] args) throws Exception {
        BlackJackModel model = new BlackJackModel();
        BlackJackView view = new BlackJackView(model);
        new BlackJackController(model, view);
    }
}
