import javax.swing.*;

public class Calculator extends JFrame{

    private double firstNum;
    private double secondNum;
    private double result;
    private String operation;
    private JPanel mainPanel;
    private JTextField display;
    private JButton raiseBtn;
    private JButton a7Button;
    private JButton a4Button;
    private JButton a1Button;
    private JButton a8Button;
    private JButton a5Button;
    private JButton a2Button;
    private JButton clrButton;
    private JButton delButton;
    private JButton a9Button;
    private JButton plusBtn;
    private JButton a6Button;
    private JButton minusBtn;
    private JButton a3Button;
    private JButton multiplyBtn;
    private JButton sqrtButton;
    private JButton divideBtn;
    private JButton a0Button;
    private JButton decimalBtn;
    private JButton equalsBtn;

    public Calculator() {
        a0Button.addActionListener(e -> {
            String putText = display.getText() + a0Button.getText();
            display.setText(putText);
        });

        a1Button.addActionListener(e -> {
            String putText = display.getText() + a1Button.getText();
            display.setText(putText);
        });

        a2Button.addActionListener(e -> {
            String putText = display.getText() + a2Button.getText();
            display.setText(putText);
        });

        a3Button.addActionListener(e -> {
            String putText = display.getText() + a3Button.getText();
            display.setText(putText);
        });

        a4Button.addActionListener(e -> {
            String putText = display.getText() + a4Button.getText();
            display.setText(putText);
        });

        a5Button.addActionListener(e -> {
            String putText = display.getText() + a5Button.getText();
            display.setText(putText);
        });

        a6Button.addActionListener(e -> {
            String putText = display.getText() + a6Button.getText();
            display.setText(putText);
        });

        a7Button.addActionListener(e -> {
            String putText = display.getText() + a7Button.getText();
            display.setText(putText);
        });

        a8Button.addActionListener(e -> {
            String putText = display.getText() + a8Button.getText();
            display.setText(putText);
        });

        a9Button.addActionListener(e -> {
            String putText = display.getText() + a9Button.getText();
            display.setText(putText);
        });

        clrButton.addActionListener(e -> {
            String putText = "";
            display.setText(putText);
        });

        delButton.addActionListener(e -> {
            String displayText = display.getText();
            if(displayText.length() == 0) {
                display.setText("");
            } else {
                display.setText(displayText.substring(0, displayText.length() - 1));
            }
        });

        decimalBtn.addActionListener(e -> {
            String putText = display.getText() + decimalBtn.getText();
            display.setText(putText);
        });

        sqrtButton.addActionListener(e -> {
            String displayText = display.getText();
            double value = Double.parseDouble(displayText);
            double srtVal = Math.sqrt(value);
            String output = String.format("%.4f", srtVal);
            display.setText(output);
        });

        raiseBtn.addActionListener(e -> {
            firstNum = Double.parseDouble(display.getText());
            display.setText("");
            operation = "^";
        });

        // Operations
        plusBtn.addActionListener(e -> {
            firstNum = Double.parseDouble(display.getText());
            display.setText("");
            operation = "+";
        });

        minusBtn.addActionListener(e -> {
            firstNum = Double.parseDouble(display.getText());
            display.setText("");
            operation = "-";
        });

        multiplyBtn.addActionListener(e -> {
            firstNum = Double.parseDouble(display.getText());
            display.setText("");
            operation = "*";
        });

        divideBtn.addActionListener(e -> {
            firstNum = Double.parseDouble(display.getText());
            display.setText("");
            operation = "/";
        });

        equalsBtn.addActionListener(e -> {
            secondNum = Double.parseDouble(display.getText());

            switch (operation) {
                case "+" -> result = firstNum + secondNum;
                case "-" -> result = firstNum - secondNum;
                case "*" -> result = firstNum * secondNum;
                case "/" -> result = firstNum / secondNum;
                case "^" -> result = Math.pow(firstNum, secondNum);
            }

            String output = String.format("%.4f", result);
            display.setText(output);
        });
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.setContentPane(calculator.mainPanel);
        calculator.setTitle("Basic Calculator");
        calculator.setBounds(600, 200, 350, 425);
        calculator.getContentPane().setBackground(new java.awt.Color(32, 30, 31));

        calculator.setVisible(true);
        calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
