package FirstSemestr.Java2.Lesson_4.Listeners;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendButtonListener implements ActionListener {
    JTextField inputField;
    TextArea chatField;

    public SendButtonListener(JTextField inputField, TextArea chatField) {
        this.inputField = inputField;
        this.chatField = chatField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!inputField.getText().equals("")) {
            chatField.append(inputField.getText() + '\n');
            inputField.setText("");
        }
    }
}
