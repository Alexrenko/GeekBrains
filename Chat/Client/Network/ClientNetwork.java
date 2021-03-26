package FirstSemestr.Chat.Client.Network;

import java.io.EOFException;

public interface ClientNetwork {
    void send(String message);
    String receive() throws EOFException;
}
