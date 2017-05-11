package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.utils;

import java.util.ArrayList;
import java.util.List;

import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.consts.GAME_BOARD.*;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.utils.Clone2darray.clone2darray;


/**
 * Created by jaeyoung on 2017. 5. 11..
 */
public class BoardUtils {
    public static int sum2dboard(int[][] board) {
        int sum = 0;
        for (int[] row:
                board) {
            for (int each:
                    row) {
                sum+=each;
            }
        }

        return sum;
    }

    public static int[][] string2board(String[] boardString) {
        int row = boardString.length;
        int col = boardString[0].length();

        int[][] board = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = Character.getNumericValue(boardString[i].charAt(j));
            }
        }

        return board;
    }

    public static List<String> board2string(int[][] board) {
        int row = board.length;
        int col = board[0].length;

        List<String> boardString = new ArrayList<String>();
        for (int i = 0; i < row; i++) {
            String eachLine = "";
            for (int j = 0; j < col; j++) {
                eachLine += board[i][j];
            }
            boardString.add(eachLine);
        }

        return boardString;
    }

    public static List<String> probmap2string(double[][] board) {
        int row = board.length;
        int col = board[0].length;

        List<String> boardString = new ArrayList<String>();
        for (int i = 0; i < row; i++) {
            String eachLine = "";
            for (int j = 0; j < col; j++) {
                eachLine += " "+cutoffDouble(board[i][j],4, 1);
            }
            boardString.add(eachLine);
        }

        return boardString;
    }

    public static String cutoffDouble(double input, int length, int PREFIX) {
        String outputString;
        if(input== BLACK_STONE|| input == WHITE_STONE){
            outputString = (int)input+"";
            for (int i = 0; i < length-1; i++) {
                outputString+=" ";
            }
        } else {
            String inputString = Double.toString(input);
            outputString = inputString.substring(PREFIX, Math.min(length + PREFIX, inputString.length()));
            for (int i = 0; i < (length+PREFIX)-Math.min(length + PREFIX, inputString.length()); i++) {
                outputString+=" ";
            }
        }
        return outputString;
    }

    public static int[][] putStonePoints(int[][] board, int[][] stonePoints, int stoneType) {
        int[][] result = clone2darray(board);

        for (int[] stonePoint:
        stonePoints) {
            result[stonePoint[X]][stonePoint[Y]] = stoneType;
        }

        return result;
    }

    public static int[][] putStonePointsForVisualize(int[][] board, int[][] stonePoints, int stoneType) {
        int[][] result = clone2darray(board);

        for (int[] stonePoint:
                stonePoints) {
            result[stonePoint[X]][stonePoint[Y]] = stoneType+2;
        }

        return result;
    }

}
