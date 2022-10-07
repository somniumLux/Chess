package com.chessexample.javafxdemo;

public class Piece {

    private boolean isWhite;
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

    public boolean[][] checkKingMovement(Spot currentSpot) {
        boolean[][] possibleMoves = new boolean[ChessBoard.boardSize][ChessBoard.boardSize];
        for (int y = currentSpot.getPositionY() - 1; y <= currentSpot.getPositionY() + 1; y++) {
            for (int x = currentSpot.getPositionX() - 1; x <= currentSpot.getPositionX() + 1; x++) {
                possibleMoves[x][y] = x != currentSpot.getPositionX() || y != currentSpot.getPositionY();
            }
        }
        return possibleMoves;
    }

    public boolean[][] checkStraightMovement(Spot currentSpot) {
        boolean[][] possibleMoves = new boolean[ChessBoard.boardSize][ChessBoard.boardSize];
        int posX = currentSpot.getPositionX();
        int posY = currentSpot.getPositionY();
        int maxMovementDistance = currentSpot.getPiece().getMaxMovementDistance();

        for (int y = posY - maxMovementDistance; y <= posY + maxMovementDistance; y++) {
            for (int x = posX - maxMovementDistance; x <= posX + maxMovementDistance; x++) {
                if (x < 0 || y < 0 || x > ChessBoard.boardSize - 1 || y > ChessBoard.boardSize - 1)
                    continue;
                if (x == posX || y == posY)
                    possibleMoves[x][y] = true;
            }
        }
        possibleMoves[posX][posY] = false;
        return possibleMoves;
    }

    //TODO create checkDiagonalMovement method
    //TODO create checkKnightMovement method
    //TODO create checkPawnMovement method

}
