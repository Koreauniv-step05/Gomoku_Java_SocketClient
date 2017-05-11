package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.client.socket;

import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.Main;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.client.domain.Channel;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.client.domain.CommandReply;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.client.domain.CommandReplyWithChannel;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.client.socket.consts.Commands;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.client.socket.domain.SocketMessage;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.client.socket.domain.SocketOnYourTurn;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
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

import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.client.commons.Config.*;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.client.socket.consts.Commands.CHANNEL.GENERAL_TO_CLIENT.ON_YOUR_TURN;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.client.socket.consts.Commands.GENERAL.JOIN_CHANNEL;

/**
 * Created by jaeyoung on 2017. 5. 8..
 */
public class SocketClientImpl implements SocketClient {
    private Logger logger = Logger.getLogger(Main.class);
    ListenableFuture<StompSession> f;
    private final WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
    private StompSession stompSession;
    private onSocketListener mListener;

    String url = SOCKET_URL;
    String host = SOCKET_HOST;
    int port = SOCKET_PORT;
    String mChannelId;


    public SocketClientImpl() {
        this.f = this.connect();
        try {
            this.stompSession = this.f.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        getChannel();
    }

    private ListenableFuture<StompSession> connect() {
        Transport webSocketTransport = new WebSocketTransport(new StandardWebSocketClient());
        List<Transport> transports = Collections.singletonList(webSocketTransport);

        SockJsClient sockJsClient = new SockJsClient(transports);
        sockJsClient.setMessageCodec(new Jackson2SockJsMessageCodec());

        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
        return stompClient.connect(url, headers, new SocketHandler(), host, port);
    }

    private void processGeneralCommand(CommandReply commandReply) {
        String command = commandReply.getCommand();
        Channel channel = null;
        if(Commands.GENERAL.GENERAL_TO_CLIENT.JOINED_CHANNEL.equals(command)) {
            channel = (Channel)commandReply.getContent();
            mListener.onGetChannel(channel.getLastUser().getStoneType());
            subscribeChannelCommand(channel.getId());
        }
    }

    private void sendJoinChannel(String channelId) {
        SocketMessage socketMessage = new SocketMessage(JOIN_CHANNEL, JOIN_CHANNEL, channelId);
        sendGeneralSocketMessage(socketMessage);
    }

    public void sendChannelSocketMessage(SocketMessage socketMessage) {
        if(this.mChannelId != null) {
            logger.info("sendChannelSocketMessage"+socketMessage.toString());
            sendSocketMessage("/app/"+mChannelId+"/to_server", socketMessage);
        } else {
            logger.info("sendChannelSocketMessage mChannelId is null");
        }
    }

    private void sendGeneralSocketMessage(SocketMessage socketMessage) {
        sendSocketMessage(GENERAL_SEND_TOPIC, socketMessage);
    }

    private void sendSocketMessage(String topic, SocketMessage socketMessage) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(socketMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        stompSession.send(topic, json.getBytes());
    }

    private void subscribeGeneralCommand() {
        logger.info("subscribeGeneralCommand");
        stompSession.subscribe(GENERAL_RECEIVE_TOPIC, new StompFrameHandler() {
            public Type getPayloadType(StompHeaders stompHeaders) {
                return byte[].class;
            }

            public void handleFrame(StompHeaders stompHeaders, Object o) {
                logger.info("Received greeting " + new String((byte[]) o));

                CommandReply commandReply = null;
                ObjectMapper mapper = new ObjectMapper();
                try {
                    commandReply = mapper.readValue(new String((byte[]) o), CommandReplyWithChannel.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                processGeneralCommand(commandReply);
            }
        });
    }

    public void subscribeChannelCommand(String channelId) {
        this.mChannelId = channelId;
        String topic = "/topic/to_client/"+channelId;
        logger.info("Subscribing to channel [{}] command using session " + stompSession);

        this.stompSession.subscribe(topic, new StompFrameHandler() {
            public Type getPayloadType(StompHeaders stompHeaders) {
                return byte[].class;
            }

            public void handleFrame(StompHeaders stompHeaders, Object o) {
                logger.info("Received greeting " + new String((byte[]) o));

                SocketMessage socketMessage = null;
                ObjectMapper mapper = new ObjectMapper();
                try {
                    socketMessage = mapper.readValue(new String((byte[]) o), SocketMessage.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (ON_YOUR_TURN.equals(socketMessage.getCommand())) {
                    try {
                        socketMessage = (SocketMessage)mapper.readValue(new String((byte[]) o), SocketOnYourTurn.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                mListener.onToClient(socketMessage);
            }
        });
    }

    public void getChannel() {
        logger.info("getChannel");
        this.subscribeGeneralCommand();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.sendJoinChannel(SOCKET_CHANNEL);
    }

    public void setListener(onSocketListener listener) {
        this.mListener = listener;
    }
}
