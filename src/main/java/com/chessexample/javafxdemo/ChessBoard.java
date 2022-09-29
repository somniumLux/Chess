package com.chessexample.javafxdemo;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {

    final int boardSize = 8;
    List<Spot> allSpots = new ArrayList<>();

    public void createSpots() {

        Piece kingPiece = new King();
        for (int x = 0; x < boardSize; x++) {
            for (int y = 0; y < boardSize; y++) {
                Spot newSpot = new Spot(x,y);
                if (x == 4 && y == 0) {
                    newSpot.setPiece(kingPiece);
                    newSpot.setHasPiece(true);
                }
                allSpots.add(newSpot);
            }
        }
    }

    //TODO fix printBoard (doesn't print spots that have pieces correctly)
    public void printBoard() {
        System.out.println("---");
        for (int y = 0; y < boardSize; y++) {
            StringBuilder line = new StringBuilder("|");
            for (int x = 0; x < boardSize; x++) {
                if (this.allSpots.get(x+y).hasPiece())
                    line.append("*");
                else
                    line.append(".");
            }
            line.append("|");
            System.out.println(line);
        }
        System.out.println("---");
    }

    public static void main(String[] args) {

        ChessBoard chessBoard = new ChessBoard();
        chessBoard.createSpots();
        chessBoard.printBoard();
        for (Spot currentSpot : chessBoard.allSpots)
            System.out.println(currentSpot.hasPiece());

    }
}
