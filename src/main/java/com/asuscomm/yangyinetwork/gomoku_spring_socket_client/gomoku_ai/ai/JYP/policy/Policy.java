package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.ai.JYP.policy;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.ai.JYP.config.Policy.NEXT_STONE_PAIRS_THERSH_PERSENTAGE;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.ai.JYP.evaluation.Evaluation.evaluation;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.consts.GAME_BOARD.X;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.consts.GAME_BOARD.Y;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.utils.BoardUtils.putStonePoints;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.utils.Clone2darray.clone2darrayToDouble;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.utils.PossibleNextStone.possibleNextStonePair;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.utils.Sort.sortedIndex;

/**
 * Created by jaeyoung on 2017. 5. 11..
 */
@Slf4j
public class Policy {
    public static List<int[][]> nextStonePairsByPolicy(int[][] board, int stoneType) {
        List<int[][]> possibleNextStonePairs = possibleNextStonePair(board);
        List<int[][]> filteredNextStonePairs = new ArrayList<int[][]>();
        List<Double> evals = new ArrayList<Double>();

        for (int i = 0; i < possibleNextStonePairs.size(); i++) {
            int[][] stonePointPair = possibleNextStonePairs.get(i);
            int[][] afterBoard = putStonePoints(board, stonePointPair, stoneType);
//            log.info("Policy/policy: before");
//            printBoard(board);
//            log.info("Policy/policy: after putStonePoints [{}], [{}]", Arrays.toString(stonePointPair[0]), Arrays.toString(stonePointPair[1]));
//            printBoard(afterBoard);
            evals.add(evaluation(afterBoard, stoneType));
        }

        List<Integer> sorted = sortedIndex(evals);

        for (int i = 0; i < possibleNextStonePairs.size() * NEXT_STONE_PAIRS_THERSH_PERSENTAGE; i++) {
            int idx = sorted.get(i);
            filteredNextStonePairs.add(possibleNextStonePairs.get(idx));
//            log.info("Policy/policy: [{}]",evals.get(idx));
        }

        return filteredNextStonePairs;
    }

    public static double[][] makeProbmapWithStonePairs(int[][] board, List<int[][]> stonePairs) {
        double[][] result = clone2darrayToDouble(board);
        double prob = 1/((double)stonePairs.size())/2;
//        int count=0;
        for (int[][] stonePair:
                stonePairs) {
            for (int i = 0; i < stonePair.length; i++) {
                int x = stonePair[i][X];
                int y = stonePair[i][Y];
                result[x][y] += prob;
//                count++;
            }
        }

//        log.info("Policy/makeProbmapWithStonePairs: count=[{}], stonePairs.size=[{}]",count,stonePairs.size());

        return result;
    }
}
