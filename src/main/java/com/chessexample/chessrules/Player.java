package com.chessexample.chessrules;

public class Player {

    private boolean hasTurn;

    public Player(boolean hasTurn) {
        this.hasTurn = hasTurn;
    }

    public boolean isHasTurn() {
        return hasTurn;
    }

    public void setHasTurn(boolean hasTurn) {
        this.hasTurn = hasTurn;
    }
}
