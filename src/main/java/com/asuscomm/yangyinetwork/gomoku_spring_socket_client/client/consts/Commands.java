package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.client.consts;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
public interface Commands {
    interface TO_SERVER {
        String JOIN_CHANNEL = "JOIN_CHANNEL";
        String JOIN_SOMEWHERE = "JOIN_SOMEWHERE";
        String GET_CHANNELS = "GET_CHANNELS";
    }

    interface TO_CLIENT {
        String JOINED_CHANNEL = "JOINED_CHANNEL";
    }
}
