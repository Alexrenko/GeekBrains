package FirstSemestr.Java2.Lesson_7_8.Client.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class ChatFrame extends JFrame {

    JPanel top;
    JPanel middle;
    JPanel bottom;

    public ChatFrame(String title, Consumer<String> messageConsumer) {
        startingSettings(title);

        setLayout(new BorderLayout());

//        top = createTop();
//        add(top, BorderLayout.NORTH);
        middle = createMiddle();
        add(middle, BorderLayout.CENTER);
        bottom = createBottom(messageConsumer);
        add(bottom, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void startingSettings(String title) {
        setTitle(title);
        setBounds(0,0,500,600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public JTextArea getChatArea() {
        return (JTextArea) middle.getComponent(0);
    }

    private JPanel createTop() {
        JTextField loginField = new JTextField();
        JTextField passwordField = new JTextField();
        JButton connButton = new JButton("Connect");
        JButton exitButton = new JButton("Exit");
//        connButton.addActionListener();
        JPanel top = new JPanel();
        top.setLayout(new GridLayout(1, 4));
        top.add(loginField);
        top.add(passwordField);
        top.add(connButton);
        top.add(exitButton);
        return top;
    }

    private JPanel createMiddle() {
        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        JPanel middle = new JPanel();
        middle.setLayout(new BorderLayout());
        middle.add(chatArea, BorderLayout.CENTER);
        return middle;
    }

    private JPanel createBottom(Consumer<String> messageConsumer) {
        JTextField inputField = new JTextField();
        JButton submit = new JButton("Submit");
        submit.addActionListener(
                new InputFieldListener(
                        (JTextArea) middle.getComponent(0),
                        inputField, messageConsumer)
        );
        JPanel bottom = new JPanel();
        bottom.setLayout(new BorderLayout());
        bottom.add(inputField, BorderLayout.CENTER);
        bottom.add(submit, BorderLayout.EAST);
        return bottom;
    }
}
