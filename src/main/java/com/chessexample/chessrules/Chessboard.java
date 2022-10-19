package com.chessexample.chessrules;

import java.util.ArrayList;
import java.util.List;

public class Chessboard {

    final static public int boardSize = 8;
    public static List<Spot> allSpots = new ArrayList<>();
    public static boolean[][] allPossibleMoves = new boolean[boardSize][boardSize];

    public static void main(String[] args) {

        Chessboard chessBoard = new Chessboard();
        createSpots();

        for (int i = 0; i < 1; i++) {
            System.out.println("Print board:");
            chessBoard.printBoard();
        }
    }

    public static void createSpots() {
        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++) {
                Spot newSpot = new Spot(x,y);

                if (y == 1) {
                    Piece pawn = new Pawn();
                    newSpot.setPiece(pawn);
                    newSpot.setHasPiece(true);
                    newSpot.getPiece().setWhite(false);
                }
                if (y == 6) {
                    Piece pawn = new Pawn();
                    newSpot.setPiece(pawn);
                    newSpot.setHasPiece(true);
                    newSpot.getPiece().setWhite(true);
                }
                if (x == 4 && y == 0) {
                    Piece king = new King();
                    newSpot.setPiece(king);
                    newSpot.setHasPiece(true);
                    newSpot.getPiece().setWhite(false);
                }
                if (x == 4 && y == 7) {
                    Piece king = new King();
                    newSpot.setPiece(king);
                    newSpot.setHasPiece(true);
                    newSpot.getPiece().setWhite(true);
                }
                if (x == 3 && y == 0) {
                    Piece queen = new Queen();
                    newSpot.setPiece(queen);
                    newSpot.setHasPiece(true);
                    newSpot.getPiece().setWhite(false);
                }
                if (x == 3 && y == 7) {
                    Piece queen = new Queen();
                    newSpot.setPiece(queen);
                    newSpot.setHasPiece(true);
                    newSpot.getPiece().setWhite(true);
                }
                if ((x == 0 || x == 7) && y == 0) {
                    Piece rook = new Rook();
                    newSpot.setPiece(rook);
                    newSpot.setHasPiece(true);
                    newSpot.getPiece().setWhite(false);
                }
                if ((x == 0 || x == 7) && y == 7) {
                    Piece rook = new Rook();
                    newSpot.setPiece(rook);
                    newSpot.setHasPiece(true);
                    newSpot.getPiece().setWhite(true);
                }
                if ((x == 1 || x == 6) && y == 0) {
                    Piece knight = new Knight();
                    newSpot.setPiece(knight);
                    newSpot.setHasPiece(true);
                    newSpot.getPiece().setWhite(false);
                }
                if ((x == 1 || x == 6) && y == 7) {
                    Piece knight = new Knight();
                    newSpot.setPiece(knight);
                    newSpot.setHasPiece(true);
                    newSpot.getPiece().setWhite(true);
                }
                if ((x == 2 || x == 5) && y == 0) {
                    Piece bishop = new Bishop();
                    newSpot.setPiece(bishop);
                    newSpot.setHasPiece(true);
                    newSpot.getPiece().setWhite(false);
                }
                if ((x == 2 || x == 5) && y == 7) {
                    Piece bishop = new Bishop();
                    newSpot.setPiece(bishop);
                    newSpot.setHasPiece(true);
                    newSpot.getPiece().setWhite(true);
                }
                allSpots.add(newSpot);
            }
        }
    }

    public static Spot checkSpot(int x, int y) {
        for (Spot spot : allSpots) {
            if (spot.getPositionX() == x && spot.getPositionY() == y)
                return spot;
        }
        return null;
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

    public static void showPossibleMoves(Spot spot) {
        if (!spot.hasPiece()) {
            for (int x = 0; x < Chessboard.boardSize; x++) {
                for (int y = 0; y < Chessboard.boardSize; y++) {
                    allPossibleMoves[x][y] = false;
                }
            }
        }
        else {
            if (spot.getPiece() instanceof Pawn)
                allPossibleMoves = spot.getPiece().checkPawnMovement(spot);
            else if (spot.getPiece() instanceof Knight)
                allPossibleMoves = spot.getPiece().checkKnightsMovement(spot);
            else if (spot.getPiece() instanceof Bishop)
                allPossibleMoves = spot.getPiece().checkDiagonalMovement(spot);
            else if (spot.getPiece() instanceof Rook)
                allPossibleMoves = spot.getPiece().checkStraightMovement(spot);
            else if (spot.getPiece() instanceof Queen)
                allPossibleMoves = spot.getPiece().checkQueensMovement(spot);
            else if (spot.getPiece() instanceof King)
                allPossibleMoves = spot.getPiece().checkKingsMovement(spot);
        }
    }
}
