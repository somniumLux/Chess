package com.chessexample.chessrules;

public class Pawn extends Piece {

    public Pawn() {
        super(1);
    }

    private int alreadyMovedOnce = 0;

    public int getAlreadyMovedOnce() {
        return alreadyMovedOnce;
    }

    public void setAlreadyMovedOnce(int alreadyMovedOnce) {
        this.alreadyMovedOnce = alreadyMovedOnce;
    }

}
