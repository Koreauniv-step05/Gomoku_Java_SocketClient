package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.consts;

/**
 * Created by jaeyoung on 2017. 5. 10..
 */
public interface GAME_RULE {
    long TIME_LIMITS = 7000;
    long TIME_LIMITS_BUFFER = 500;
    long REAL_TIME_LIMITS = TIME_LIMITS-TIME_LIMITS_BUFFER;
    long TIMELIMIT_CHECK_PERIOD = 250;
}
