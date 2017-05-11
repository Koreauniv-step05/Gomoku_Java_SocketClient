package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.utils;

import java.util.ArrayList;
import java.util.List;

import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.consts.CONSTS.INF;

/**
 * Created by jaeyoung on 2017. 5. 11..
 */
public class Sort {
    public static List<Integer> sortedIndex(List<Double> before) {
        List<Integer> sorted = new ArrayList<Integer>();
        List<Double> beforeClone = new ArrayList<Double>();
        beforeClone.addAll(before);


        for (int i = 0; i < beforeClone.size(); i++) {
            double maximum = -INF;
            int maximum_idx = i;
            for (int j = i; j < beforeClone.size(); j++) {
                double temp = beforeClone.get(j);
                if (temp > maximum) {
                    beforeClone.set(j,beforeClone.get(i));
                    beforeClone.set(i,temp);
                    maximum = temp;
                    maximum_idx = j;
                }
            }
            sorted.add(maximum_idx);
        }

        return sorted;
    }
}
