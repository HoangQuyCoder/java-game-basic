public class App {
    public static void main(String[] args) throws Exception {
        MatchCardsModel model = new MatchCardsModel();
        MatchCardsView view = new MatchCardsView(model);
        new MatchCardsController(model, view);
    }
}
