package com.vista.drill.xiaochao.回溯;

/**
 * https://leetcode-cn.com/problems/sudoku-solver/
 *
 * @author Wen TingTing by 2021/2/11
 */
public class 解数独 {
    char[][] board;

    public void solveSudoku(char[][] board) {
        this.board = board;
        solveSudoku(0, 0);
    }

    private boolean solveSudoku(int i, int j) {
        if (i == board.length) {
            return true;
        }
        if (j == board[0].length) {
            return solveSudoku(i + 1, 0);
        }
        if (board[i][j] != '.') {
            return solveSudoku(i, j + 1);
        } else {
            for (char ch = '1'; ch <= '9'; ch++) {
                if (!isVaild(ch, i, j)) {
                    continue;
                }
                board[i][j] = ch;
                if (solveSudoku(i, j + 1)) {
                    return true;
                }
                board[i][j] = '.';
            }

        }
        return false;
    }

    private boolean isVaild(char ch, int i, int j) {
        for (int k = 0; k < board.length; k++) {
            if (ch == board[i][k]) return false;
            if (ch == board[k][j]) return false;
            if (ch == board[(i / 3) * 3 + k / 3][(j / 3) * 3 + k % 3]) return false;

        }
        return true;
    }

}
