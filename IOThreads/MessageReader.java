package FirstSemestr.Java2.Lesson_6.IOThreads;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class MessageReader extends Thread {
    private DataInputStream in;

    public MessageReader(Socket client) {
        try {
            in = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException("SWW", e);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println('\t' + in.readUTF());
            } catch (SocketException e){
                System.out.println("Connection closed.");
                break;
            } catch (IOException e) {
                throw new RuntimeException("SWW", e);
            }
        }
    }
}
