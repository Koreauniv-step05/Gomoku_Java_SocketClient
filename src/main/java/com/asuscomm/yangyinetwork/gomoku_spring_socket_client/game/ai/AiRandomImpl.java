package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.game.ai;

import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.game.ai.utils.PossibleNextStone;

import java.util.List;

import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.commons.Config.CHOOSE_STONE_DELAY;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.game.ai.utils.ChooseRandomly.chooseRandomlyInStonePoints;

/**
 * Created by jaeyoung on 2017. 5. 8..
 */
public class AiRandomImpl implements Ai {

    public void findSolution(int[][] board, OnSolutionListener listener) {
        List<int[]> possibleNextStone = PossibleNextStone.possibleNextStone(board);

        int[] solution = chooseRandomlyInStonePoints(possibleNextStone);

        try {
            Thread.sleep(CHOOSE_STONE_DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        listener.onSolution(solution);
    }
}