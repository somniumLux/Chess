package com.chessexample.javafxdemo;

import java.util.List;

public class Piece {

    private boolean isWhite = false;
    private boolean isKilled = false;
    private final int maxMovementDistance;

    public Piece(int maxMovementDistance) {
        this.maxMovementDistance = maxMovementDistance;
    }

    protected int getMaxMovementDistance() {
        return maxMovementDistance;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }

    public boolean isKilled() {
        return isKilled;
    }

    public void setKilled(boolean killed) {
        isKilled = killed;
    }

    // TODO add checking if spot already has a piece of same colour

    public boolean[][] checkKingsMovement(Spot currentSpot) {
        boolean[][] possibleMoves = new boolean[ChessBoard.boardSize][ChessBoard.boardSize];
        for (int y = currentSpot.getPositionY() - 1; y <= currentSpot.getPositionY() + 1; y++) {
            for (int x = currentSpot.getPositionX() - 1; x <= currentSpot.getPositionX() + 1; x++) {
                try {
                    possibleMoves[x][y] = x != currentSpot.getPositionX() || y != currentSpot.getPositionY();
                } catch (Exception e) {
                    System.out.println(e + "in checkKingsMovement");
                }
            }
        }
        return possibleMoves;
    }

    public boolean[][] checkStraightMovement(Spot currentSpot) {
        boolean[][] possibleMoves = new boolean[ChessBoard.boardSize][ChessBoard.boardSize];
        int posX = currentSpot.getPositionX();
        int posY = currentSpot.getPositionY();
        int maxMovementDistance = currentSpot.getPiece().getMaxMovementDistance();

        for (int y = posY - maxMovementDistance; y <= posY + maxMovementDistance; y++) {
            for (int x = posX - maxMovementDistance; x <= posX + maxMovementDistance; x++) {
                if (x < 0 || y < 0 || x > ChessBoard.boardSize - 1 || y > ChessBoard.boardSize - 1)
                    continue;
                if (x == posX || y == posY)
                    possibleMoves[x][y] = true;
            }
        }
        possibleMoves[posX][posY] = false;
        return possibleMoves;
    }

    public boolean[][] checkDiagonalMovement(Spot currentSpot) {
        boolean[][] possibleMoves = new boolean[ChessBoard.boardSize][ChessBoard.boardSize];
        int posX = currentSpot.getPositionX();
        int posY = currentSpot.getPositionY();

        for (int i = 1; i < ChessBoard.boardSize; i++) {
            int x = posX + i;
            int y = posY + i;
            try {
                possibleMoves[x][y] = true;
            } catch (Exception e) {
                break;
            }
        }
        for (int i = 1; i < ChessBoard.boardSize; i++) {
            int x = posX - i;
            int y = posY - 1;
            try {
                possibleMoves[x][y] = true;
            } catch (Exception e) {
                break;
            }
        }
        for (int i = 1; i < ChessBoard.boardSize; i++) {
            int x = posX + i;
            int y = posY - i;
            try {
                possibleMoves[x][y] = true;
            } catch (Exception e) {
                break;
            }
        }
        for (int i = 1; i < ChessBoard.boardSize; i++) {
            int x = posX - i;
            int y = posY + i;
            try {
                possibleMoves[x][y] = true;
            } catch (Exception e) {
                break;
            }
        }
        return possibleMoves;
    }

    // TODO add "en passant" to checkPawnMovement method
    public boolean[][] checkPawnMovement(Spot currentSpot, List<Spot> allSpots) {
        boolean[][] possibleMoves = new boolean[ChessBoard.boardSize][ChessBoard.boardSize];
        int posX = currentSpot.getPositionX();
        int posY = currentSpot.getPositionY();

        if (currentSpot.getPiece().isWhite)
            possibleMoves[posX][posY + 1] = true;
        // checking if white pawn can eat
        for (Spot spotInBoard : allSpots) {
            if ((spotInBoard.getPositionX() == posX + 1 || spotInBoard.getPositionX() == posX - 1) && spotInBoard.getPositionY() == posY + 1) {
                if (spotInBoard.hasPiece()) {
                    if (!spotInBoard.getPiece().isWhite) {
                        try {
                            possibleMoves[spotInBoard.getPositionX()][spotInBoard.getPositionY()] = true;
                        } catch (Exception e) {
                            break;
                        }
                    }
                }
            }

        }

        if (!currentSpot.getPiece().isWhite)
            possibleMoves[posX][posY - 1] = true;
        // checking if black pawn can eat
        for (Spot spotInBoard : allSpots) {
            if ((spotInBoard.getPositionX() == posX + 1 || spotInBoard.getPositionX() == posX - 1) && spotInBoard.getPositionY() == posY - 1) {
                if (spotInBoard.hasPiece()) {
                    if (spotInBoard.getPiece().isWhite) {
                        try {
                            possibleMoves[spotInBoard.getPositionX()][spotInBoard.getPositionY()] = true;
                        } catch (Exception e) {
                            break;
                        }
                    }
                }
            }

        }

        return possibleMoves;
    }

    //TODO create checkKnightMovement method

}
