package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.client.channel;

import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.Main;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.client.socket.SocketClient;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.client.socket.SocketClientImpl;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.client.socket.domain.OnYourTurn;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.client.socket.domain.SocketMessage;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.client.socket.domain.StonePoint;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;

import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.client.socket.consts.Commands.CHANNEL.GENERAL_TO_CLIENT.ON_YOUR_TURN;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.client.socket.consts.Commands.CHANNEL.ON_NEW_STONE_FROM_CLIENT;
import static com.asuscomm.yangyinetwork.utils.PrintUtils.printBoard;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
@Slf4j
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
        log.info("ChannelControllerImpl/onNewStoneFromClient: [{}]",socketMessage.toString());
        mSocketClient.sendChannelSocketMessage(socketMessage);
    }

    public void onToClient(SocketMessage socketMessage) {
        String command = socketMessage.getCommand();
        logger.info("onToClient command = "+command);
        if (ON_YOUR_TURN.equals(command)) {
            int[][] board = { {0,0}, {0,0} };
//            int[][] board = (int[][])((LinkedHashMap) socketMessage.getContent()).get("board");
//            logger.info("on to client ON_YOUR_TURN before");
            OnYourTurn onYourTurn  = (OnYourTurn)socketMessage.getContent();


//            logger.info("on to client ON_YOUR_TURN after");
            log.info("ChannelControllerImpl/onToClient: onYourTurn.getBoard");
            printBoard(onYourTurn.getBoard());
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
