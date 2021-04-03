package Java_3_Lesson_6.Chat;

import Java_3_Lesson_6.Chat.Client.ClientChatAdapter;

public class ClientTwo {
    public static void main(String[] args) {
        new ClientChatAdapter("127.0.0.1", 8989);
    }
}
