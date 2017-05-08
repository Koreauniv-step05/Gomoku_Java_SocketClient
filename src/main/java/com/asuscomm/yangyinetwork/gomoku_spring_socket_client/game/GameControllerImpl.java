package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.game;

import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.Main;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.channel.ChannelController;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.channel.ChannelControllerImpl;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.game.ai.Ai;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.game.ai.AiRandomImpl;
import org.apache.log4j.Logger;
import org.springframework.messaging.simp.stomp.StompSession;

/**
 * Created by jaeyoung on 2017. 5. 8..
 */
public class GameControllerImpl implements ChannelController.Listener{
    private Logger logger = Logger.getLogger(Main.class);
    private ChannelController mChannelController;
    private int mStoneType;
    private Ai mAi;

    public GameControllerImpl() {
        this.getChannel();
        this.mAi = new AiRandomImpl();

        new Thread(waitCommand).start();
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
        mAi.findSolution(board, new Ai.OnSolutionListener() {
            public void onSolution(int[] stonePoint) {
                mChannelController.onNewStoneFromClient(stonePoint);
            }
        });
//        mChannelController.onNewStoneFromClient(new int[]{1,1}); //stub
        // ai
    }

    public void setStoneType(int stoneType) {
        this.mStoneType=stoneType;
    }

    public void onYourTurn(int stoneType, int[][] board) {
        if(stoneType == this.mStoneType) {
            this.findSolution(board);
        }
    }
}
