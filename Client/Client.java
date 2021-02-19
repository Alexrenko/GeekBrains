package FirstSemestr.Java2.Lesson_6.Client;

import FirstSemestr.Java2.Lesson_6.IOThreads.MessageReader;
import FirstSemestr.Java2.Lesson_6.IOThreads.MessageTransmitter;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private Socket socket;

    public Client() {
        try {
            socket = new Socket("localhost", 8989);

            new MessageTransmitter(socket).start();
            new MessageReader(socket).start();

        } catch (IOException e) {
            throw new RuntimeException("SWW", e);
        }
    }
}
