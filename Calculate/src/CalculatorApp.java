import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApp {
    private JFrame frame;
    private JTextField inputField;

    private double currentValue;
    private String currentOperator;

    public CalculatorApp() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        inputField = new JTextField();
        inputField.setHorizontalAlignment(JTextField.RIGHT);
        frame.add(inputField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4));
        frame.add(buttonPanel, BorderLayout.CENTER);

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String button : buttons) {
            JButton btn = new JButton(button);
            buttonPanel.add(btn);
            btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    onButtonClick(e);
                }
            });
        }

        frame.setSize(300, 400);
        frame.setVisible(true);
    }

    private void onButtonClick(ActionEvent e) {
        String buttonText = ((JButton) e.getSource()).getText();

        if ("0123456789.".contains(buttonText)) {
            inputField.setText(inputField.getText() + buttonText);
        } else if ("/ * - +".contains(buttonText)) {
            currentValue = Double.parseDouble(inputField.getText() );
            currentOperator = buttonText;
            inputField.setText("");
        } else if ("=".equals(buttonText)) {
            double secondValue = Double.parseDouble(inputField.getText());
            double result = performOperation(currentValue, secondValue, currentOperator);
            inputField.setText(String.valueOf(result));
        }
    }

    private double performOperation(double firstValue, double secondValue, String operator) {
        switch (operator) {
            case "/":
                return firstValue / secondValue;
            case "*":
                return firstValue * secondValue;
            case "-":
                return firstValue - secondValue;
            case "+":
                return firstValue + secondValue;
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }
}


