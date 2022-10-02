package com.chessexample.javafxdemo;

public class Pawn extends Piece {

    public Pawn() {
        super(1,false);
    }

    @Override
    public boolean[][] straightMovement(Spot currentSpot) {
        return new boolean[0][];
    }

}
