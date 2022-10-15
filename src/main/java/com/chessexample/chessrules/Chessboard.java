package com.chessexample.chessrules;

import java.util.ArrayList;
import java.util.List;

public class Chessboard {

    final static public int boardSize = 8;
    public static List<Spot> allSpots = new ArrayList<>();
    static boolean[][] allPossibleMoves = new boolean[boardSize][boardSize];

    public static void createSpots() {
        Piece blackPawn = new Pawn();
        Piece whitePawn = new Pawn();
        Piece blackKing = new King();
        Piece whiteKing = new King();
        Piece blackQueen = new Queen();
        Piece whiteQueen = new Queen();
        Piece blackBishop = new Bishop();
        Piece whiteBishop = new Bishop();
        Piece blackRook = new Rook();
        Piece whiteRook = new Rook();
        Piece blackKnight = new Knight();
        Piece whiteKnight = new Knight();

        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++) {
                Spot newSpot = new Spot(x,y);

                if (y == 1) {
                    newSpot.setPiece(blackPawn);
                    newSpot.setHasPiece(true);
                    newSpot.getPiece().setWhite(false);
                }
                if (y == 6) {
                    newSpot.setPiece(whitePawn);
                    newSpot.setHasPiece(true);
                    newSpot.getPiece().setWhite(true);
                }
                if (x == 4 && y == 0) {
                    newSpot.setPiece(blackKing);
                    newSpot.setHasPiece(true);
                    newSpot.getPiece().setWhite(false);
                }
                if (x == 4 && y == 7) {
                    newSpot.setPiece(whiteKing);
                    newSpot.setHasPiece(true);
                    newSpot.getPiece().setWhite(true);
                }
                if (x == 3 && y == 0) {
                    newSpot.setPiece(blackQueen);
                    newSpot.setHasPiece(true);
                    newSpot.getPiece().setWhite(false);
                }
                if (x == 3 && y == 7) {
                    newSpot.setPiece(whiteQueen);
                    newSpot.setHasPiece(true);
                    newSpot.getPiece().setWhite(true);
                }
                if ((x == 0 || x == 7) && y == 0) {
                    newSpot.setPiece(blackRook);
                    newSpot.setHasPiece(true);
                    newSpot.getPiece().setWhite(false);
                }
                if ((x == 0 || x == 7) && y == 7) {
                    newSpot.setPiece(whiteRook);
                    newSpot.setHasPiece(true);
                    newSpot.getPiece().setWhite(true);
                }
                if ((x == 1 || x == 6) && y == 0) {
                    newSpot.setPiece(blackKnight);
                    newSpot.setHasPiece(true);
                    newSpot.getPiece().setWhite(false);
                }
                if ((x == 1 || x == 6) && y == 7) {
                    newSpot.setPiece(whiteKnight);
                    newSpot.setHasPiece(true);
                    newSpot.getPiece().setWhite(true);
                }
                if ((x == 2 || x == 5) && y == 0) {
                    newSpot.setPiece(blackBishop);
                    newSpot.setHasPiece(true);
                    newSpot.getPiece().setWhite(false);
                }
                if ((x == 2 || x == 5) && y == 7) {
                    newSpot.setPiece(whiteBishop);
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
            }
        }
    }

    public static void main(String[] args) {

        Chessboard chessBoard = new Chessboard();
        createSpots();

        for (int i = 0; i < 1; i++) {
            System.out.println("Print board:");
            chessBoard.printBoard();

            chessBoard.showPossibleMoves();
            System.out.println("Print possible moves:");
            chessBoard.printBoardWithMoves();
        }

    }
}
