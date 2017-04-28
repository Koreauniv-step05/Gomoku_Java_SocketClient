package controller;

import consts.SOCKET_COMMANDS;
import domain.Data;
import service.Socketclient;

import java.util.Scanner;

import static consts.SOCKET_COMMANDS.*;
import static consts.SOCKET_COMMANDS.ROLES.BLACK;
import static controller.Todo.*;

/**
 * Created by jaeyoung on 2017. 4. 29..
 */
public class GameController implements IoController.Listener, Socketclient.Listener{
    private Boolean isBlack;
    private String nickname;

    private IoController mIoController;
    private Socketclient mSocketclient;

    private int todo;

    public GameController(Socketclient socketclient, IoController ioController) {
        this.mSocketclient = socketclient;
        this.mSocketclient.setListener(this);
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

    // IoController Listener
    public void onSendStonePoint(int axisX, int axisY) {
        this.mSocketclient.sendNewStonePoint(axisX,axisY);
        this.todo = WAIT;
    }

    public void onSendNickName(String nickname) {
        this.nickname = nickname;
        this.mSocketclient.sendNickName(nickname);
        this.todo = WAIT;
    }

    // Socketclient Listener
    public void onReceiveCommandFromServer(Data data) {
        System.out.println("GameController/onReceiveCommandFromServer");
        if(data.getCommand().equals(SETROLE)) {
            String role = (String)data.getContent();
            if (role.equals(BLACK)) {
                setBlack(true);
            } else if(role.equals(SOCKET_COMMANDS.ROLES.WHITE)) {
                setBlack(false);
            }
            System.out.println("GameController/onReceiveCommandFromServer : set isBlack "+isBlack);
        }

        else if(data.getCommand().equals(ONYOURTURN)) {
            if(data.getContent().equals(BLACK) == this.isBlack) {
                System.out.println("GameController/onReceiveCommandFromServer : onMyTurn");
                this.todo = GET_NEWSTONEPOINT;
            }

        }
    }

    public void setBlack(boolean black) {
        if(isBlack==null) {
            isBlack = black;
        }
    }
}
