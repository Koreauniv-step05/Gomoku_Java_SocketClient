package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.ai.commons;

import lombok.extern.slf4j.Slf4j;


import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.consts.GAME_RULE.REAL_TIME_LIMITS;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.consts.GAME_RULE.TIMELIMIT_CHECK_PERIOD;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.utils.ChooseRandomly.choosePairRandomlyInBoard;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.utils.PrintUtils.printBoardWithNextStones;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.utils.PrintUtils.printStonePointPairs;

/**
 * Created by jaeyoung on 2017. 5. 10..
 */
@Slf4j
public abstract class AiBaseClass implements Ai, Runnable {
    private long startTime;
    protected int[][] currentOptimal;

    protected int stoneType;
    protected int[][] board;
    protected Thread findSolThread;

    protected boolean terminate;
    protected int remainStones;

    protected void tic() {
        this.startTime = System.currentTimeMillis();
    }

    protected long toc() {
        long toc = System.currentTimeMillis() - this.startTime;
        log.debug("AiBaseClass/toc: [{}]",toc);
        return toc;
    }

    public void findSolution(int[][] board, int remainStones, Ai.OnSolutionListener listener) {
        this.board = board;
        setSolution(choosePairRandomlyInBoard(board));
        tic();
        this.remainStones = remainStones;
        this.findSolThread = new Thread(this);
        this.findSolThread.start();
        while(true) {
            if(toc()> REAL_TIME_LIMITS) {
                log.info("AiBaseClass/findSolution: REAL_TIME_LIMITS");
                terminate();
                break;
            }
            try {
                Thread.sleep(TIMELIMIT_CHECK_PERIOD);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        listener.onSolution(this.currentOptimal, this.remainStones);
    }

    protected void terminate() {
        this.findSolThread.interrupt();
    }

    abstract public void run();

    public void setStoneType(int stoneType) {
        this.stoneType = stoneType;
    }

    protected void setSolution(int[][] stonePointPair) {
        printBoardWithNextStones(board, stonePointPair, stoneType);
        printStonePointPairs(stonePointPair);
        this.currentOptimal = stonePointPair;
    }
}
