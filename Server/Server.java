package FirstSemestr.Java2.Lesson_7_8.Server;

import FirstSemestr.Java2.Lesson_7_8.Server.Auth.AuthenticationService;
import FirstSemestr.Java2.Lesson_7_8.Server.Auth.ClientHandler;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Server {
    private final ServerSocket serverSocket;
    private final AuthenticationService authenticationService;
    private final Set<ClientHandler> handlers;

    public Server() {
        authenticationService = new AuthenticationService();
        handlers = new HashSet<>();
        try {
            serverSocket = new ServerSocket(8989);
            init();
        } catch (IOException e) {
            throw new RuntimeException("SWW", e);
        }

    }

    private void init() throws IOException {
        while (true) {
            System.out.println("Server is waiting for a connection...");
            Socket client = serverSocket.accept();
            System.out.println("Client accepted " + client);
            new ClientHandler(client, this);
        }
    }

    public void disconnectedMessage(Socket clientSocket) {
        System.out.println("client disconnected " + clientSocket.toString());
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public synchronized boolean isNicknameFree(String nickname) {
        for (ClientHandler handler : handlers) {
            if (handler.getName().equals(nickname)) {
                return false;
            }
        }
        return true;
    }

    public synchronized void broadcast(String message) throws IOException {
        for (ClientHandler handler : handlers) {
            handler.sendMessage(message);
        }
    }

    public void send(String nickname, String message) {
        for (ClientHandler handler : handlers) {
            if (handler.getName().equals(nickname)) {
                handler.sendMessage(message);
                return;
            }
        }
    }

    public synchronized void subscribe(ClientHandler handler) {
        handlers.add(handler);
    }

    public synchronized void unsubscribe(ClientHandler handler) {
        handlers.remove(handler);
    }
}
