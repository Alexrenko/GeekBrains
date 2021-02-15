package FirstSemestr.Java2.Lesson_4.Listeners;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class inputFieldKeyListener implements KeyListener {
    TextArea chatArea;

    public inputFieldKeyListener(TextArea chatArea) {
        this.chatArea = chatArea;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 10) {
            JTextField inputField = (JTextField) e.getSource();
            if (!inputField.getText().equals("")) {
                chatArea.append(inputField.getText() + '\n');
                inputField.setText("");
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
