package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.consts;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
public interface GAME_BOARD {
    int DEFAULT_BOARD_SIZE = 19;
    int NONE_STONE = 0;
    int BLACK_STONE = 1;
    int WHITE_STONE = 2;
    int WALL_STONE = 5;

    int X = 0;
    int Y = 1;
    int NUM_CONNECT = 6;
}