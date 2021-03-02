package FirstSemestr.Java2.Lesson_7_8.Server.Auth;

import FirstSemestr.Java2.Lesson_7_8.Server.Server;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

/**
 * 1. Existing and unique user (accept)
 * 2. Unknown user (reject)
 * 3. Already logged-in (reject)
 * 4. Send message to itself
 * 5. Broadcast message upon success login + basic message
 * 6. Send a personal message
 * 7. Exclusion from chat by key "-exit"
 */

public class ClientHandler {
    private final Server server;
    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;
    private String name;

    public ClientHandler(Socket socket, Server server) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(this::listen).start();

        } catch (IOException e) {
            throw new RuntimeException("SWW", e);
        }
    }

    private void listen(){
        try {
            while (true) {
                doAuth();
                readMessage();
            }
        }catch (SocketException e) {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            server.disconnectedMessage(socket);
        } catch (EOFException e) {
            server.disconnectedMessage(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doAuth() throws IOException {
        while (true) {
            String input = in.readUTF();
            if (input.startsWith("-auth")) {
                String[] credentioals = input.split("\\s");
                AuthEntry maybeAuthEntry = server.getAuthenticationService()
                        .findUserByCredentials(credentioals[1], credentioals[2]);
                if (maybeAuthEntry != null) {
                    if (server.isNicknameFree(maybeAuthEntry.getNickname())) {
                        sendMessage("CMD: auth is OK");
                        name = maybeAuthEntry.getNickname();
                        server.broadcast(name + " logged in.");
                        server.subscribe(this);
                        return;
                    } else {
                        sendMessage("Current user is already logged-in");
                    }
                }else {
                    sendMessage("Unknown user. Incorrect login/password");
                }
            } else {
                sendMessage("Invalid authentication request.");
            }
        }
    }

    public void readMessage() throws IOException {
        while (true) {
            String message = in.readUTF();
            if (message.startsWith("/w")) {
                sendPersonalMessage(message);
            } else if (message.startsWith("-exit")){
                server.broadcast(name + " logged off.");
                server.unsubscribe(this);
                break;
            } else {
                server.broadcast(name + ": " + message);
            }
        }
    }

    private void sendPersonalMessage(String message) {
        String[] arrMessage = message.split("\\s");
        if (server.isNicknameFree(arrMessage[1])) {
            sendMessage(arrMessage[1] + " isn`t registered in the chat.");
        } else {
            String newMessage = "";
            for (int i = 2; i < arrMessage.length; i++) {
                newMessage += arrMessage[i] + " ";
            }
            server.send(arrMessage[1], newMessage);
        }
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }
}
