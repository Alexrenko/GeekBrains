package FirstSemestr.Java3.Lesson_4.Chat_with_ExecutorService.Client.Network;

import java.io.EOFException;

public interface ClientNetwork {
    void send(String message);
    String receive() throws EOFException;
}
