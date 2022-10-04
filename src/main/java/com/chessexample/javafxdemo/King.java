package com.chessexample.javafxdemo;

public class King extends Piece implements PieceMovement {

    public King() {
        super(1);
    }

    @Override
    public boolean[][] checkMovement(Spot currentSpot) {
        boolean[][] possibleMoves = new boolean[ChessBoard.boardSize][ChessBoard.boardSize];
        for (int y = currentSpot.getPositionY() - 1; y <= currentSpot.getPositionY() + 1; y++) {
            for (int x = currentSpot.getPositionX() - 1; x <= currentSpot.getPositionX() + 1; x++) {
                possibleMoves[x][y] = x != currentSpot.getPositionX() || y != currentSpot.getPositionY();
            }
        }
        return possibleMoves;
    }

}
