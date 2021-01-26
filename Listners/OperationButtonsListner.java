package FirstSemestr.Calculator.Listners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperationButtonsListner implements ActionListener {
    JTextField display;

    public OperationButtonsListner(JTextField display) {
        this.display = display;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String text = display.getText();

        if (text.length() > 0) {
            char lastSign = text.charAt(text.length() - 1);
            boolean isSignAgainWrited = (lastSign == '+' || lastSign == '-' ||
                                         lastSign == '*' || lastSign == '/');
            if (isSignAgainWrited) {
                display.setText(changeLastSign(text, button.getText()));
            } else {
                display.setText(text += button.getText());
            }
        } else {
            if (button.getText().equals("-"))
                display.setText(text += button.getText());
        }

    }

    private String changeLastSign(String text, String sign) {
        if (text.length() > 0) {
            text = text.substring(0, text.length() - 1);
            text += sign;
        }
        return text;
    }


}
