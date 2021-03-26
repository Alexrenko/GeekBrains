package FirstSemestr.Chat;

import FirstSemestr.Chat.Client.ClientChatAdapter;

import java.io.IOException;

public class ClientOne {
    public static void main(String[] args) {
        new ClientChatAdapter("127.0.0.1", 8989);
    }
}
