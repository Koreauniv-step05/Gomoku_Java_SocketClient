package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.client.socket.domain;

/**
 * Created by jaeyoung on 2017. 5. 8..
 */
public class StonePoint {
    private int[] stonePoint;
    private int stoneType;

    public StonePoint() {
    }

    public StonePoint(int[] stonePoint, int stoneType) {
        this.stonePoint = stonePoint;
        this.stoneType = stoneType;
    }

    public int[] getStonePoint() {
        return stonePoint;
    }

    public void setStonePoint(int[] stonePoint) {
        this.stonePoint = stonePoint;
    }

    public int getStoneType() {
        return stoneType;
    }

    public void setStoneType(int stoneType) {
        this.stoneType = stoneType;
    }
}
