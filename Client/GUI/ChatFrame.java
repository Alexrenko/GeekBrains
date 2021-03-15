package FirstSemestr.Java2.Lesson_7_8.Client.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class ChatFrame extends JFrame {

    JPanel top;
    JPanel bottom;

    public ChatFrame(String title, Consumer<String> messageConsumer) {
        startingSettings(title);

        top = createTop();
        add(top, BorderLayout.CENTER);
        bottom = createBottom(messageConsumer);
        add(bottom, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void startingSettings(String title) {
        setTitle(title);
        setBounds(0,0,500,600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    public JTextArea getChatArea() {
        return (JTextArea) top.getComponent(0);
    }

    private JPanel createTop() {
        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        top.add(chatArea, BorderLayout.CENTER);
        return top;
    }

    private JPanel createBottom(Consumer<String> messageConsumer) {
        JTextField inputField = new JTextField();
        JButton submit = new JButton("Submit");
        submit.addActionListener(
                new InputFieldListener(
                        (JTextArea) top.getComponent(0),
                        inputField, messageConsumer)
        );
        JPanel bottom = new JPanel();
        bottom.setLayout(new BorderLayout());
        bottom.add(inputField, BorderLayout.CENTER);
        bottom.add(submit, BorderLayout.EAST);
        return bottom;
    }
}
