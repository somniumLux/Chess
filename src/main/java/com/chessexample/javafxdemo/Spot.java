package com.chessexample.javafxdemo;

public class Spot {

    private Piece piece;
    private int positionX;
    private int positionY;
    private boolean isThreatened;

    public Spot(Piece piece, int positionX, int positionY) {
        this.piece = piece;
        this.positionX = positionX;
        this.positionY = positionY;
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
}
