package com.chessexample.chessrules;

public class Spot {

    private Piece piece;
    private final int positionX;
    private final int positionY;
    private boolean hasPiece, isSelected, isThreatenedByWhite, isThreatenedByBlack;

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

    public boolean isThreatenedByWhite() {
        return isThreatenedByWhite;
    }

    public void setThreatenedByWhite() {
        isThreatenedByWhite = true;
    }

    public boolean isThreatenedByBlack() {
        return isThreatenedByBlack;
    }

    public void setThreatenedByBlack() {
        isThreatenedByBlack = true;
    }

    public boolean hasPiece() {
        return hasPiece;
    }

    public void setHasPiece(boolean hasPiece) {
        this.hasPiece = hasPiece;
    }

    public boolean isHasPiece() {
        return hasPiece;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void deletePiece() {
        piece = null;
        this.setHasPiece(false);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spot spot = (Spot) o;
        return positionX == spot.positionX && positionY == spot.positionY;
    }

    @Override
    public String toString() {
        return "Spot{" + " x = " + positionX +
                ", y = " + positionY +
                ", piece = " + piece +
                ", hasPiece = " + hasPiece +
                '}';
    }
}
