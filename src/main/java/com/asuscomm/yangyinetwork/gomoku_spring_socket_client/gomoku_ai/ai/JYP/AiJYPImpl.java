package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.ai.JYP;

import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.ai.algorithms.AlphaBetaForLoop;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.ai.algorithms.domain.Node;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.ai.commons.AiBaseClass;
import lombok.extern.slf4j.Slf4j;

import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.ai.JYP.config.IterativeDeepening.MAXIMUM_DEPTH;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.ai.JYP.config.IterativeDeepening.START_DEPTH;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.ai.algorithms.AlphaBeta.alphabeta;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.consts.CONSTS.INF;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.utils.ChooseRandomly.chooseRandomlyInBoard;

/**
 * Created by jaeyoung on 2017. 5. 10..
 */
@Slf4j
public class AiJYPImpl extends AiBaseClass {

    public AiJYPImpl() {
    }

    @Override
    public void run() {
        {
            log.info("AiJYPImpl/run: ");

            if (this.remainStones == 1) {
                setSolution(new int[][]{chooseRandomlyInBoard(board)});
            } else {
                Node rootNode = new Node(board, stoneType);

//                iterativeDeepeningSearch(rootNode);
                int presentDepth = START_DEPTH;
                while(true) {
                    rootNode.extendByEval(presentDepth);
                    log.info("AiJYPImpl/iterativeDeepeningSearch: [{}]", presentDepth);
                    Node bestNode = (Node)alphabeta(rootNode,0, -INF, INF, true, presentDepth);
                    if(this.terminate) {
                        break;
                    }
                    int[][] stonePointPair = bestNode.getMothersChild().getStonePoints();
                    setSolution(stonePointPair);

                    if(presentDepth >= MAXIMUM_DEPTH) {
                        break;
                    }
                    presentDepth = presentDepth + 1;
                }
            }
        }
    }

    @Override
    public void terminate() {
        log.info("AiJYPImpl/terminate: terminate");
        super.terminate();
        this.terminate = true;
        AlphaBetaForLoop.setTerminate(true);
    }
}
