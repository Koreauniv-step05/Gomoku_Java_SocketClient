package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.ai.algorithms;

import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.ai.algorithms.domain.Node;
import lombok.extern.slf4j.Slf4j;

import java.util.Stack;

/**
 * Created by jaeyoung on 2017. 5. 10..
 */
@Slf4j
public class AlphaBetaForLoop {

    private static Stack<Args> argsStack;
    private static boolean terminate;

    public static void setTerminate(boolean terminate) {
//        log.info("AlphaBetaForLoop/setTerminate: "+terminate);
        AlphaBetaForLoop.terminate = terminate;
    }

    public static Node alphabetaforloop(Node node, int depth,
                                        double alpha, double beta, boolean maximizingPlayer, int maximumDepth) {
        argsStack = new Stack<Args>();

        double v = 0;
        for (int i = 0; i < 1000000; i++) {
            if(i%100000 == 0 ) {
                log.info("AlphaBetaForLoop/alphabetaforloop: [{}]", AlphaBetaForLoop.terminate);
            }
            if(AlphaBetaForLoop.terminate) {
                log.info("AlphaBetaForLoop/alphabetaforloop: terminate");
                return null;
            }
            v++;
        }return null;

//        argsStack.push(new Args(node, alpha, beta, 0));

//        while(true) {
//            Node bestNode = node;
//            if(node.getEval() >= GAMEEND || node.getEval() <= (-GAMEEND) || node.getChildrenSize() == 0 || depth == maximumDepth) {
//                return bestNode;
//            }
//
//            if (maximizingPlayer) {
//                v = -INF;
//                for (Node child :
//                        node.getChildren()) {
//                    double w = alphabeta(child, depth + 1, alpha, beta, false, maximumDepth).getEval();
//                    if (v < w) {
//                        bestNode = child;
//                        v = w;
//                    }
//                    alpha = max(alpha, v);
//                    if (beta <= alpha) {
//                        break;
//                    }
//                }
//
//                return bestNode;
//            } else {
//                v = INF;
//                for (Node child :
//                        node.getChildren()) {
//                    double w = -(alphabeta(child, depth + 1, alpha, beta, true, maximumDepth).getEval());
//                    if (v > w) {
//                        bestNode = child;
//                        v = w;
//                    }
//                    beta = -max(-beta, -v);
//                    if (beta <= alpha) {
//                        break;
//                    }
//                }
//                return bestNode;
//            }
//        }
    }

    static class Args {
        Node node;
        double alpha;
        double beta;
        int index;
        int depth;

        public Args(Node node, double alpha, double beta, int index, int depth) {
            this.node = node;
            this.alpha = alpha;
            this.beta = beta;
            this.index = index;
            this.depth = depth;
        }

        public Node getNode() {
            return node;
        }

        public void setNode(Node node) {
            this.node = node;
        }

        public double getAlpha() {
            return alpha;
        }

        public void setAlpha(double alpha) {
            this.alpha = alpha;
        }

        public double getBeta() {
            return beta;
        }

        public void setBeta(double beta) {
            this.beta = beta;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }
}
