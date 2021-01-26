package FirstSemestr.Calculator.Listners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DigitButtonsListener implements ActionListener {
    private final JTextField display;

    public DigitButtonsListener(JTextField display) {
        this.display = display;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String text = display.getText();
        text = text + button.getText();
        display.setText(text);
    }
}
