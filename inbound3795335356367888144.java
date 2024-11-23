import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AgapayCalculator extends JFrame {
    private final JTextField display = new JTextField("0");
    private String operator = "";
    private double num1 = 0;
    JFrame frame = new JFrame ();

    public AgapayCalculator() {
        setTitle("Agapay Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 5, 5));
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "×",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this::onButtonClick);
            buttonPanel.add(button);
        }
        add(buttonPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void onButtonClick(ActionEvent e) {
        String command = e.getActionCommand();
        try {
            switch (command) {
                case "C" -> display.setText("0");
                case "=" -> {
                    double num2 = Double.parseDouble(display.getText());
                    double result = switch (operator) {
                        case "+" -> num1 + num2;
                        case "-" -> num1 - num2;
                        case "×" -> num1 * num2;
                        case "/" -> num1 / num2;
                        default -> num2;
                    };
                    display.setText(String.valueOf(result));
                    operator = "";
                }
                case "+", "-", "×", "/" -> {
                    num1 = Double.parseDouble(display.getText());
                    operator = command;
                    display.setText("0");
                }
                default -> {
                    if (display.getText().equals("0")) display.setText(command);
                    else display.setText(display.getText() + command);
                }
            }
        } catch (Exception ex) {
            display.setText("Error");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AgapayCalculator::new);
    }
}