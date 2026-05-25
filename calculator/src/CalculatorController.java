import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class CalculatorController implements ActionListener {
    private CalculatorModel model;
    private CalculatorView view;

    public CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;

        // Register controller action listener onto the View buttons
        this.view.setButtonListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonValue = button.getText();

        if (buttonValue.equals("AC")) {
            model.resetDisplay();
        } else if (buttonValue.equals("+/-")) {
            model.toggleSign();
        } else if (buttonValue.equals("%")) {
            model.applyPercent();
        } else if (buttonValue.equals("=")) {
            model.calculate();
        } else if (buttonValue.equals("+") || buttonValue.equals("-") || 
                   buttonValue.equals("×") || buttonValue.equals("÷") || 
                   buttonValue.equals("√")) {
            model.setOperator(buttonValue);
        } else { // Digits or dot
            model.appendDigit(buttonValue);
        }

        // Always update display label representation
        view.updateDisplay(model.displayValue);
    }
}
