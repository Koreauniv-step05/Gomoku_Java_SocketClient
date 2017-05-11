package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.ai.algorithms.domain;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.ai.JYP.evaluation.Evaluation.evaluation;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.ai.JYP.policy.Policy.nextStonePairsByPolicy;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.consts.ENEMY_STONETYPE.ENEMY_STONETYPE;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.consts.GAME_BOARD.X;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.consts.GAME_BOARD.Y;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.utils.Clone2darray.clone2darray;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.utils.PossibleNextStone.possibleNextStonePair;

/**
 * Created by jaeyoung on 2017. 5. 10..
 */
@Slf4j
public class Node extends BaseTree<Node> implements TreeForAlphabeta{
    private int stoneType;
    private int[][] board;
    private int[][] stonePoints;
    private Double eval;

    public Node(Node mother, int[][] stonePoints) {
        super(mother);
        this.stoneType = ENEMY_STONETYPE(this.mother.stoneType);
        this.stonePoints = stonePoints;
//        log.info("Node/Node: getStoneType[{}]",stonePoints[0].getStoneType());
//        log.info("Node/Node: stoneTypeArg[{}]",stoneType);
    }

//    public Node(BaseTree mother, StonePoint[] stonePoints, int stoneType) {
//        super(mother,stoneType);
//        this.stonePoints = stonePoints;
//    }

    public Node(int[][] board, int stoneType) {
        super();
        this.stoneType = stoneType;
        this.board = board;
    }

    @Override
    public List<Node> getChildren() {
        return super.children;
    }

    public int[][] getStonePoints() {
        return stonePoints;
    }

    public int[][] getBoard() {
        if(this.isRoot) {
            return this.board;
        }
        else {
            return updateBoard(this.mother.getBoard(), this.stonePoints);
        }
    }

    private int[][] updateBoard(int[][] board, int[][] stonePoints) {
        int[][] result = clone2darray(board);
        for (int[] stonePoint:
             stonePoints) {
            result[stonePoint[X]][stonePoint[Y]] = this.stoneType;
        }
        return result;
    }

    public void extend() {
        if(!this.extended) {
            for (int[][] stonePointPair :
                    possibleNextStonePair(this.getBoard())) {
                this.children.add(new Node(this, stonePointPair));
            }
            this.extended = true;
        }
    }

    public void extend(int targetDepth) {
//        log.info("BaseTree/extend: targetDepth=[{}]",targetDepth);
//        log.info("BaseTree/extend: before");
//        boardCompare(getBoard());
        extend();
//        log.info("BaseTree/extend: after");
//        boardCompare(getBoard());
        if(targetDepth > 1) {
            for (Node node :
                    this.children) {
                node.extend(targetDepth-1);
            }
        }
    }

    public double getEval() {
        if (this.eval == null) {
            this.eval = evaluation(getBoard(), getRoot().getStoneType());
//            PrintUtils.printBoard(getBoard());
//            log.info("Node/getEval: eval=[{}]",this.eval);
        }
        return this.eval;
    }

    public void extendByEval() {
        if(!this.extended) {
            List<int[][]> nextStonePairs = nextStonePairsByPolicy(getBoard(), this.stoneType);
            for (int[][] stonePointPair :
                    nextStonePairs) {
                Node node = new Node(this, stonePointPair);
                this.children.add(node);
            }
//            log.info("Node/extendByEval: maximumEval=[{}], wholechildren=[{}], numofchildren=[{}]",this.children.get(getChildrenSize()-1).getEval(), sortedChildren.size(), getChildrenSize());
            this.extended = true;
        }
    }

    public void extendByEval(int targetDepth) {
//        log.info("BaseTree/extend: targetDepth=[{}]",targetDepth);
//        log.info("BaseTree/extend: before");
//        boardCompare(getBoard());
        extendByEval();
//        log.info("BaseTree/extend: after");
//        boardCompare(getBoard());
        if(targetDepth != 1) {
            for (Node node :
                    this.children) {
                node.extendByEval(targetDepth-1);
            }
        }
    }
}