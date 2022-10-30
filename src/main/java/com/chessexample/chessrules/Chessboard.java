package com.chessexample.chessrules;

import java.util.ArrayList;
import java.util.List;

public class Chessboard {

    final static public int boardSize = 8;
    public static List<Spot> allSpots = new ArrayList<>();
    public static boolean[][] allMoves = new boolean[boardSize][boardSize];

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
        return Chessboard.allSpots.get(0);
    }

    // TODO add spots with enemy pieces
    public static void showPossibleMoves(Spot spot) {
        if (!spot.hasPiece()) {
            for (int x = 0; x < Chessboard.boardSize; x++) {
                for (int y = 0; y < Chessboard.boardSize; y++) {
                    allMoves[x][y] = false;
                }
            }
        }
        else {
            if (spot.getPiece() instanceof Pawn)
                allMoves = spot.getPiece().checkPawnMovement(spot);
            else if (spot.getPiece() instanceof Knight)
                allMoves = spot.getPiece().checkKnightsMovement(spot);
            else if (spot.getPiece() instanceof Bishop)
                allMoves = spot.getPiece().checkDiagonalMovement(spot);
            else if (spot.getPiece() instanceof Rook)
                allMoves = spot.getPiece().checkStraightMovement(spot);
            else if (spot.getPiece() instanceof Queen)
                allMoves = spot.getPiece().checkQueensMovement(spot);
            else if (spot.getPiece() instanceof King)
                allMoves = spot.getPiece().checkKingsMovement(spot);
        }
    }

    public static void eraseAllPossibleMoves () {
        for (int y = 0; y < Chessboard.boardSize; y++) {
            for (int x = 0; x < Chessboard.boardSize; x++) {
                allMoves[x][y] = false;
            }
        }
    }
}
