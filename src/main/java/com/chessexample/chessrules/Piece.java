package com.chessexample.chessrules;

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

    // TODO add checking if spot already has a piece (added only to diagonalMovement)
    // TODO fix pieces being able to eat the enemy king

    /*This method returns value 0 if the piece is unobstructed in its path. If it's
    * obstructed by an enemy piece, it returns 1 and -1 if it's obstructed by
    * a friendly piece*/
    private int canMove(Piece piece, int posX, int posY) {
        Spot checkedSpot = Chessboard.allSpots.get(0);
        for (Spot spot : Chessboard.allSpots) {
            if (spot.getPositionX() == posX && spot.getPositionY() == posY) {
                checkedSpot = spot;
            }
        }
        if (!checkedSpot.hasPiece())
            return 0;
        if (checkedSpot.hasPiece()) {
            if (checkedSpot.getPiece().isWhite && !piece.isWhite() || !checkedSpot.getPiece().isWhite && piece.isWhite())
                return 1;
        }
        return -1;
    }

    public boolean[][] checkKingsMovement(Spot startingSpot) {
        boolean[][] possibleMoves = new boolean[Chessboard.boardSize][Chessboard.boardSize];
        for (int y = startingSpot.getPositionY() - 1; y <= startingSpot.getPositionY() + 1; y++) {
            for (int x = startingSpot.getPositionX() - 1; x <= startingSpot.getPositionX() + 1; x++) {
                try {
                    possibleMoves[x][y] = x != startingSpot.getPositionX() || y != startingSpot.getPositionY();
                } catch (Exception e) {
                    System.out.println(e + "in checkKingsMovement");
                }
            }
        }
        return possibleMoves;
    }

    public boolean[][] checkStraightMovement(Spot startingSpot) {
        boolean[][] possibleMoves = new boolean[Chessboard.boardSize][Chessboard.boardSize];
        int posX = startingSpot.getPositionX();
        int posY = startingSpot.getPositionY();
        int maxMovementDistance = startingSpot.getPiece().getMaxMovementDistance();

        for (int y = posY - maxMovementDistance; y <= posY + maxMovementDistance; y++) {
            for (int x = posX - maxMovementDistance; x <= posX + maxMovementDistance; x++) {
                if (x < 0 || y < 0 || x > Chessboard.boardSize - 1 || y > Chessboard.boardSize - 1)
                    continue;
                if (x == posX || y == posY)
                    possibleMoves[x][y] = true;
            }
        }
        possibleMoves[posX][posY] = false;
        return possibleMoves;
    }

    public boolean[][] checkDiagonalMovement(Spot startingSpot) {
        boolean[][] possibleMoves = new boolean[Chessboard.boardSize][Chessboard.boardSize];
        int posX = startingSpot.getPositionX();
        int posY = startingSpot.getPositionY();
        int movementStatus;

        for (int i = 1; i < Chessboard.boardSize; i++) {
            int x = posX + i;
            int y = posY + i;
            movementStatus = canMove(startingSpot.getPiece(), x, y);
            if (movementStatus == -1)
                break;
            try {
                possibleMoves[x][y] = true;
            } catch (Exception e) {
                break;
            }
            if (movementStatus == 1)
                break;
        }
        for (int i = 1; i < Chessboard.boardSize; i++) {
            int x = posX - i;
            int y = posY - 1;
            movementStatus = canMove(startingSpot.getPiece(), x, y);
            if (movementStatus == -1)
                break;
            try {
                possibleMoves[x][y] = true;
            } catch (Exception e) {
                break;
            }
            if (movementStatus == 1)
                break;
        }
        for (int i = 1; i < Chessboard.boardSize; i++) {
            int x = posX + i;
            int y = posY - i;
            movementStatus = canMove(startingSpot.getPiece(), x, y);
            if (movementStatus == -1)
                break;
            try {
                possibleMoves[x][y] = true;
            } catch (Exception e) {
                break;
            }
            if (movementStatus == 1)
                break;
        }
        for (int i = 1; i < Chessboard.boardSize; i++) {
            int x = posX - i;
            int y = posY + i;
            movementStatus = canMove(startingSpot.getPiece(), x, y);
            if (movementStatus == -1)
                break;
            try {
                possibleMoves[x][y] = true;
            } catch (Exception e) {
                break;
            }
            if (movementStatus == 1)
                break;
        }
        return possibleMoves;
    }

    // TODO add "en passant" to checkPawnMovement method
    public boolean[][] checkPawnMovement(Spot startingSpot) {
        boolean[][] possibleMoves = new boolean[Chessboard.boardSize][Chessboard.boardSize];
        int posX = startingSpot.getPositionX();
        int posY = startingSpot.getPositionY();

        if (startingSpot.getPiece().isWhite)
            possibleMoves[posX][posY + 1] = true;

        for (Spot spotInBoard : Chessboard.allSpots) {
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

        if (!startingSpot.getPiece().isWhite)
            possibleMoves[posX][posY - 1] = true;
        // checking if black pawn can eat
        for (Spot spotInBoard : Chessboard.allSpots) {
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

    // TODO test checkKnightsMovement method
    public boolean[][] checkKnightsMovement(Spot startingSpot) {
        boolean[][] possibleMoves = new boolean[Chessboard.boardSize][Chessboard.boardSize];
        int posX = startingSpot.getPositionX();
        int posY = startingSpot.getPositionY();

        for (int y = 0; y <= Chessboard.boardSize; y++) {
            for (int x = 0; x <= Chessboard.boardSize; x++) {
                try {
                    if (y == posY + 2 && x == posX + 1)
                        possibleMoves[x][y] = true;
                    if (y == posY + 2 && x == posX - 1)
                        possibleMoves[x][y] = true;
                    if (y == posY - 2 && x == posX + 1)
                        possibleMoves[x][y] = true;
                    if (y == posY - 2 && x == posX - 1)
                        possibleMoves[x][y] = true;
                    if (y == posX + 2 && x == posY + 1)
                        possibleMoves[x][y] = true;
                    if (y == posX + 2 && x == posY - 1)
                        possibleMoves[x][y] = true;
                    if (y == posX - 2 && x == posY + 1)
                        possibleMoves[x][y] = true;
                    if (y == posX - 2 && x == posY - 1)
                        possibleMoves[x][y] = true;
                } catch (Exception e) {
                    System.out.println(e + "in checkKnightsMovement");
                }
            }
        }
        return possibleMoves;
    }
}
