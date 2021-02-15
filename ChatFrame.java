package FirstSemestr.Java2.Lesson_4;

import FirstSemestr.Java2.Lesson_4.Listeners.SendButtonListener;
import FirstSemestr.Java2.Lesson_4.Listeners.inputFieldKeyListener;

import javax.swing.*;
import java.awt.*;

public class ChatFrame extends JFrame {
    public ChatFrame() {
        setTitle("Chat");
        setBounds(300, 150, 600, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel chatPanel = new JPanel();
        JPanel buttomPanel = new JPanel();
        JButton sendButton = getCustomizedJButton();
        TextArea chatArea = getCustomizedTextArea();
        JTextField inputField = getCustomizedJTextField();

        setLayout(new BorderLayout());

        add(chatPanel, BorderLayout.CENTER);
        chatPanel.setLayout(new BorderLayout());
        chatPanel.add(chatArea, BorderLayout.CENTER);

        add(buttomPanel, BorderLayout.SOUTH);
        buttomPanel.setLayout((new BorderLayout()));
        buttomPanel.add(inputField, BorderLayout.CENTER);
        buttomPanel.add(sendButton, BorderLayout.EAST);

        SendButtonListener listener = new SendButtonListener(inputField, chatArea);
        sendButton.addActionListener(listener);

        inputFieldKeyListener keyListener = new inputFieldKeyListener(chatArea);
        inputField.addKeyListener(keyListener);

        setVisible(true);
    }

    private static JButton getCustomizedJButton() {
        JButton button = new JButton("Send");
        button.setBackground(new Color(230, 230, 230));
        button.setFont(new Font("Arial", Font.BOLD, 22));
        button.setForeground(new Color(0, 65, 80));
        return button;
    }

    private static JTextField getCustomizedJTextField() {
        JTextField textField = new JTextField();
        textField.setBackground(new Color(230, 230, 230));
        textField.setFont(new Font("Arial", Font.BOLD, 18));
        textField.setForeground(new Color(0, 65, 80));
        return textField;
    }

    private static TextArea getCustomizedTextArea() {
        TextArea area = new TextArea();
        area.setBackground(new Color(204, 204, 204));
        area.setFont(new Font("Arial Black", Font.BOLD, 16));
        area.setForeground(new Color(0, 65, 80));
        return area;
    }
}
