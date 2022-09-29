package com.chessexample.javafxdemo;

public class Spot {

    private Piece piece;
    private int positionX;
    private int positionY;
    private boolean isThreatened;
    private boolean hasPiece = false;

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

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
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
