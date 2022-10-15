package com.chessexample.chessrules;

public class Spot {

    private Piece piece;
    private final int positionX;
    private final int positionY;
    private boolean isThreatened;
    private boolean hasPiece;

    public Spot(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public boolean isThreatened() {
        return isThreatened;
    }

    public void setThreatened(boolean threatened) {
        isThreatened = threatened;
    }

    public boolean hasPiece() {
        return hasPiece;
    }

    public void setHasPiece(boolean hasPiece) {
        this.hasPiece = hasPiece;
    }

}
