package com.chessexample.chessrules;

import java.util.ArrayList;
import java.util.List;

public class Chessboard {

    final static public int boardSize = 8;
    public static List<Spot> allSpots = new ArrayList<>();
    static boolean[][] allPossibleMoves = new boolean[boardSize][boardSize];

    public void createSpots() {
        Piece kingPiece = new King();
        Piece queenPiece = new Queen();
        Piece bishopPiece = new Bishop();
        Piece pawnPiece = new Pawn();
        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++) {
                Spot newSpot = new Spot(x,y);
                if (x == 2 && y == 2) {
                    newSpot.setPiece(queenPiece);
                    newSpot.setHasPiece(true);
                }
                if (x == 5 && y == 5) {
                    newSpot.setPiece(kingPiece);
                    newSpot.setHasPiece(true);
                }
                if (x == 3 && y == 5) {
                    newSpot.setPiece(bishopPiece);
                    newSpot.setHasPiece(true);
                }
                if (x == 1 && y == 6) {
                    newSpot.setPiece(pawnPiece);
                    newSpot.setHasPiece(true);
                    newSpot.getPiece().setWhite(false);
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
                if (currentSpot.getPiece() instanceof Queen) {
                    boolean[][] tempQueenMoves;
                    tempQueenMoves = currentSpot.getPiece().checkStraightMovement(currentSpot);
                    allPossibleMoves = currentSpot.getPiece().checkDiagonalMovement(currentSpot);
                    for (int y = 0; y < Chessboard.boardSize; y++) {
                        for (int x = 0; x < Chessboard.boardSize; x++) {
                            if (tempQueenMoves[x][y])
                                allPossibleMoves[x][y] = true;
                        }
                    }
                }
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
