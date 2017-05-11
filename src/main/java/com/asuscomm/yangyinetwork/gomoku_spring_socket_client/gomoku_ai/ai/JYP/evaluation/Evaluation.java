package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.ai.JYP.evaluation;

import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.ai.JYP.EverySequences;
import com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.ai.JYP.Sequence;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.consts.ENEMY_STONETYPE.ENEMY_STONETYPE_INCLUDE_NONE;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.consts.GAME_BOARD.*;


/**
 * Created by jaeyoung on 2017. 5. 10..
 */
@Slf4j
public class Evaluation {
    public static double evaluation(int[][] board, int stoneType) {
//        boardCompare(board);
        List<String> seqs = everySequence(board);
        List<String> enemySeqs = everyEnemySequence(board);

        double evaluation=0;
        evaluation+=evalSumBySequences(seqs);
        evaluation-=evalSumBySequences(enemySeqs);

        if(stoneType == BLACK_STONE) {
            return evaluation;
        } else {
            return -evaluation;
        }
    }

    public static double evalSumBySequences(List<String> seqsString) {
        double evaluation = 0;
        for (String seqString:
                seqsString) {
            for (Sequence sequence:
            EverySequences.getEverySequences()) {

                int count = StringUtils.countMatches(seqString, sequence.getSequence());
                evaluation += sequence.getScore() * count;
//                if (sequence.getScore() == GAMEEND*2) {
//                    if(count >0) {
////                        log.info("Evaluation/evalSumBySequences: seqString=[{}]",seqString);
//                    }
//                    log.debug("Evaluation/evalSumBySequences: [{}]", count);
//                }
            }
        }

        return evaluation;
    }
    
    public static List<String> everySequence(int[][] board) {
        List<String> seqs = new ArrayList<String>();
        seqs.addAll(garo(board));
        seqs.addAll(sero(board));
        seqs.addAll(rightdowncross(board));
        seqs.addAll(leftdowncross(board));

        seqs = wallToEnemy(seqs);
        
        return seqs;
    }

    public static List<String> wallToEnemy(List<String> sequences) {
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < sequences.size(); i++) {
            String seq = sequences.get(i);
            result.add(seq.replace(Integer.toString(WALL_STONE), Integer.toString(WHITE_STONE)));
        }

        return result;
    }

    public static List<String> everyEnemySequence(int[][] board) {
        return everySequence(inverseBoard(board));
    }

    private static int[][] inverseBoard(int[][] board) {
        int[][] inverse = board.clone();

        for (int i = 0; i < board.length; i++) {
            inverse[i] = board[i].clone();
            for (int j = 0; j < board[i].length; j++) {
                inverse[i][j] = ENEMY_STONETYPE_INCLUDE_NONE(board[i][j]);
            }
        }

        return inverse;
    }

    public static ArrayList<String> garo(int[][] board) {
        return toStringArr(board);
    }

    public static ArrayList<String> sero(int[][] board) {
        int[][] transState = transpose(board);

        return toStringArr(transState);
    }

    public static int[][] transpose(int[][] ori) {
        // transpose

        // clone
        int[][] dup= ori.clone();
        for (int i = 0; i < ori.length; i++) {
            dup[i] = ori[i].clone();
        }

        for (int i = 0; i < ori.length; i++) {
            for (int j = 0; j < ori[i].length; j++) {
                dup[j][i] = ori[i][j];
//                System.out.println(i+""+j+""+ori[i][j]);
            }
        }

        return dup;
    }

    public static ArrayList<String> toStringArr(int[][] board) {
        ArrayList<String> strArr = new ArrayList<String>();

        String str = "";

        for (int[] col:board) {
            str = WALL_STONE+"";
            for (int each:col) {
                str = str + each;
            }
            str = str+WALL_STONE;
            // System.out.println(str.toString());
            strArr.add(str);
        }
        return strArr;
    }

    public static ArrayList<String> leftdowncross(int[][] board) {
        ArrayList<String> strArr = new ArrayList<String>();
        String str = "";

        for (int i = 0; i < board.length; i++) {
            str = WALL_STONE+"";
            for (int j = i; j >= 0; j--) {
                str = str + board[i-j][j];
            }
            str = str + WALL_STONE;
//            System.out.println(str);
            strArr.add(str);
        }

        for (int j = 1; j < board.length; j++) {
            str = WALL_STONE+"";
            for (int i = 0; (j+i) < board.length; i++) {
                str = str + board[j+i][board.length-i-1];
            }
            str = str+WALL_STONE;
//            System.out.println(str);
            strArr.add(str);
        }

        return strArr;
    }

    public static ArrayList<String> rightdowncross(int[][] board) {
        ArrayList<String> strArr = new ArrayList<String>();
        String str = "";

        for (int i = 0; i < board.length; i++) {
            str = WALL_STONE+"";
            for (int j = 0; (i+j) < board[i].length; j++) {
                str = str + board[i+j][j];
            }
            str = str+WALL_STONE;

//            System.out.println(str);
            strArr.add(str);
        }

        for (int j = 1; j < board.length; j++) {
            str = WALL_STONE+"";
            for (int i = 0; (i+j) < board[j].length; i++) {
                str = str + board[i][i+j];
            }
            str = str+WALL_STONE;

//            System.out.println(str);
            strArr.add(str);
        }

        return strArr;
    }
}
