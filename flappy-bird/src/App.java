import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {
        int boardWidth = 360;
        int boardHeight = 640;

        JFrame frame = new JFrame("Flappy Bird");
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FlappyBirdModel model = new FlappyBirdModel();
        FlappyBirdView view = new FlappyBirdView(model);
        FlappyBirdController controller = new FlappyBirdController(model, view);

        frame.add(view);
        frame.pack();
        view.requestFocus();
        frame.setVisible(true);

        controller.startGame();
    }
}