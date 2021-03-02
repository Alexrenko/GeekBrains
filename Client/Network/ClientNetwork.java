package FirstSemestr.Java2.Lesson_7_8.Client.Network;

import java.io.EOFException;

public interface ClientNetwork {
    void send(String message);
    String receive() throws EOFException;
}
