import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Calculator {

    private JFrame frame;
    private JTextField display;
    private double num1 = 0, num2 = 0, result = 0;
    private String operator = "";
    private boolean isOperatorClicked = false;
    private ArrayList<String> history;

    public Calculator() {
        frame = new JFrame("Scientific Calculator");
        frame.setLayout(new BorderLayout());
        frame.setSize(450, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        history = new ArrayList<>(); 

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 30));
        display.setHorizontalAlignment(JTextField.RIGHT); 
        display.setBackground(Color.WHITE);  
        display.setForeground(Color.BLACK);  
        display.setPreferredSize(new Dimension(480, 100)); 
        frame.add(display, BorderLayout.NORTH);

        frame.getContentPane().setBackground(new Color(220, 220, 220)); 

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 5, 10, 10)); 
        frame.add(panel, BorderLayout.CENTER);

        String[] buttons = {
            "7", "8", "9", "/", "sqrt",
            "4", "5", "6", "*", "^",
            "1", "2", "3", "-", "sin",
            "0", ".", "=", "+", "cos",
            "Delete", "History", "tan", "log", "pi"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 18));  
            panel.add(button);

            if (text.matches("[0-9]") || text.equals(".")) {
                button.setBackground(new Color(230, 230, 250)); 
            } else {
                button.setBackground(new Color(135, 206, 235));  
            }
            button.setForeground(Color.BLACK); 
            button.setPreferredSize(new Dimension(60, 60)); 

            button.addActionListener(new ButtonClickListener());
        }

        // Add KeyListener to JTextField
        display.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char keyChar = e.getKeyChar();
                
                // Handle numeric keys
                if (Character.isDigit(keyChar) || keyChar == '.') {
                    if (isOperatorClicked) {
                        display.setText(String.valueOf(keyChar));
                        isOperatorClicked = false;
                    } else {
                        display.setText(display.getText() + keyChar);
                    }
                }
                // Handle operators
                else if (keyChar == '+' || keyChar == '-' || keyChar == '*' || keyChar == '/') {
                    if (!display.getText().isEmpty()) {
                        num1 = Double.parseDouble(display.getText());
                        operator = String.valueOf(keyChar);
                        display.setText(display.getText() + " " + operator + " ");
                        isOperatorClicked = true;
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please enter a number before the operator.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                // Handle "=" key
                else if (keyChar == '=' || keyChar == KeyEvent.VK_ENTER) {
                    try {
                        if (display.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(frame, "Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            num2 = Double.parseDouble(display.getText().split(" ")[0]); // Get the correct operand
                            calculate();
                            display.setText(String.valueOf(result));
                            num1 = result;
                            operator = "";
                            history.add(num1 + " " + operator + " " + num2 + " = " + result);
                            isOperatorClicked = true;
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        display.setText(""); 
                    }
                }
                // Handle Delete key
                else if (keyChar == KeyEvent.VK_BACK_SPACE) {
                    display.setText(""); 
                    num1 = num2 = result = 0;
                    operator = "";
                    isOperatorClicked = false;
                }
                // Handle scientific functions
                else if (keyChar == 's' || keyChar == 'S') { // sqrt
                    num1 = Double.parseDouble(display.getText());
                    result = Math.sqrt(num1);
                    display.setText(String.valueOf(result));
                    history.add("√" + num1 + " = " + result);
                    isOperatorClicked = true;
                } else if (keyChar == 'p' || keyChar == 'P') { // pi
                    display.setText(String.valueOf(Math.PI));
                    history.add("π = " + Math.PI);
                    isOperatorClicked = true;
                }
            }
        });

        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.matches("[0-9]") || command.equals(".")) {
                if (isOperatorClicked) {
                    display.setText(command);
                    isOperatorClicked = false; 
                } else {
                    display.setText(display.getText() + command);
                }
            }
            else if (command.equals("=")) {
                try {
                    if (display.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        num2 = Double.parseDouble(display.getText().split(" ")[0]); // Get the correct operand
                        calculate();
                        display.setText(String.valueOf(result));
                        num1 = result;
                        operator = "";
                        history.add(num1 + " " + operator + " " + num2 + " = " + result);
                        isOperatorClicked = true;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    display.setText(""); 
                }
            }
            else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
                if (!display.getText().isEmpty()) {
                    num1 = Double.parseDouble(display.getText());
                    operator = command;
                    display.setText(display.getText() + " " + operator + " ");
                    isOperatorClicked = true; 
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter a number before the operator.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (command.equals("Delete")) {
                display.setText(""); 
                num1 = num2 = result = 0;
                operator = "";
                isOperatorClicked = false; 
            }
            else if (command.equals("History")) {
                if (history.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "No history available.", "History", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    StringBuilder historyText = new StringBuilder();
                    for (String record : history) {
                        historyText.append(record).append("\n");
                    }
                    JOptionPane.showMessageDialog(frame, historyText.toString(), "History", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else {
                if (command.equals("sqrt")) {
                    num1 = Double.parseDouble(display.getText());
                    result = Math.sqrt(num1);
                    display.setText(String.valueOf(result));
                    history.add("√" + num1 + " = " + result);
                    isOperatorClicked = true;
                } else if (command.equals("^")) {
                    num1 = Double.parseDouble(display.getText());
                    operator = "^";
                    display.setText(display.getText() + " " + operator + " ");
                } else if (command.equals("sin")) {
                    num1 = Double.parseDouble(display.getText());
                    result = Math.sin(Math.toRadians(num1));
                    display.setText(String.valueOf(result));
                    history.add("sin(" + num1 + ") = " + result);
                    isOperatorClicked = true;
                } else if (command.equals("cos")) {
                    num1 = Double.parseDouble(display.getText());
                    result = Math.cos(Math.toRadians(num1));
                    display.setText(String.valueOf(result));
                    history.add("cos(" + num1 + ") = " + result);
                    isOperatorClicked = true;
                } else if (command.equals("tan")) {
                    num1 = Double.parseDouble(display.getText());
                    result = Math.tan(Math.toRadians(num1));
                    display.setText(String.valueOf(result));
                    history.add("tan(" + num1 + ") = " + result);
                    isOperatorClicked = true;
                } else if (command.equals("log")) {
                    num1 = Double.parseDouble(display.getText());
                    result = Math.log10(num1);
                    display.setText(String.valueOf(result));
                    history.add("log(" + num1 + ") = " + result);
                    isOperatorClicked = true;
                } else if (command.equals("pi")) {
                    display.setText(String.valueOf(Math.PI));
                    history.add("π = " + Math.PI);
                    isOperatorClicked = true;
                }
            }
        }
    }

    private void calculate() {
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    JOptionPane.showMessageDialog(frame, "Error! Division by zero.", "Math Error", JOptionPane.ERROR_MESSAGE);
                    display.setText(""); 
                    return;
                }
                break;
            default:
                JOptionPane.showMessageDialog(frame, "Invalid operation.", "Math Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator());
    }
}