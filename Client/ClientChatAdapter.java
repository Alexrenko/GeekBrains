package FirstSemestr.Java2.Lesson_7_8.Client;

import FirstSemestr.Java2.Lesson_7_8.Client.GUI.ClientChatFrame;
import FirstSemestr.Java2.Lesson_7_8.Client.Network.BasicChatNetwork;
import FirstSemestr.Java2.Lesson_7_8.Client.Network.ClientNetwork;

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
            while (true) {
                frame.append(network.receive());
            }

        }).
                start();
    }
}
