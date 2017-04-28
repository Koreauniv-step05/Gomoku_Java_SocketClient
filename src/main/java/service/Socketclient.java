package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.AxisSet;
import domain.dto.AxisSetData;
import domain.Data;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by jaeyoung on 2017. 4. 28..
 */
public interface Socketclient {
    interface Listener {
        void onReceiveCommandFromServer(Data data);
    }
    void setListener(Listener listener);
    void sendNewStonePoint(int axisX, int axisY);
    void sendNickName(String nickname);
}
