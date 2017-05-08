package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.socket;

import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.Main;
import org.apache.log4j.Logger;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

/**
 * Created by jaeyoung on 2017. 5. 8..
 */
public class SocketHandler extends StompSessionHandlerAdapter {
    private Logger logger = Logger.getLogger(Main.class);

    public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {
        logger.info("Now connected");
    }
}