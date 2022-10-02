package com.chessexample.javafxdemo;

public abstract class Piece {

    private boolean isWhite = false;
    private boolean isKilled = false;
    private final boolean canMoveBackwards;
    private final int maxMovementDistance;

    public Piece(int maxMovementDistance, boolean canMoveBackwards) {
        this.maxMovementDistance = maxMovementDistance;
        this.canMoveBackwards = canMoveBackwards;
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

    //TODO make the straightMovement method work
    public abstract boolean[][] straightMovement(Spot currentSpot);

}
