public class CalculatorModel {
    public String A = "0";
    public String operator = null;
    public String B = null;
    public String displayValue = "0";

    public void clearAll() {
        A = "0";
        operator = null;
        B = null;
    }

    public void resetDisplay() {
        displayValue = "0";
        clearAll();
    }

    public void appendDigit(String digit) {
        if (digit.equals(".")) {
            if (!displayValue.contains(".")) {
                displayValue = displayValue + ".";
            }
        } else { // 0-9
            if (displayValue.equals("0")) {
                displayValue = digit;
            } else {
                displayValue = displayValue + digit;
            }
        }
    }

    public void toggleSign() {
        try {
            double val = Double.parseDouble(displayValue);
            val *= -1;
            displayValue = removeZeroDecimal(val);
        } catch (NumberFormatException e) {
            displayValue = "0";
        }
    }

    public void applyPercent() {
        try {
            double val = Double.parseDouble(displayValue);
            val /= 100;
            displayValue = removeZeroDecimal(val);
        } catch (NumberFormatException e) {
            displayValue = "0";
        }
    }

    public void setOperator(String op) {
        if (operator == null) {
            A = displayValue;
            displayValue = "0";
            B = "0";
        }
        operator = op;
    }

    public void calculate() {
        if (A != null && operator != null) {
            B = displayValue;
            try {
                double numA = Double.parseDouble(A);
                double numB = Double.parseDouble(B);
                double result = 0;

                if (operator.equals("+")) {
                    result = numA + numB;
                } else if (operator.equals("-")) {
                    result = numA - numB;
                } else if (operator.equals("×")) {
                    result = numA * numB;
                } else if (operator.equals("÷")) {
                    result = numA / numB;
                } else if (operator.equals("√")) {
                    result = Math.sqrt(numA);
                }
                displayValue = removeZeroDecimal(result);
            } catch (NumberFormatException e) {
                displayValue = "Error";
            }
            clearAll();
        }
    }

    public String removeZeroDecimal(double numDisplay) {
        if (numDisplay % 1 == 0) {
            return Integer.toString((int) numDisplay);
        }
        return Double.toString(numDisplay);
    }
}
