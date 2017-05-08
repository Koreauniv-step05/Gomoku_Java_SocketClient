package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.game.ai;

import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.game.ai.utils.PossibleNextStone;

import java.util.List;

import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.game.ai.utils.ChooseRandomly.chooseRandomlyInStonePoints;

/**
 * Created by jaeyoung on 2017. 5. 8..
 */
public class AiRandomImpl implements Ai {

    public void findSolution(int[][] board, OnSolutionListener listener) {
        List<int[]> possibleNextStone = PossibleNextStone.possibleNextStone(board);

        int[] solution = chooseRandomlyInStonePoints(possibleNextStone);
        listener.onSolution(solution);
    }
}