package com.chessexample.javafxdemo;

public class Queen extends Piece implements  PieceMovement {

    public Queen() {
        super(7);
    }

    //TODO horizontal and vertical movement works, add diagonal movement
    @Override
    public boolean[][] checkMovement(Spot currentSpot) {
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

}
