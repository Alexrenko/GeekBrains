package FirstSemestr.Java3.Lesson_4.Chat_with_ExecutorService.Client;

import FirstSemestr.Java3.Lesson_4.Chat_with_ExecutorService.Client.GUI.ClientChatFrame;
import FirstSemestr.Java3.Lesson_4.Chat_with_ExecutorService.Client.Network.BasicChatNetwork;
import FirstSemestr.Java3.Lesson_4.Chat_with_ExecutorService.Client.Network.ClientNetwork;

import java.io.EOFException;
import java.util.function.Consumer;

public class ClientChatAdapter {
    private final ClientNetwork network;
    private final ClientChatFrame frame;

    public ClientChatAdapter(String host, int port) {
        this.network = new BasicChatNetwork(host, port);
        this.frame = new ClientChatFrame(new Consumer<String>() {
            @Override
            public void accept(String message) {
                network.send(message);
            }
        });

        receive();
    }

    private void receive() {
        new Thread(()-> {
            try {
                while (true) {
                    String str = network.receive();
                    frame.append(str);
                }
            } catch (EOFException e) {
                frame.append("Disconnected.");
            }
        }).
                start();
    }
}

