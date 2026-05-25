import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
        int boardWidth = 750;
        int boardHeight = 250;

        JFrame frame = new JFrame("Chrome Dinosaur");

        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ChromeDinosaurModel model = new ChromeDinosaurModel();
        ChromeDinosaurView view = new ChromeDinosaurView(model);
        new ChromeDinosaurController(model, view);

        frame.add(view);
        frame.pack();
        view.requestFocus();
        frame.setVisible(true);
    }
}
