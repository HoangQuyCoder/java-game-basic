import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class CalculatorView extends JPanel {
    public final int boardWidth = 360;
    public final int boardHeight = 540;

    private Color customLightGray = new Color(212, 212, 210);
    private Color customDarkGray = new Color(80, 80, 80);
    private Color customBlack = new Color(28, 28, 28);
    private Color customOrange = new Color(255, 149, 0);

    private String[] buttonValues = {
        "AC", "+/-", "%", "÷",
        "7", "8", "9", "×",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "√", "="
    };
    private String[] rightSymbols = { "÷", "×", "-", "+", "=" };
    private String[] topSymbols = { "AC", "+/-", "%" };

    private JLabel displayLabel;
    private ArrayList<JButton> buttons;

    public CalculatorView() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setLayout(new BorderLayout());
        setBackground(customBlack);

        // Display panel at the top
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayLabel = new JLabel("0");
        displayLabel.setBackground(customBlack);
        displayLabel.setForeground(Color.white);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setOpaque(true);
        displayPanel.add(displayLabel, BorderLayout.CENTER);
        add(displayPanel, BorderLayout.NORTH);

        // Buttons panel in the center
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4));
        buttonPanel.setBackground(customBlack);

        buttons = new ArrayList<JButton>();
        for (int i = 0; i < buttonValues.length; i++) {
            String buttonValue = buttonValues[i];
            JButton button = new JButton(buttonValue);
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setFocusable(false);
            button.setBorder(new LineBorder(customBlack));

            // Style buttons based on their category
            if (Arrays.asList(topSymbols).contains(buttonValue)) {
                button.setBackground(customLightGray);
                button.setForeground(customBlack);
            } else if (Arrays.asList(rightSymbols).contains(buttonValue)) {
                button.setBackground(customOrange);
                button.setForeground(Color.white);
            } else {
                button.setBackground(customDarkGray);
                button.setForeground(Color.white);
            }

            buttonPanel.add(button);
            buttons.add(button);
        }
        add(buttonPanel, BorderLayout.CENTER);
    }

    public void updateDisplay(String text) {
        displayLabel.setText(text);
    }

    public void setButtonListener(ActionListener listener) {
        for (JButton button : buttons) {
            button.addActionListener(listener);
        }
    }
}
