package FirstSemestr.Java3.Lesson_4.Chat_with_ExecutorService;

import FirstSemestr.Java3.Lesson_4.Chat_with_ExecutorService.Client.ClientChatAdapter;

public class ClientOne {
    public static void main(String[] args) {
        new ClientChatAdapter("127.0.0.1", 8989);
    }
}
