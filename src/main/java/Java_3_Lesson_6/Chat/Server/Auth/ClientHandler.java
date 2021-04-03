package Java_3_Lesson_6.Chat.Server.Auth;

import Java_3_Lesson_6.Chat.Server.Server;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 1. Existing and unique user (accept)
 * 2. Unknown user (reject)
 * 3. Already logged-in (reject)
 * 4. Send message to itself
 * 5. Broadcast message upon success login + basic message
 * 6. Send a personal message
 * 7. Exclusion from chat by key "-exit"
 * 8. Change nickname by key "-change"
 * 9. keeping client history
 */

public class ClientHandler {
    private final Server server;
    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;
    private String name;
    private boolean isAuth;
    private File logFile;
    private boolean isLogging;
    private final int requiredLogLines = 100;
    private static final Logger LOGGER_CLIENT_HANDLER = LogManager.getLogger(ClientHandler.class);

    public ClientHandler(Socket socket, Server server) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            server.getExecutorService().execute(new Thread(() -> {
                try {
                    listen();
                } catch (SocketException e) {
                    server.disconnectedMessage(socket);
                    server.broadcast(name, name + " logged off.", true);
                    server.unsubscribe(this);
                } catch (IOException e) {
                    LOGGER_CLIENT_HANDLER.error("SWW", e);
                    throw new RuntimeException("SWW", e);
                }
            }));

        } catch (IOException e) {
            LOGGER_CLIENT_HANDLER.error("SWW", e);
            throw new RuntimeException("SWW", e);
        }
    }

    private void listen() throws IOException {
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
                String[] creds = getCreds(inputMessage);
                String login = "";
                String password = "";
                if (creds != null) {
                    login = creds[1];
                    password = creds[2];
                }
                LOGGER_CLIENT_HANDLER.info(
                        "Invalid authentication request. Sent creds: " + login + "/" + password
                );
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
            if (server.isNicknameFree(maybeAuthEntry.getNickname(), socket)) {
                return maybeAuthEntry;
            } else {
                LOGGER_CLIENT_HANDLER.info("Current user is already logged-in");
                sendMessage("Current user is already logged-in");
            }
        }else {
            LOGGER_CLIENT_HANDLER.info(
                    "Unknown user. Incorrect login/password. Sent creds: " + login + "/" + password
            );
            sendMessage("Unknown user. Incorrect login/password");
        }
        return null;
    }

    private void signIn(AuthEntry authEntry) throws IOException {
        name = authEntry.getNickname();
        logFile = new File("history_" + name + ".txt");
        logFile.createNewFile();
        LOGGER_CLIENT_HANDLER.info(name + " was authenticated");
        sendMessage(getHistoryLines());
        sendMessage(name + ", welcome to the Chat");
        server.broadcast(name, name + " logged in.", true);
        server.subscribe(this);
        isAuth = true;
        isLogging = true;
    }

    private void timeOut(int seconds) {
        new Thread(() -> {
            try {
                Thread.sleep(seconds * 1000);
                if (!isAuth) {
                    if (!socket.isClosed()) {
                        sendMessage("Time out.");
                        LOGGER_CLIENT_HANDLER.info(socket.toString() + " time out");
                        in.close();
                        out.close();
                        socket.close();
                    }
                }
            } catch (InterruptedException e) {
                LOGGER_CLIENT_HANDLER.error("SWW", e);
                e.printStackTrace();
            } catch (IOException e) {
                LOGGER_CLIENT_HANDLER.error("SWW", e);
                throw new RuntimeException("SWW", e);
            }
        }).start();
    }

    public void readMessage() throws IOException {
        while (true) {
            String message = in.readUTF();
            if (isLogging) {
                writeHistory(message);
            }
            if (message.startsWith("/w")) {
                sendPersonalMessage(message);
            } else if (message.startsWith("-change")){
                LOGGER_CLIENT_HANDLER.info(name + " sent system message: " + message);
                changeLogin(message);
            } else if (message.startsWith("-exit")){
                LOGGER_CLIENT_HANDLER.info(name + " sent system message: " + message);
                exit();
                break;
            } else {
                server.broadcast(name, name + ": " + message, false);
            }
        }
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
            if (isLogging) {
                writeHistory(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendPersonalMessage(String message) {
        String[] arrMessage = message.split("\\s");
        if (server.isNicknameFree(arrMessage[1], socket)) {
            sendMessage(arrMessage[1] + " isn`t registered in the chat.");
        } else {
            String newMessage = "";
            for (int i = 2; i < arrMessage.length; i++) {
                newMessage += arrMessage[i] + " ";
            }
            server.send(arrMessage[1], newMessage, name);
            if (isLogging) {
                writeHistory(newMessage);
            }
        }
    }

    private void writeHistory(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))){
            if (logFile.length() == 0) {
                writer.write(message);
            } else {
                writer.write('\n' + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readHistory(File file) {
        String logFile = "";
        try (FileReader reader = new FileReader(file)) {
            int c;
            while ((c = reader.read()) != -1) {
                logFile += ((char) c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException("SWW during read log", e);
        }
        return logFile;
    }

    private String getHistoryLines() {
        String outputLines = "";
        String log = readHistory(logFile);
        String[] allLines = log.split("\n");
        if (allLines.length > requiredLogLines) {
            for (int i = requiredLogLines; i > 0 ; i--) {
                outputLines += allLines[allLines.length - i] + '\n';
            }
            return outputLines;
        } else {
            return log;
        }
    }

    private void changeLogin(String message) {
        AuthEntry entry = checkLogin(message);
        if (entry != null) {
            String lastName = name;
            name = entry.getNickname();
            LOGGER_CLIENT_HANDLER.info(lastName + " changed his nickname to " + name);
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

    public String getName() {
        return name;
    }


}
