package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.game.ai;

/**
 * Created by jaeyoung on 2017. 5. 8..
 */
public interface Ai {
    interface OnSolutionListener {
        void onSolution(int[] stonePoint);
    }
    void findSolution(int[][] board, OnSolutionListener listener);
}
