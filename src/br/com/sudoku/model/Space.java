package br.com.sudoku.model;

public class Space {

    private int value;
    private boolean isFixed;
    private boolean isWrong;

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