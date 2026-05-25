import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {
        int tileSize = 32;
        int rows = 16;
        int cols = 16;
        int boardWidth = tileSize * cols;
        int boardHeight = tileSize * rows;

        JFrame frame = new JFrame("Space Invaders");
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        SpaceInvadersModel model = new SpaceInvadersModel();
        SpaceInvadersView view = new SpaceInvadersView(model);
        SpaceInvadersController controller = new SpaceInvadersController(model, view);

        view.requestFocus();
        frame.add(view);
        frame.pack();
        frame.setVisible(true);
    }
}
