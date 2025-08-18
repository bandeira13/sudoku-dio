package br.com.sudoku.model;

import br.com.sudoku.util.BoardTemplate;

import java.util.stream.IntStream;

public class Board {

    private Space[][] spaces;

    public Board(int[][] initialGrid , int[][] solutionGrid) {
        this.spaces = new Space[9][9];
        for (int r = 0;r < 9; r++){
            for (int c = 0; c< 9; c++){
                spaces[r][c] = new Space(initialGrid[r][c] , solutionGrid[r][c]);
            }
        }
    }

    public Space getSpace(int row, int col) {
        return spaces[row][col];
    }

    public boolean isValid(int row, int col, int value) {
        return isRowValid(row, value) && isColValid(col, value) && isBoxValid(row, col, value);
    }

    private boolean isRowValid(int row, int value) {
        for (int col = 0; col < 9; col++) {
            if (spaces[row][col].getValue() == value) {
                return false;
            }
        }
        return true;
    }

    private boolean isColValid(int col, int value) {
        for (int row = 0; row < 9; row++) {
            if (spaces[row][col].getValue() == value) {
                return false;
            }
        }
        return true;
    }

    private boolean isBoxValid(int row, int col, int value) {
        int boxRowStart = row - row % 3;
        int boxColStart = col - col % 3;
        for (int r = boxRowStart; r < boxRowStart + 3; r++) {
            for (int c = boxColStart; c < boxColStart + 3; c++) {
                if (spaces[r][c].getValue() == value) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isComplete() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (spaces[row][col].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printBoard() {
        Object[] values = IntStream.range(0, 81)
                .mapToObj(i -> spaces[i / 9][i % 9].toString())
                .toArray();
        System.out.println(String.format(BoardTemplate.BOARD_TEMPLATE, values));
    }
    public void checkErrors(int[][] solution) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                Space s = spaces[r][c];
                if (!s.isFixed() && s.getValue() != 0) {
                    s.setWrong(s.getValue() != solution[r][c]);
                } else {
                    s.setWrong(false);
                }
            }
        }
    }

}