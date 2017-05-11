package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.utils;

import java.util.ArrayList;

import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.consts.GAME_BOARD.NONE_STONE;


/**
 * Created by jaeyoung on 2017. 5. 7..
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

    public static ArrayList<int[][]> possibleNextStonePair(int[][] board) {
        ArrayList<int[]> possibleNextStones = possibleNextStone(board);
        ArrayList<int[][]> possibleNextStonePairs = new ArrayList<int[][]>();

        int length = possibleNextStones.size();
        for (int i = 0; i < length-1; i++) {
            for(int j=i+1; j<length; j++) {
                possibleNextStonePairs.add(new int[][]{ possibleNextStones.get(i), possibleNextStones.get(j)});
            }
        }
        return possibleNextStonePairs;
    }
}