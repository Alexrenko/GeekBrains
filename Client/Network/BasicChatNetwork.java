package FirstSemestr.Java2.Lesson_7_8.Client.Network;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class BasicChatNetwork implements ClientNetwork {
    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;

    public BasicChatNetwork(String host, int port) {
        try {
            socket = new Socket(host, port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException("SWW during establishing", e);
        }
    }

    @Override
    public void send(String message) {
        try {
            out.writeUTF(message);
        } catch (SocketException e) {
            try {
                out.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (IOException e) {
            throw new RuntimeException("SWW during send", e);
        }
    }

    @Override
    public String receive() {
        try {
            return in.readUTF();
        } catch (EOFException e) {
            try {
                in.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (IOException e) {
            throw new RuntimeException("SWW during receive", e);
        }
        return null;
    }
}
