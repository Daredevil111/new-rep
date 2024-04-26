import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorFrame implements ActionListener {

    private JTextField display;
    private double result;
    private String lastOperation = "=";

    public CalculatorFrame() {
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        display = new JTextField();
        display.setPreferredSize(new Dimension(300, 50));
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBackground(Color.ORANGE);
        panel.add(display, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(5, 4, 5, 5));

        String[] buttonLabels = {
                "Ans", "&", "^", "%",
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            buttonsPanel.add(button);
        }

        panel.add(buttonsPanel, BorderLayout.CENTER);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            switch (command) {
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                    appendNumber(command);
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                case "%":
                case "^":
                case "&": 
                    performOperation(command);
                    break;
                case "=":
                    calculate();
                    break;
                case "C":
                    clear();
                    break;
                case "Ans":
                    appendAns();
                    break;
            }
        } catch (Exception ex) {
            display.setText("Error");
        }
    }

    private void appendNumber(String number) {
        display.setText(display.getText() + number);
    }

    private void appendAns() {
        if (result != 0) {
            display.setText(display.getText() + "Ans");
        }
    }

    private void performOperation(String operation) throws Exception {
        if (lastOperation.equals("=")) {
            result = Double.parseDouble(display.getText());
        } else {
            calculate();
        }
        lastOperation = operation;
        display.setText("");
    }

    private void calculate() throws Exception {
        double secondOperand = Double.parseDouble(display.getText());
        switch (lastOperation) {
            case "+":
                result += secondOperand;
                break;
            case "-":
                result -= secondOperand;
                break;
            case "*":
                result *= secondOperand;
                break;
            case "/":
                if (secondOperand == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                result /= secondOperand;
                break;
            case "%":
                result %= secondOperand;
                break;
            case "^":
                result = Math.pow(result, secondOperand);
                break;
            case "&": 
                result = (int) result & (int) secondOperand;
                break;
        }
        display.setText(String.valueOf(result));
        lastOperation = "=";
    }

    private void clear() {
        result = 0;
        lastOperation = "=";
        display.setText("");
    }

    public static void main(String[] args) {
        new CalculatorFrame();
    }
}
