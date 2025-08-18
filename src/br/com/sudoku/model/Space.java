package br.com.sudoku.model;

public class Space {

    private int value;
    private final int correctValue;
    private final boolean isFixed;
    private boolean isWrong;

    public Space(int initialValue, int correctValue) {
        this.value = initialValue;
        this.correctValue = correctValue;
        this.isFixed = (initialValue != 0);
        this.isWrong = false;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (!isFixed) {
            this.value = value;
            this.isWrong = (value != 0 && value != correctValue);
        }
    }

    public boolean isFixed() {

        return isFixed;
    }

    public boolean isEmpty() {

        return value == 0;
    }

    public boolean isWrong() {
        return isWrong;
    }
    public void setWrong(boolean wrong) {
        this.isWrong = wrong;
    }


    @Override
    public String toString() {
        return isEmpty() ? " " : String.valueOf(value);
    }
}