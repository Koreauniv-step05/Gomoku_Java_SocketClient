package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.game.ai.utils;

import java.util.List;

/**
 * Created by jaeyoung on 2017. 5. 8..
 */
public class ChooseRandomly {
    public static int[] chooseRandomlyInStonePoints(List<int[]> stonePoints) {
        int siz = stonePoints.size();
        double rnd = Math.random()*siz;
        int idx = (int)(rnd % siz);
        return stonePoints.get(idx);
    }
}