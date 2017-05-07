package com.asuscomm.yangyinetwork.gomoku_spring_socket_client;

import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.consts.Commands;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.domain.Channel;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.domain.Command;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.domain.CommandReply;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.domain.CommandReplyWithChannel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import org.springframework.web.socket.sockjs.frame.Jackson2SockJsMessageCodec;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by nick on 30/09/2015.
 */
public class SpringClient {

    private static Logger logger = Logger.getLogger(SpringClient.class);

    private final static WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
    private static StompSession stompSession;

    public ListenableFuture<StompSession> connect() {

        Transport webSocketTransport = new WebSocketTransport(new StandardWebSocketClient());
        List<Transport> transports = Collections.singletonList(webSocketTransport);

        SockJsClient sockJsClient = new SockJsClient(transports);
        sockJsClient.setMessageCodec(new Jackson2SockJsMessageCodec());

        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);

        String url = "ws://{host}:{port}/gomoku";
//        String host = "52.78.111.146";
//        int port = 80;
        String host = "localhost";
        int port = 8080;
        return stompClient.connect(url, headers, new MyHandler(), host, port);
    }

    public void subscribeGeneralCommand(StompSession stompSession) throws ExecutionException, InterruptedException {
        stompSession.subscribe("/topic/command/general", new StompFrameHandler() {

            public Type getPayloadType(StompHeaders stompHeaders) {
                return byte[].class;
            }

            public void handleFrame(StompHeaders stompHeaders, Object o) {
                logger.info("Received greeting " + new String((byte[]) o));

                ObjectMapper mapper = new ObjectMapper();
                String result = new String((byte[]) o);
                CommandReplyWithChannel commandReply = null;
                try {
                    commandReply = mapper.readValue(result, CommandReplyWithChannel.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Channel channel = commandReply.getContent();
                try {
                    subscribeChannelCommand(channel);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                logger.info("why ??"+commandReply);
                logger.info("why ??"+commandReply.getContent());
                logger.info("why ??"+commandReply.getContent().getId());
                String id = commandReply.getContent().getId();
                logger.info("why ??"+id);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sendChannelCommand(id);
            }
        });
    }

    public void subscribeChannelCommand(Channel channel) throws ExecutionException, InterruptedException {
        String topic = "/topic/command/"+channel.getId();
        logger.info("subscribe channel "+topic);
        stompSession.subscribe(topic, new StompFrameHandler() {

            public Type getPayloadType(StompHeaders stompHeaders) {
                return byte[].class;
            }

            public void handleFrame(StompHeaders stompHeaders, Object o) {
                logger.info("Received channel command " + new String((byte[]) o));

                ObjectMapper mapper = new ObjectMapper();
                String result = new String((byte[]) o);
                CommandReply commandReply = null;
                try {
                    commandReply = mapper.readValue(result, CommandReply.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                logger.info("Received channel command "+commandReply.toString() );
            }
        });
    }


    public void sendGeneralCommand(StompSession stompSession) {
        Command command = new Command(Commands.TO_SERVER.JOIN_SOMEWHERE,
                Commands.TO_SERVER.JOIN_SOMEWHERE,
                "");

        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(command);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        stompSession.send("/app/general/command", json.getBytes());
    }

    public void sendChannelCommand(String channel) {
        logger.info("SpringClient/sendChannelCommand: "+channel);
        Command command = new Command("aaaa",
                "AAAA",
                "");

        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(command);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        logger.info("SpringClient/sendChannelCommand: "+channel);
        stompSession.send("/app/"+channel+"/command", json.getBytes());
    }

    private class MyHandler extends StompSessionHandlerAdapter {
        public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {
            logger.info("Now connected");
        }
    }
    
    public static void main(String[] args) throws Exception {
        SpringClient springClient = new SpringClient();

        ListenableFuture<StompSession> f = springClient.connect();
        stompSession = f.get();

        logger.info("Subscribing to greeting topic using session " + stompSession);
        springClient.subscribeGeneralCommand(stompSession);

        logger.info("Sending hello message" + stompSession);
        springClient.sendGeneralCommand(stompSession);
        Thread.sleep(60000);
    }
    
}
