package domain;

import static consts.BoardCondition.NONESTONE;

/**
 * Created by jaeyoung on 2017. 4. 29..
 */
public class Board {
    int[][] board;

    public Board() {
        initBoard();
    }

    private void initBoard() {
        for(int[] cols:
                this.board){
            for(int each:
                    cols) {
                each = NONESTONE;
            }
        }
    }

    public Board(int[][] board) {
        this.board = board;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }
}
