package com.chessexample.javafxdemo;

public class Piece {

    private boolean isWhite = false;
    private boolean isKilled = false;
    private boolean canMoveBack = true;
    private int positionX;
    private int positionY;
    private int maxMovementDistance;

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

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public boolean insideOfMaxMovementStraightReach(int nextPosition, int currentPosition, int maxMovementDistance) {
        return (nextPosition > currentPosition + maxMovementDistance) || (nextPosition < currentPosition - maxMovementDistance);
    }

    // TODO after fixing printBoard, correct and test method straightMovement
    public boolean[][] straightMovement(int maxDistance, Piece piece) {
        boolean[][] possibleEndSpots = new boolean[8][8];
        int currentPositionX = piece.getPositionX();
        int currentPositionY = piece.getPositionY();

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (x == currentPositionX) {
                    possibleEndSpots[x][y] = insideOfMaxMovementStraightReach(x, piece.getPositionX(), maxDistance);
                }
                if (y == currentPositionY) {
                    possibleEndSpots[x][y] = insideOfMaxMovementStraightReach(y, piece.getPositionY(), maxDistance);
                }
            }
        }
        return possibleEndSpots;
    }

}
