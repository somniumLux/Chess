package com.chessexample.chessrules;

import java.util.ArrayList;
import java.util.List;

public class Chessboard {

    final static public int boardSize = 8;
    public static List<Spot> allSpots = new ArrayList<>();
    public static boolean[][] allMoves = new boolean[boardSize][boardSize];
    public static boolean[][] allThreatenedSpots = new boolean[boardSize][boardSize];

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

    public static boolean[][] showPossibleMoves(Spot spot) {
        boolean[][] spots = new boolean[Chessboard.boardSize][Chessboard.boardSize];
        if (!spot.hasPiece()) {
            for (int x = 0; x < Chessboard.boardSize; x++) {
                for (int y = 0; y < Chessboard.boardSize; y++) {
                    spots[x][y] = false;
                }
            }
        }
        else {
            if (spot.getPiece() instanceof Pawn)
                spots = spot.getPiece().checkPawnMovement(spot);
            else if (spot.getPiece() instanceof Knight)
                spots = spot.getPiece().checkKnightsMovement(spot);
            else if (spot.getPiece() instanceof Bishop)
                spots = spot.getPiece().checkDiagonalMovement(spot);
            else if (spot.getPiece() instanceof Rook)
                spots = spot.getPiece().checkStraightMovement(spot);
            else if (spot.getPiece() instanceof Queen)
                spots = spot.getPiece().checkQueensMovement(spot);
            else if (spot.getPiece() instanceof King)
                spots = spot.getPiece().checkKingsMovement(spot);
        }
        return spots;
    }

    public static void eraseAllPossibleMoves () {
        for (int y = 0; y < Chessboard.boardSize; y++) {
            for (int x = 0; x < Chessboard.boardSize; x++) {
                allMoves[x][y] = false;
            }
        }
    }

    // TODO finish, test and implement with turn base
    public static void checkAllThreatenedSpots() {
        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++)
                allThreatenedSpots[x][y] = false;
        }
        for (Spot spot : allSpots) {
            boolean[][] threatenedSpots = new boolean[boardSize][boardSize];
            if (spot.hasPiece()) {
                if (spot.getPiece().isWhite())
                    threatenedSpots = showPossibleMoves(spot);
            }
            if (spot.hasPiece()) {
                for (int y = 0; y < boardSize; y++) {
                    for (int x = 0; x < boardSize; x++) {
                        if (threatenedSpots[x][y])
                            allThreatenedSpots[x][y] = true;
                    }
                }
            }
        }
    }
}
