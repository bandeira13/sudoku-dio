package br.com.sudoku.ui;
import javax.swing.text.*;

public class NumberFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (isValid(string, fb.getDocument().getLength())) {
            super.insertString(fb, offset, string, attr);
        }
    }
    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        if (isValid(text, fb.getDocument().getLength() - length)) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    private boolean isValid(String text, int currentLength) {
        // só aceita um único número de 1 a 9
        return text.matches("[1-9]") && currentLength == 0;
    }



}