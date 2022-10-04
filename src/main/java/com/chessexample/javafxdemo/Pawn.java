package com.chessexample.javafxdemo;

public class Pawn extends Piece implements PieceMovement {

    public Pawn() {
        super(1);
    }

    @Override
    public boolean[][] checkMovement(Spot currentSpot) {
        return new boolean[0][];
    }

}
