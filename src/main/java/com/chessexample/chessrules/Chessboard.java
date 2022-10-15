package com.chessexample.chessrules;

import java.util.ArrayList;
import java.util.List;

public class Chessboard {

    final static public int boardSize = 8;
    public static List<Spot> allSpots = new ArrayList<>();
    static boolean[][] allPossibleMoves = new boolean[boardSize][boardSize];

    public void createSpots() {
        Piece pawnPiece = new Pawn();
        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++) {
                Spot newSpot = new Spot(x,y);
                if (y == 1) {
                    newSpot.setPiece(pawnPiece);
                    newSpot.setHasPiece(true);
                    newSpot.getPiece().setWhite(false);
                }
                if (y == 6) {
                    newSpot.setPiece(pawnPiece);
                    newSpot.setHasPiece(true);
                    newSpot.getPiece().setWhite(true);
                }
                allSpots.add(newSpot);
            }
        }
    }

    public void printBoard() {
        int atIndex = 0;
        System.out.println("---");
        for (int y = 0; y < boardSize; y++) {
            StringBuilder line = new StringBuilder("|");
            for (int x = 0; x < boardSize; x++) {
                if (allSpots.get(atIndex++).hasPiece())
                    line.append("*");
                else
                    line.append(".");
            }
            line.append("|");
            System.out.println(line);
        }
        System.out.println("---");
    }

    public void printBoardWithMoves() {
        System.out.println("---");
        for (int y = 0; y < boardSize; y++) {
            StringBuilder line = new StringBuilder("|");
            for (int x = 0; x < boardSize; x++) {
                if (allPossibleMoves[x][y])
                    line.append(";");
                else
                    line.append(".");
            }
            line.append("|");
            System.out.println(line);
        }
        System.out.println("---");
    }

    private void showPossibleMoves() {
        for (Spot currentSpot : allSpots) {
            if (currentSpot.hasPiece()) {
                if (currentSpot.getPiece() instanceof Queen)
                    allPossibleMoves = currentSpot.getPiece().checkQueensMovement(currentSpot);
                if (currentSpot.getPiece() instanceof Knight)
                    allPossibleMoves = currentSpot.getPiece().checkKnightsMovement(currentSpot);
            }
        }
    }

    public static void main(String[] args) {

        Chessboard chessBoard = new Chessboard();
        chessBoard.createSpots();

        for (int i = 0; i < 1; i++) {
            System.out.println("Print board:");
            chessBoard.printBoard();

            chessBoard.showPossibleMoves();
            System.out.println("Print possible moves:");
            chessBoard.printBoardWithMoves();
        }

    }
}
