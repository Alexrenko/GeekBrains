package FirstSemestr.Java3.Lesson_4.Chat_with_ExecutorService.Client.Network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
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
            throw new RuntimeException("SWW with socket", e);
        } catch (IOException e) {
            throw new RuntimeException("SWW during send", e);
        }
    }

    @Override
    public String receive() throws EOFException {
        try {
            return in.readUTF();
        } catch (EOFException e) {
            throw new EOFException();
        } catch (IOException e) {
            throw new RuntimeException("SWW during receive", e);
        }
    }

}
