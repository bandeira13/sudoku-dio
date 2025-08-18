package br.com.sudoku.ui;

import br.com.sudoku.model.Board;
import br.com.sudoku.model.Space;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;

public class SudokuUi extends JFrame {
    private Board board;
    private JTextField[][] cells = new JTextField[9][9];

    public SudokuUi(Board board) {
        this.board = board;
        setTitle("Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLayout(new GridLayout(9, 9));

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                cells[row][col] = new JTextField();
                cells[row][col].setHorizontalAlignment(JTextField.CENTER);

                Space space = board.getSpace(row, col);
                if (space.isFixed()) {
                    cells[row][col].setText(String.valueOf(space.getValue()));
                    cells[row][col].setEditable(false);
                    cells[row][col].setBackground(Color.LIGHT_GRAY);
                } else {
                    ((AbstractDocument) cells[row][col].getDocument()).setDocumentFilter(new NumberFilter());

                    final int r = row;
                    final int c = col;

                    cells[row][col].getDocument().addDocumentListener(new DocumentListener() {
                        @Override
                        public void insertUpdate(DocumentEvent e) {
                            updateBoardAndUi(r, c);
                        }

                        @Override
                        public void removeUpdate(DocumentEvent e) {
                            updateBoardAndUi(r, c);
                        }

                        @Override
                        public void changedUpdate(DocumentEvent e) {
                        }
                    });
                }
                add(cells[row][col]);
            }
        }
        updateUi();
        setVisible(true);
    }

    private void updateBoardAndUi(int row, int col) {
        String text = cells[row][col].getText();
        try {
            int value = text.isEmpty() ? 0 : Integer.parseInt(text);
            board.getSpace(row, col).setValue(value);
            updateUi();
        } catch (NumberFormatException e) {
        }
    }

    private void updateUi() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                Space space = board.getSpace(row, col);
                if (!space.isFixed()) {
                    if (space.isWrong()) {
                        cells[row][col].setBackground(Color.RED);
                    } else {
                        cells[row][col].setBackground(Color.WHITE);
                    }
                }
            }
        }
    }

    private static class NumberFilter extends DocumentFilter {

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string.matches("[1-9]") && fb.getDocument().getLength() == 0) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text.isEmpty()) {
                super.replace(fb, offset, length, text, attrs);
                return;
            }

            String newString = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
            if (newString.matches("[1-9]") && newString.length() == 1) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }
}