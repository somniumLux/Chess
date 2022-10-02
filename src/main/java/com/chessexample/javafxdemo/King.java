package com.chessexample.javafxdemo;

public class King extends Piece {

    public King() {
        super(1,true);
    }

    //TODO finish the method
    @Override
    public boolean[][] straightMovement(Spot currentSpot) {
        boolean[][] possibleMoves = new boolean[ChessBoard.boardSize][ChessBoard.boardSize];
        int posX = currentSpot.getPositionX();
        int posY = currentSpot.getPositionY();

        return possibleMoves;
    }

}
