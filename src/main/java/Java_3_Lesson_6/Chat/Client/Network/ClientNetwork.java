package Java_3_Lesson_6.Chat.Client.Network;

import java.io.EOFException;

public interface ClientNetwork {
    void send(String message);
    String receive() throws EOFException;
}
