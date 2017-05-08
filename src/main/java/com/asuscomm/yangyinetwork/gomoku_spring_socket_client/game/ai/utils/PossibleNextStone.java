package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.game.ai.utils;

import java.util.ArrayList;

import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.game.consts.GAME_BOARD.NONE_STONE;

/**
 * Created by jaeyoung on 2017. 5. 8..
 */
public class PossibleNextStone {
    public static ArrayList<int[]> possibleNextStone(int[][] board) {
        ArrayList<int[]> possibleNextStones = new ArrayList<int[]>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == NONE_STONE) { // todo remove ==
                    possibleNextStones.add(new int[]{i, j});
                }
            }
        }

        return possibleNextStones;
    }
}