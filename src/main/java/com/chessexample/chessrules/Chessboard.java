package com.chessexample.chessrules;

import java.util.ArrayList;
import java.util.List;

public class Chessboard {

    final static public int boardSize = 8;
    public static List<Spot> allSpots = new ArrayList<>();
    public static boolean[][] allMoves = new boolean[boardSize][boardSize];
    public static boolean[][] allThreatenedSpotsByWhite = new boolean[boardSize][boardSize];
    public static boolean[][] allThreatenedSpotsByBlack = new boolean[boardSize][boardSize];

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
        boolean[][] spots = new boolean[boardSize][boardSize];
        if (!spot.hasPiece()) {
            for (int x = 0; x < boardSize; x++) {
                for (int y = 0; y < boardSize; y++) {
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
        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++) {
                allMoves[x][y] = false;
            }
        }
    }

    // TODO fix commented bug (spots threatened but the king)
    /**
     * Bug with spots that are threatened by the king. If the king is brought to
     * the most left or right column, threatened spots are not updated properly (some spots in the most
     * left and right columns are left uncolored until player presses on the king piece again and
     * some spots towards the middle of the board are left colored until the king is brought back closer).
     */
    public static void updateAllThreatenedSpots() {
        for (Spot spot : allSpots) {
            spot.setThreatenedByWhite(false);
            spot.setThreatenedByBlack(false);
        }
        updateThreatenedSpotsByBlack();
        updateThreatenedSpotsByWhite();
        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++) {
                Spot spot = checkSpot(x,y);
                if (allThreatenedSpotsByWhite[x][y])
                    spot.setThreatenedByWhite(true);
                if (allThreatenedSpotsByBlack[x][y])
                    spot.setThreatenedByBlack(true);
            }
        }
    }

    public static void updateThreatenedSpotsByWhite() {
        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++)
                allThreatenedSpotsByWhite[x][y] = false;
        }
        for (Spot spot : allSpots) {
            boolean[][] threatenedSpots = new boolean[boardSize][boardSize];
            if (spot.hasPiece()) {
                if (spot.getPiece().isWhite()) {
                    if (spot.getPiece() instanceof Pawn) {
                        try {
                            allThreatenedSpotsByWhite[spot.getPositionX() + 1][spot.getPositionY() - 1] = true;
                            allThreatenedSpotsByWhite[spot.getPositionX() - 1][spot.getPositionY() - 1] = true;
                        } catch (Exception e) {
                            System.out.println(e + " in threatened spots by pawns");
                        }
                    }
                    else
                        threatenedSpots = showPossibleMoves(spot);

                    if (spot.getPiece() instanceof King) {
                        for (int y = spot.getPositionY() - 1; y <= spot.getPositionY() + 1 ; y++) {
                            for (int x = spot.getPositionX() - 1; x <= spot.getPositionX() + 1 ; x++) {
                                try {
                                    threatenedSpots[x][y] = true;
                                } catch (Exception e) {
                                    System.out.println(e + " in threatened spots by kings");
                                }
                            }
                        }
                    }                    
                }
            }
            if (spot.hasPiece()) {
                for (int y = 0; y < boardSize; y++) {
                    for (int x = 0; x < boardSize; x++) {
                        if (threatenedSpots[x][y])
                            allThreatenedSpotsByWhite[x][y] = true;
                    }
                }
            }
        }
    }

    public static void updateThreatenedSpotsByBlack() {
        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++)
                allThreatenedSpotsByBlack[x][y] = false;
        }
        for (Spot spot : allSpots) {
            boolean[][] threatenedSpots = new boolean[boardSize][boardSize];
            if (spot.hasPiece()) {
                if (!spot.getPiece().isWhite()) {
                    if (spot.getPiece() instanceof Pawn) {
                        try {
                            allThreatenedSpotsByBlack[spot.getPositionX() + 1][spot.getPositionY() + 1] = true;
                            allThreatenedSpotsByBlack[spot.getPositionX() - 1][spot.getPositionY() + 1] = true;
                        } catch (Exception e) {
                            System.out.println(e + " in threatened spots by pawns");
                        }
                    }
                    else
                        threatenedSpots = showPossibleMoves(spot);

                    if (spot.getPiece() instanceof King) {
                        for (int y = spot.getPositionY() - 1; y <= spot.getPositionY() + 1 ; y++) {
                            for (int x = spot.getPositionX() - 1; x <= spot.getPositionX() + 1 ; x++) {
                                try {
                                    threatenedSpots[x][y] = true;
                                } catch (Exception e) {
                                    System.out.println(e + " in threatened spots by kings");
                                }

                            }
                        }
                    }
                }
            }
            if (spot.hasPiece()) {
                for (int y = 0; y < boardSize; y++) {
                    for (int x = 0; x < boardSize; x++) {
                        if (threatenedSpots[x][y])
                            allThreatenedSpotsByBlack[x][y] = true;
                    }
                }
            }
        }
    }
}
