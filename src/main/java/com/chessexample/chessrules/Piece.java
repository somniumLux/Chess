package com.chessexample.chessrules;

public class Piece {

    private boolean isWhite, isKilled = false;

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

    /*This method returns value 0 if the king's piece path is unobstructed. If the spot
     * is threatened by an enemy piece, it returns 1 and -1 if it's obstructed by
     * a friendly piece or enemy king*/
    private int canKingMoveToSpot(Piece piece, int posX, int posY) {
        Spot checkedSpot = Chessboard.allSpots.get(0);
        for (Spot spot : Chessboard.allSpots) {
            if (spot.getPositionX() == posX && spot.getPositionY() == posY) {
                checkedSpot = spot;
            }
        }
        if (!piece.isWhite()) {
            if (Chessboard.allThreatenedSpotsByWhite[checkedSpot.getPositionX()][checkedSpot.getPositionY()])
                return 1;
        }
        else {
            if (Chessboard.allThreatenedSpotsByBlack[checkedSpot.getPositionX()][checkedSpot.getPositionY()])
                return 1;
        }
                if (!checkedSpot.hasPiece())
            return 0;
        if (checkedSpot.hasPiece() && checkedSpot.getPiece().isWhite != piece.isWhite) {
            if (checkedSpot.getPiece() instanceof King)
                return 1;
            return 0;
        }
        if (checkedSpot.hasPiece()) {
            if (checkedSpot.getPiece().isWhite != piece.isWhite && !(checkedSpot.getPiece() instanceof King))
                return 1;
        }
        return -1;
    }

    private int canMoveToSpot(Piece piece, int posX, int posY) {
        Spot checkedSpot = Chessboard.allSpots.get(0);
        for (Spot spot : Chessboard.allSpots) {
            if (spot.getPositionX() == posX && spot.getPositionY() == posY) {
                checkedSpot = spot;
            }
        }
        if (!checkedSpot.hasPiece())
            return 0;
        if (checkedSpot.hasPiece() && checkedSpot.getPiece().isWhite != piece.isWhite && !(checkedSpot.getPiece() instanceof King))
            return 1;
         if (checkedSpot.hasPiece()) {
            if (checkedSpot.getPiece().isWhite == piece.isWhite)
                return -1;
        }
        return -1;
    }

    // TODO update checkKingsMovement() for checks
    public boolean[][] checkKingsMovement(Spot startingSpot) {
        boolean[][] possibleMoves = new boolean[Chessboard.boardSize][Chessboard.boardSize];
        for (int y = startingSpot.getPositionY() - 1; y <= startingSpot.getPositionY() + 1; y++) {
            for (int x = startingSpot.getPositionX() - 1; x <= startingSpot.getPositionX() + 1; x++) {
                if (canKingMoveToSpot(startingSpot.getPiece(), x,y) != 0)
                    continue;
                try {
                    possibleMoves[x][y] = x != startingSpot.getPositionX() || y != startingSpot.getPositionY();
                } catch (Exception e) {
                    System.out.println(e + " in checkKingsMovement");
                }
            }
        }
        return possibleMoves;
    }

    public boolean[][] checkQueensMovement(Spot startingSpot) {
        boolean[][] possibleMoves1, possibleMoves2;
        possibleMoves1 = checkStraightMovement(startingSpot);
        possibleMoves2 = checkDiagonalMovement(startingSpot);
        for (int x = 0; x < Chessboard.boardSize; x++) {
            for (int y = 0; y < Chessboard.boardSize; y++) {
                if (possibleMoves1[x][y])
                    possibleMoves2[x][y] = true;
            }
        }
        return possibleMoves2;
    }

    public boolean[][] checkStraightMovement(Spot startingSpot) {
        boolean[][] possibleMoves = new boolean[Chessboard.boardSize][Chessboard.boardSize];
        int posX = startingSpot.getPositionX(), posY = startingSpot.getPositionY(), movementStatus;

        for (int i = 1; i < Chessboard.boardSize; i++) {
            int y = posY + i;
            movementStatus = canMoveToSpot(startingSpot.getPiece(), posX, y);
            if (movementStatus == -1)
                break;
            try {
                possibleMoves[posX][y] = true;
            } catch (Exception e) {
                break;
            }
            if (movementStatus == 1)
                break;
        }
        for (int i = 1; i < Chessboard.boardSize; i++) {
            int y = posY - i;
            movementStatus = canMoveToSpot(startingSpot.getPiece(), posX, y);
            if (movementStatus == -1)
                break;
            try {
                possibleMoves[posX][y] = true;
            } catch (Exception e) {
                break;
            }
            if (movementStatus == 1)
                break;
        }
        for (int i = 1; i < Chessboard.boardSize; i++) {
            int x = posX + i;
            movementStatus = canMoveToSpot(startingSpot.getPiece(), x, posY);
            if (movementStatus == -1)
                break;
            try {
                possibleMoves[x][posY] = true;
            } catch (Exception e) {
                break;
            }
            if (movementStatus == 1)
                break;
        }
        for (int i = 1; i < Chessboard.boardSize; i++) {
            int x = posX - i;
            movementStatus = canMoveToSpot(startingSpot.getPiece(), x, posY);
            if (movementStatus == -1)
                break;
            try {
                possibleMoves[x][posY] = true;
            } catch (Exception e) {
                break;
            }
            if (movementStatus == 1)
                break;
        }
        possibleMoves[posX][posY] = false;
        return possibleMoves;
    }

    public boolean[][] checkDiagonalMovement(Spot startingSpot) {
        boolean[][] possibleMoves = new boolean[Chessboard.boardSize][Chessboard.boardSize];
        int posX = startingSpot.getPositionX(), posY = startingSpot.getPositionY(), movementStatus;

        for (int i = 1; i < Chessboard.boardSize; i++) {
            int x = posX + i;
            int y = posY + i;
            movementStatus = canMoveToSpot(startingSpot.getPiece(), x, y);
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
            int y = posY - i;
            movementStatus = canMoveToSpot(startingSpot.getPiece(), x, y);
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
            movementStatus = canMoveToSpot(startingSpot.getPiece(), x, y);
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
            movementStatus = canMoveToSpot(startingSpot.getPiece(), x, y);
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

    // TODO add pawn promotion
    // TODO add "en passant"
    public boolean[][] checkPawnMovement(Spot startingSpot) {
        boolean[][] possibleMoves = new boolean[Chessboard.boardSize][Chessboard.boardSize];
        int posX = startingSpot.getPositionX(), posY = startingSpot.getPositionY();

        // white pawn's forward movement
        if (startingSpot.getPiece().isWhite) {
            if (!Chessboard.checkSpot(startingSpot.getPositionX(), startingSpot.getPositionY() - 1).hasPiece())
                possibleMoves[posX][posY - 1] = true;

            if (startingSpot.getPositionY() == 6) {
                if (!Chessboard.checkSpot(startingSpot.getPositionX(), startingSpot.getPositionY() - 2).hasPiece()
                        && !Chessboard.checkSpot(startingSpot.getPositionX(), startingSpot.getPositionY() - 1).hasPiece())
                    possibleMoves[posX][posY - 2] = true;
            }
        }
        // checking if white pawn can eat
        for (Spot spotInBoard : Chessboard.allSpots) {
            if ((spotInBoard.getPositionX() == posX + 1 || spotInBoard.getPositionX() == posX - 1) && spotInBoard.getPositionY() == posY + 1) {
                if (spotInBoard.hasPiece()) {
                    if (spotInBoard.getPiece().isWhite != startingSpot.getPiece().isWhite && !(spotInBoard.getPiece() instanceof King)) {
                        try {
                            possibleMoves[spotInBoard.getPositionX()][spotInBoard.getPositionY()] = true;
                        } catch (Exception e) {
                            break;
                        }
                    }
                }
            }
        }
        // black pawn's forward movement
        if (!startingSpot.getPiece().isWhite) {
            if (!Chessboard.checkSpot(startingSpot.getPositionX(), startingSpot.getPositionY() + 1).hasPiece())
                possibleMoves[posX][posY + 1] = true;

            if (startingSpot.getPositionY() == 1) {
                if (!Chessboard.checkSpot(startingSpot.getPositionX(), startingSpot.getPositionY() + 2).hasPiece()
                        && !Chessboard.checkSpot(startingSpot.getPositionX(), startingSpot.getPositionY() + 1).hasPiece())
                    possibleMoves[posX][posY + 2] = true;
            }
        }
        // checking if black pawn can eat
        for (Spot spotInBoard : Chessboard.allSpots) {
            if ((spotInBoard.getPositionX() == posX + 1 || spotInBoard.getPositionX() == posX - 1) && spotInBoard.getPositionY() == posY - 1) {
                if (spotInBoard.hasPiece()) {
                    if (spotInBoard.getPiece().isWhite != startingSpot.getPiece().isWhite && !(spotInBoard.getPiece() instanceof King)) {
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

    public boolean[][] checkKnightsMovement(Spot startingSpot) {
        boolean[][] possibleMoves = new boolean[Chessboard.boardSize][Chessboard.boardSize];
        int posX = startingSpot.getPositionX(), posY = startingSpot.getPositionY();

        for (int y = 0; y < Chessboard.boardSize; y++) {
            for (int x = 0; x < Chessboard.boardSize; x++) {
                Spot endingSpot = Chessboard.checkSpot(x,y);
                if (endingSpot.hasPiece()) {
                    if (endingSpot.getPiece() instanceof King || endingSpot.getPiece().isWhite == startingSpot.getPiece().isWhite)
                        continue;
                }
                try {
                    if (y == posY + 2 && x == posX + 1)
                        possibleMoves[x][y] = true;
                    if (y == posY + 2 && x == posX - 1)
                        possibleMoves[x][y] = true;
                    if (y == posY - 2 && x == posX + 1)
                        possibleMoves[x][y] = true;
                    if (y == posY - 2 && x == posX - 1)
                        possibleMoves[x][y] = true;
                    if (x == posX + 2 && y == posY + 1)
                        possibleMoves[x][y] = true;
                    if (x == posX + 2 && y == posY - 1)
                        possibleMoves[x][y] = true;
                    if (x == posX - 2 && y == posY + 1)
                        possibleMoves[x][y] = true;
                    if (x == posX - 2 && y == posY - 1)
                        possibleMoves[x][y] = true;
                } catch (Exception e) {
                    System.out.println(e + "in checkKnightsMovement");
                }
            }
        }
        return possibleMoves;
    }
}