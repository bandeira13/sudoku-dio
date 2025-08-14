package br.com.sudoku.model;

public class Space {

    private int value;
    private boolean isFixed;

    public Space(int value) {
        this.value = value;
        this.isFixed = (value != 0);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (!isFixed) {
            this.value = value;
        }
    }

    public boolean isFixed() {
        return isFixed;
    }

    public boolean isEmpty() {
        return value == 0;
    }

    @Override
    public String toString() {
        return isEmpty() ? " " : String.valueOf(value);
    }
}