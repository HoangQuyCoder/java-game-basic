public class App {
    public static void main(String[] args) throws Exception {
        javax.swing.JFrame frame = new javax.swing.JFrame("Sudoku");
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        SudokuModel model = new SudokuModel();
        SudokuView view = new SudokuView();
        @SuppressWarnings("unused")
        SudokuController controller = new SudokuController(model, view);

        frame.add(view);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
