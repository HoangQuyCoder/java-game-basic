import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {
        PacManModel model = new PacManModel();
        PacManView view = new PacManView(model);
        PacManController controller = new PacManController(model, view);

        JFrame frame = new JFrame("Pac Man");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(view);
        frame.pack();
        view.requestFocus();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        controller.startGame();
    }
}
