package br.com.sudoku.ui;

import br.com.sudoku.model.Board;


import javax.swing.*;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.text.AbstractDocument;

public class SudokuUi extends JFrame {
    private Board board;
    private JTextField[][] cells = new JTextField[9][9];

    public SudokuUi(Board board) {
        this.board = board;
        setTitle("Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(new GridLayout(9, 9));


        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                cells[row][col] = new JTextField();
                cells[row][col].setHorizontalAlignment(JTextField.CENTER);

                int value = board.getSpace(row, col).getValue();
                if (value != 0) {
                    cells[row][col].setText(String.valueOf(value));
                    cells[row][col].setEditable(false);
                    cells[row][col].setBackground(Color.LIGHT_GRAY);
                } else {
                    ((AbstractDocument)cells [row][col].getDocument()).setDocumentFilter(new NumberFilter());
                    if (board.getSpace(row, col).isWrong()) {
                        cells[row][col].setBackground(Color.RED);
                    } else {
                        cells[row][col].setBackground(Color.WHITE);
                    }

                }

                add(cells[row][col]);
            }
        }

        setVisible(true);


    }

}
