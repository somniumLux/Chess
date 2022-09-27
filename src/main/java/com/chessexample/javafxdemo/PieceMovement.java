package com.chessexample.javafxdemo;

public interface PieceMovement {

    public boolean[][] straightMovement(int maxDistance, Piece piece);
    public boolean[][] diagonalMovement(int maxDistance, Piece piece);

}
