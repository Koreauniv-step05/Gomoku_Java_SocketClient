package controller;

import service.Socketclient;

import java.util.Scanner;

import static controller.Todo.GET_NEWSTONEPOINT;
import static controller.Todo.GET_NICKNAME;
import static controller.Todo.WAIT;

/**
 * Created by jaeyoung on 2017. 4. 29..
 */
public class GameController implements IoController.Listener {
    private boolean isBlack;
    private String nickname;

    private IoController mIoController;
    private Socketclient mSocketclient;

    private int todo;

    public GameController(Socketclient socketclient, IoController ioController) {
        this.mSocketclient = socketclient;
        this.todo = GET_NICKNAME;
        this.mIoController = ioController;
        this.mIoController.setListener(this);
        this.getInputThread.start();
    }

    private Runnable getInputRunnable = new Runnable() {
        public void run() {
            while (true) {
                switch (todo) {
                    case GET_NICKNAME:
                        mIoController.getNickName();
                        todo = WAIT;
                        break;
                    case WAIT:
                        break;
                    case GET_NEWSTONEPOINT:
                        mIoController.getStonePoint();
                        todo = WAIT;
                        break;
                }
            }
        }
    };
    private Thread getInputThread = new Thread(getInputRunnable);

    public void onSendStonePoint(int axisX, int axisY) {
        this.mSocketclient.sendNewStonePoint(axisX,axisY);
        this.todo = WAIT;
    }

    public void onSendNickName(String nickname) {
        this.nickname = nickname;
        this.mSocketclient.sendNickName(nickname);
        this.todo = WAIT;
    }
}
