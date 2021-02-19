package FirstSemestr.Java2.Lesson_6.Server;

import FirstSemestr.Java2.Lesson_6.IOThreads.MessageReader;
import FirstSemestr.Java2.Lesson_6.IOThreads.MessageTransmitter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket client;

    public Server() {
        try {
            serverSocket = new ServerSocket(8989);
            System.out.println("Server is running on and waiting for a connection...");

            client = serverSocket.accept();
            new MessageReader(client).start();
            new MessageTransmitter(client).start();

        } catch (IOException e) {
            throw new RuntimeException("SWW", e);
        }
    }
}
