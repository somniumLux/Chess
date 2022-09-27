package com.chessexample.javafxdemo;

public class Main {
    public static void main(String[] args) {

        final int boardSize = 8;
        int[][] chessBoard = new int[boardSize][boardSize];

        King king = new King();
        king.straightMovement(king.getMaxMovementDistance(), king);

        Spot spot = new Spot(king,0,0);



    }
}
