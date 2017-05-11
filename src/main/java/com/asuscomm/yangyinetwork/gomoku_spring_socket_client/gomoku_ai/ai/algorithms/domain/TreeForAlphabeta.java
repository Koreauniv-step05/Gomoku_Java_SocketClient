package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.ai.algorithms.domain;

import java.util.List;

/**
 * Created by jaeyoung on 2017. 5. 11..
 */
public interface TreeForAlphabeta {
    double getEval();
    int getChildrenSize();
    List<?> getChildren();
}
