package controller;

import domain.Board;

/**
 * Created by jaeyoung on 2017. 4. 29..
 */
public interface IoController {
    interface Listener {
        void onSendStonePoint(int axisX, int axisY);
        void onSendNickName(String name);
    }

    void setListener(IoController.Listener listener);

    void getStonePoint();
    void getNickName();

    void showBoard(Board board);
    void showWinner(boolean isBlack);
}
