package com.chessexample.javafxdemo;

public abstract class Piece implements PieceMovement {

    private boolean isWhite = false;
    private boolean isKilled = false;
    private final int maxMovementDistance;

    public Piece(int maxMovementDistance) {
        this.maxMovementDistance = maxMovementDistance;
    }

    protected int getMaxMovementDistance() {
        return maxMovementDistance;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }

    public boolean isKilled() {
        return isKilled;
    }

    public void setKilled(boolean killed) {
        isKilled = killed;
    }

}
