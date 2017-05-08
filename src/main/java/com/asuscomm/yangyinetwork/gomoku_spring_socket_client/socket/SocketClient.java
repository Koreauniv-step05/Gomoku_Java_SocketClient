package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.socket;

import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.domain.Channel;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.socket.domain.SocketMessage;

/**
 * Created by jaeyoung on 2017. 5. 8..
 */
public interface SocketClient {
    interface onSocketListener {
        void onToClient(SocketMessage socketMessage);
        void onGetChannel(int stoneType);
    }
    void sendChannelSocketMessage(SocketMessage socketMessage);
    void setListener(onSocketListener listener);
}
