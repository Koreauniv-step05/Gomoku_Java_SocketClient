package controller;

import domain.Board;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import service.Socketclient;

import java.net.URISyntaxException;
import java.util.Scanner;

/**
 * Created by jaeyoung on 2017. 4. 29..
 */
public class GetInput implements IoController {
    private Listener listener;


    public GetInput() {
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void getStonePoint() {
        Scanner scan = new Scanner(System.in);

        System.out.println("x좌표 : ");
        int axisX = scan.nextInt();

        System.out.println("y좌표 : ");
        int axisY = scan.nextInt();

        System.out.println("(X, Y) : (" + axisX + ", " + axisY+")");

        this.listener.onSendStonePoint(axisX,axisY);
    }

    public void getNickName() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Nickname : ");
        String nickname = scan.nextLine();

        this.listener.onSendNickName(nickname);
    }

    public void showBoard(Board board) {

    }

    public void showWinner(boolean isBlack) {

    }
}
