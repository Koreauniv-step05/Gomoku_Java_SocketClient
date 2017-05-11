package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.utils;

/**
 * Created by jaeyoung on 2017. 5. 10..
 */
public class Clone2darray {
    public static int[][] clone2darray(int[][] arr) {
        int row = arr.length;
        int col = arr[0].length;
        int[][] result = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                result[i][j] = arr[i][j];
            }
        }
        return result;
    }

    public static double[][] clone2darrayToDouble(int[][] arr) {
        int row = arr.length;
        int col = arr[0].length;
        double[][] result = new double[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                result[i][j] = arr[i][j];
            }
        }
        return result;
    }
}
