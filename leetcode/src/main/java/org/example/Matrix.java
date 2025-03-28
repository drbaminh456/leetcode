package org.example;

import java.util.*;

public class Matrix {
    public static void main(xString[] args) {
        int[] nums = {2,2,2,3};
//        System.out.println(isValidSudoku(nums));
    }
    public static boolean isValidSudoku(char[][] board) {
        HashSet<Character>[] rows = new HashSet[9];
        HashSet<Character>[] cols = new HashSet[9];
        HashSet<Character>[] boxes = new HashSet[9];
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c =='.'){
                    continue;
                }
                int boxIndex = (i/3)*3 + j/3;
                if(rows[i].contains(c) || cols[j].contains(c) ||boxes[boxIndex].contains(c)){
                    return false;
                }
                rows[i].add(c);
                cols[j].add(c);
                boxes[boxIndex].add(c);
            }
        }
        return true;
    }

    public static void rotate(int[][] matrix){
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }

}
