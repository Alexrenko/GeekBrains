package FirstSemestr.Java2.Lesson_6.IOThreads;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class MessageTransmitter extends Thread {
    private DataOutputStream out;
    private Scanner scanner;

    public MessageTransmitter(Socket socket) {
        try {
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException("SWW", e);
        }
    }

    @Override
    public void run() {
        scanner = new Scanner(System.in);
        while (true) {
            try {
                out.writeUTF(scanner.nextLine());
            } catch (SocketException e) {
                System.out.println("Connection closed.");
                break;
            } catch (IOException e) {
                throw new RuntimeException("SWW", e);
            }
        }
    }
}
