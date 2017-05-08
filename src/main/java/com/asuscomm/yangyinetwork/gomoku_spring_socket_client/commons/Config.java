package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.commons;

/**
 * Created by jaeyoung on 2017. 5. 8..
 */
public interface Config {
    String SOCKET_URL = "ws://{host}:{port}/gomoku";
    String SOCKET_HOST = "localhost";
    int SOCKET_PORT = 8080;
    //        String host = "52.78.111.146";
//        int port = 80;

    String SOCKET_CHANNEL = "1";

    String GENERAL_RECEIVE_TOPIC = "/topic/command/general";
    String GENERAL_SEND_TOPIC = "/app/general/command";
}
