package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.game;

/**
 * Created by jaeyoung on 2017. 5. 10..
 */
public class StonePoint {
    int[] stonePoints;
    int stoneType;

    public StonePoint(int[] stonePoints, int stoneType) {
        this.stonePoints = stonePoints;
        this.stoneType = stoneType;
    }

    public int[] getStonePoints() {
        return stonePoints;
    }

    public void setStonePoints(int[] stonePoints) {
        this.stonePoints = stonePoints;
    }

    public int getStoneType() {
        return stoneType;
    }

    public void setStoneType(int stoneType) {
        this.stoneType = stoneType;
    }
}
