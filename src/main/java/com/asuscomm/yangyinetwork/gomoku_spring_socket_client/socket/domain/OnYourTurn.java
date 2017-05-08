package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.socket.domain;

/**
 * Created by jaeyoung on 2017. 5. 8..
 */
public class OnYourTurn {
    int stoneType;
    int[][] board;

    public OnYourTurn() {
    }

    public OnYourTurn(int stoneType, int[][] board) {
        this.stoneType = stoneType;
        this.board = board;
    }

    public int getStoneType() {
        return stoneType;
    }

    public void setStoneType(int stoneType) {
        this.stoneType = stoneType;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }
}
