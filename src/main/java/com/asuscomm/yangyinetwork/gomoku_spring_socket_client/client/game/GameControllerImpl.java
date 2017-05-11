package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.client.game;

import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.Main;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.client.channel.ChannelController;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.client.channel.ChannelControllerImpl;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.client.socket.domain.StonePoint;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.ai.JYP.AiJYPImpl;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.ai.commons.Ai;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.Stack;

import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.utils.ArrayCompareUtils.isEmptyBoard;

/**
 * Created by jaeyoung on 2017. 5. 8..
 */
public class GameControllerImpl implements ChannelController.Listener{
    private Logger logger = Logger.getLogger(Main.class);
    private ChannelController mChannelController;
    private int[][] prevBoard;
    private Integer mStoneType;
    private Ai mAi;
    private Stack<StonePoint> stonePointStack;

    public GameControllerImpl() {
        this.getChannel();
//        this.mAi = new AiRandomImpl();
        this.mAi = new AiJYPImpl();

        new Thread(waitCommand).start();
        this.stonePointStack = new Stack<StonePoint>();
    }

    Runnable waitCommand = new Runnable() {
        public void run() {
            while(true) {
                logger.info("waitCommand...");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    void getChannel(){
        this.mChannelController = new ChannelControllerImpl();
        this.mChannelController.addListener(this);
    }

    void findSolution(int[][] board) {
        int remainStones = 2;
        if(isEmptyBoard(board)) {
            remainStones = 1;
        }
        mAi.findSolution(board,remainStones, new Ai.OnSolutionListener() {
            public void onSolution(int[][] stonePoint, int remainStones) {
                    logger.info("onNewStoneFromClient "+Arrays.toString(stonePoint));
                    StonePoint point = new StonePoint(stonePoint,remainStones, mStoneType);
                    mChannelController.onNewStoneFromClient(point);
            }
        });
    }

    public void setStoneType(int stoneType) {
        if( mStoneType== null) {
            logger.info("GameControllerImpl/setStoneType: " + stoneType);
            this.mStoneType = stoneType;
            this.mAi.setStoneType(stoneType);
        }
    }

    public void onYourTurn(int stoneType, int[][] board) {
        logger.info("GameControllerImpl/onYourTurn: "+ stoneType + ", "+ Arrays.toString(board));

        if(stoneType == this.mStoneType) {
            this.findSolution(board);
        }
    }
}
