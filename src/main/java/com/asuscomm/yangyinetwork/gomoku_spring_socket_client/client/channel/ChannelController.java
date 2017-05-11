package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.client.channel;

import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.client.game.GameController;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.client.socket.domain.StonePoint;

/**
 * Created by jaeyoung on 2017. 5. 8..
 */
public interface ChannelController extends GameController.onNewStoneListener {
    interface Listener{
        void onYourTurn(int stoneType, int[][] board);
        void setStoneType(int stoneType);
    }

    void addListener(Listener listener);
    void onNewStoneFromClient(StonePoint stonePoint);
}
