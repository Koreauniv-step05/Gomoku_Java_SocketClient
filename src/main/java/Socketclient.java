import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.net.URISyntaxException;

/**
 * Created by jaeyoung on 2017. 4. 28..
 */
public class Socketclient {
    private Socket socket;

    public Socketclient() {
        try {
            socket = IO.socket("http://localhost:8002");
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

                public void call(Object... objects) {
                    System.out.println("EVENT_CONNECT");
                    socket.send("Java Client Connected"); // server log
                }
            }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                public void call(Object... objects) {

                }
            }).on(Socket.EVENT_RECONNECT, new Emitter.Listener() {
                public void call(Object... objects) {

                }
            }).on("to_server", new Emitter.Listener() {
                public void call(Object... objects) {
                    System.out.println("to_server"+objects);
                }
            }).on("to_client", new Emitter.Listener() {
                public void call(Object... objects) {
                    System.out.println("to_client"+objects);
                }
            });

            socket.connect();
        } catch(URISyntaxException e){
            e.printStackTrace();
        }
    }
}
