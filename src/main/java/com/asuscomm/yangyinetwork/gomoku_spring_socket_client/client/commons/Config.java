package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.client.commons;

/**
 * Created by jaeyoung on 2017. 5. 8..
 */
public interface Config {
    String SOCKET_URL = "ws://{host}:{port}/gomoku";
//    String SOCKET_HOST = "localhost";int SOCKET_PORT =8080;
    String SOCKET_HOST = "52.78.111.146"; int SOCKET_PORT = 80;

    String SOCKET_CHANNEL = "5";

    String GENERAL_RECEIVE_TOPIC = "/topic/command/general";
    String GENERAL_SEND_TOPIC = "/app/general/command";

    long CHOOSE_STONE_DELAY = 1000;
}
