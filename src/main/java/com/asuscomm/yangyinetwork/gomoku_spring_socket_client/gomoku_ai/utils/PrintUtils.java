package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.utils.BoardUtils.board2string;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.utils.BoardUtils.probmap2string;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.utils.BoardUtils.putStonePointsForVisualize;


/**
 * Created by jaeyoung on 2017. 5. 7..
 */
@Slf4j
public class PrintUtils {
    public static void printStonePoints(List<int[]> stonePoints) {
        for (int i = 0; i < stonePoints.size(); i++) {
            System.out.println(Arrays.toString(stonePoints.get(i)));
        }
    }

    public static void printStonePoint(int[] stonePoint) {
        System.out.println(Arrays.toString(stonePoint));
    }
    public static void printStonePointPairs(int[][] stonePointPairs) {
        if(stonePointPairs.length == 2)
            log.info("AiBaseClass/setSolution: [{}], [{}]",""+ Arrays.toString(stonePointPairs[0]), ""+Arrays.toString(stonePointPairs[1]));
    }

    public static void printBoard(int[][] board) {

        List<String> boardString = board2string(board);

        printStringList(boardString);

//        for (int[] row:
//             board) {
//            log.info("PrintUtils/printBoard: [{}]",Arrays.toString(row));
//        }
    }

    public static void printBoardWithNextStones(int[][] board, int[][] stonePoints, int stoneType) {
        printBoard(putStonePointsForVisualize(board, stonePoints, stoneType));
    }

    public static void printProbmap(double[][] board) {

        List<String> boardString = probmap2string(board);

        printStringList(boardString);

//        for (int[] row:
//             board) {
//            log.info("PrintUtils/printBoard: [{}]",Arrays.toString(row));
//        }
    }

    public static void printStringList(List<String> boardString) {
        for (int i = 0; i < boardString.size(); i++) {
            log.info("PrintUtils/printBoard: [{}]",boardString.get(i));
        }
    }
}
