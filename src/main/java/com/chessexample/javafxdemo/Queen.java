package com.chessexample.javafxdemo;

public class Queen extends Piece {

    public Queen() {
        super(7,true);
    }

    @Override
    public boolean[][] straightMovement(Spot currentSpot) {
        return new boolean[0][];
    }

}
