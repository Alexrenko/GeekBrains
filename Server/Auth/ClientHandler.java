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
    private boolean isAuth;

    public ClientHandler(Socket socket, Server server) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {
                    listen();
                } catch (SocketException e) {
                    server.disconnectedMessage(socket);
                    server.broadcast(name, name + " logged off.", true);
                    server.unsubscribe(this);
                } catch (IOException e) {
                    throw new RuntimeException("SWW", e);
                }
            }).start();

        } catch (IOException e) {
            throw new RuntimeException("SWW", e);
        }
    }

    private void listen() throws IOException{
        try {
            while (true) {
                Authentication();
                readMessage();
            }
        } finally {
            in.close();
            out.close();
            socket.close();
        }
    }

    private void Authentication() throws IOException {
        while (true) {
            timeOut(120);
            String inputMessage = in.readUTF();
            if (inputMessage.startsWith("-auth")) {
                AuthEntry entry = checkLogin(inputMessage);
                if (entry != null) {
                    signIn(entry);
                    return;
                }
            } else {
                sendMessage("Invalid authentication request.");
            }
        }
    }

    private AuthEntry checkLogin(String inputMessage) {
        String[] creds = getCreds(inputMessage);
        if (creds != null) {
            AuthEntry entry = checkAuthEntry(creds[1], creds[2]);
            if (entry != null) {
                return entry;
            }
        }
        return null;
    }

    private String[] getCreds(String inputMessage) {
        String[] creds = inputMessage.split("\\s");
        if (creds.length >= 3) {
            return creds;
        } else {
            sendMessage("Invalid authentication request.");
        }
        return null;
    }

    private AuthEntry checkAuthEntry(String login, String password) {
        AuthEntry maybeAuthEntry = server.getAuthenticationService()
                .findUserByCredentials(login, password);
        if (maybeAuthEntry != null) {
            if (server.isNicknameFree(maybeAuthEntry.getNickname())) {
                return maybeAuthEntry;
            } else {
                sendMessage("Current user is already logged-in");
            }
        }else {
            sendMessage("Unknown user. Incorrect login/password");
        }
        return null;
    }

    private void signIn(AuthEntry authEntry) {
        name = authEntry.getNickname();
        sendMessage(name + ", welcome to the Chat");
        server.broadcast(name, name + " logged in.", true);
        server.subscribe(this);
        isAuth = true;
    }

    private void timeOut(int seconds) {
        new Thread(() -> {
            try {
                Thread.sleep(seconds * 1000);
                if (!isAuth) {
                    if (!socket.isClosed()) {
                        sendMessage("Time out.");
                        in.close();
                        out.close();
                        socket.close();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException("SWW", e);
            }
        }).start();
    }

    public void readMessage() throws IOException {
        while (true) {
            String message = in.readUTF();
            if (message.startsWith("/w")) {
                sendPersonalMessage(message);
            } else if (message.startsWith("-change")){
                changeLogin(message);
            } else if (message.startsWith("-exit")){
                exit();
                break;
            } else {
                server.broadcast(name, name + ": " + message, false);
            }
        }
    }

    private void changeLogin(String message) {
        AuthEntry entry = checkLogin(message);
        if (entry != null) {
            String lastName = name;
            name = entry.getNickname();
            sendMessage("You changed your nickname to " + name + ".");
            server.broadcast(name, lastName + " changed his nickname to " + name + ".", true);
        }
    }

    private void exit() {
        server.broadcast(name, name + " logged off.", true);
        server.unsubscribe(this);
        isAuth = false;
        sendMessage("You are out of chat.");
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
