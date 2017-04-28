package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.AxisSet;
import domain.Data;
import domain.dto.AxisSetData;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.io.IOException;
import java.net.URISyntaxException;

import static consts.CONFIG.SOCKET_SESRVER_FULLDOMAIN;

/**
 * Created by jaeyoung on 2017. 4. 29..
 */
public class SocketclientImpl implements Socketclient {
    private Socket socket;
    private ObjectMapper mapper = new ObjectMapper();
    private Listener listener;

    public SocketclientImpl() {
        runSocketThread.start();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    private Runnable runSocketRunnable = new Runnable() {
        public void run() {
            try {
                socket = IO.socket(SOCKET_SESRVER_FULLDOMAIN);
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
                        try {

                            Data obj = mapper.readValue(objects[0].toString(), Data.class);
                            System.out.println("to_server "+obj.toString());
                        } catch(IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).on("to_client", new Emitter.Listener() {
                    public void call(Object... objects) {
                        try {
                            Data obj = mapper.readValue(objects[0].toString(), Data.class);
                            System.out.println("to_client "+obj.toString());
                            listener.onReceiveCommandFromServer(obj);
                        } catch(IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                socket.connect();
            } catch(URISyntaxException e){
                e.printStackTrace();
            }
        }
    };
    private Thread runSocketThread = new Thread(runSocketRunnable);

    private void sendDataToServer(Data data) {
        System.out.println("service.Socketclient/sendDataToServer : " + data.toString());
        try {
            String jsonInString = mapper.writeValueAsString(data);
            socket.emit("to_server", jsonInString);
        }catch(JsonProcessingException e){
            e.printStackTrace();
        }
    }

    public void sendNewStonePoint(int axisX, int axisY){
        Data data = new Data("NewStonePoint",
                "NewStonePoint is "+axisX+", "+axisY,
                new AxisSet(axisX, axisY, true));
        sendDataToServer(data);
    }

    public void sendNickName(String nickname) {
        Data data = new Data("Nickname",
                            "Nickname is "+nickname,
                            nickname);
        sendDataToServer(data);
    }
}
