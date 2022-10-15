package com.chessexample.chessrules;

public class Pawn extends Piece {

    private boolean alreadyMovedOnce = true;

    public boolean isAlreadyMovedOnce() {
        return alreadyMovedOnce;
    }

    public void setAlreadyMovedOnce(boolean alreadyMovedOnce) {
        this.alreadyMovedOnce = alreadyMovedOnce;
    }
}
