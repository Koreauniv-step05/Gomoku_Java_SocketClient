package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.game;

import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.channel.ChannelControllerImpl;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
public interface GameController {
    interface onNewStoneListener {
        void onNewStoneFromClient(int[] stonePoint);
    }
    void findSolution(int[][] board);
    void setStoneType(int stoneType);
}
