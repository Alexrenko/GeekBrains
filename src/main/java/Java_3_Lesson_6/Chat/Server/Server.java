package Java_3_Lesson_6.Chat.Server;

import Java_3_Lesson_6.Chat.Server.Auth.AuthenticationService;
import Java_3_Lesson_6.Chat.Server.Auth.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {
    private final ServerSocket serverSocket;
    private final AuthenticationService authenticationService;
    private final ExecutorService executorService;
    private final Set<ClientHandler> handlers;
    private final int numberOfThreads = 2;
    private static final Logger LOGGER_SERVER = LogManager.getLogger(Server.class);

    public Server() {
        authenticationService = new AuthenticationService();
        executorService = Executors.newFixedThreadPool(numberOfThreads);
        handlers = new HashSet<>();
        try {
            serverSocket = new ServerSocket(8989);
            init();
        } catch (IOException e) {
            LOGGER_SERVER.error("SWW with Server", e);
            throw new RuntimeException("SWW", e);
        }
    }

    private void init() throws IOException {
        while (true) {
            System.out.println("Server is waiting for a connection...");
            LOGGER_SERVER.info("Server is running");
            Socket socket = serverSocket.accept();
            System.out.println("Client accepted " + socket);
            LOGGER_SERVER.info("Client accepted " + socket);
            new ClientHandler(socket, this);
        }
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void disconnectedMessage(Socket clientSocket) {
        System.out.println("client disconnected " + clientSocket.toString());
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public synchronized boolean isNicknameFree(String nickname, Socket clientSocket) {
        for (ClientHandler handler : handlers) {
            if (handler.getName().equals(nickname)) {
                LOGGER_SERVER.debug("client " + clientSocket.toString());
                return false;
            }
        }
        return true;
    }

    public synchronized void broadcast(String name, String message, boolean SystemMessage) {
        for (ClientHandler handler : handlers) {
            if (!handler.getName().equals(name)) {
                if (SystemMessage) {
                    handler.sendMessage(message);
                    LOGGER_SERVER.debug(name + "system message: " + message);
                } else {
                    handler.sendMessage('\t' + message);
                    LOGGER_SERVER.info(name + ": " + message);
                }
            }
        }
    }

    public void send(String nickname, String message, String name) {
        for (ClientHandler handler : handlers) {
            if (handler.getName().equals(nickname)) {
                handler.sendMessage(message);
                LOGGER_SERVER.info("" + name + " personal to " + nickname + ": " + message);
                return;
            }
        }
    }

    public synchronized void subscribe(ClientHandler handler) {
        handlers.add(handler);
        LOGGER_SERVER.info("" + handler.getName() + " вошел в чат");
    }

    public synchronized void unsubscribe(ClientHandler handler) {
        handlers.remove(handler);
        LOGGER_SERVER.info("" + handler.getName() + " вышел из чата");
    }
}
