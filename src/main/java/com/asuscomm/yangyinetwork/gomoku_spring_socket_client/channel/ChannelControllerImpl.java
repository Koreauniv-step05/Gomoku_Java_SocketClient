package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.channel;

import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.Main;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.socket.SocketClient;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.socket.SocketClientImpl;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.socket.domain.OnYourTurn;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.socket.domain.SocketMessage;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.socket.domain.StonePoint;
import org.apache.log4j.Logger;

import java.util.LinkedHashMap;

import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.socket.consts.Commands.CHANNEL.GENERAL_TO_CLIENT.ON_YOUR_TURN;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.socket.consts.Commands.CHANNEL.ON_NEW_STONE_FROM_CLIENT;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
public class ChannelControllerImpl implements ChannelController, SocketClient.onSocketListener{
    private Logger logger = Logger.getLogger(Main.class);

    private SocketClient mSocketClient;
    private Listener mListener;

    public ChannelControllerImpl() {
        this.mSocketClient = new SocketClientImpl();
        this.mSocketClient.setListener(this);
    }

    public void addListener(Listener listener) {
        this.mListener = listener;
    }

    public void onNewStoneFromClient(StonePoint newStonePoint) {
        SocketMessage socketMessage= new SocketMessage(ON_NEW_STONE_FROM_CLIENT,ON_NEW_STONE_FROM_CLIENT,newStonePoint);
        mSocketClient.sendChannelSocketMessage(socketMessage);
    }

    public void onToClient(SocketMessage socketMessage) {
        String command = socketMessage.getCommand();
        logger.info("onToClient command = "+command);
        if (ON_YOUR_TURN.equals(command)) {
            int[][] board = { {0,0}, {0,0} };
//            int[][] board = (int[][])((LinkedHashMap) socketMessage.getContent()).get("board");
            logger.info("on to client ON_YOUR_TURN before");
            OnYourTurn onYourTurn  = (OnYourTurn)socketMessage.getContent();


            logger.info("on to client ON_YOUR_TURN after");
            mListener.onYourTurn(onYourTurn.getStoneType(), onYourTurn.getBoard());
        }
    }

    private int[][] convert(Integer[][] a) {
        return new int[][]{};
    }

    public void onGetChannel(int stoneType) {
        mListener.setStoneType(stoneType);
    }
}
