package com.chessexample.chessgraphics;

import com.chessexample.chessrules.*;
import javafx.scene.image.Image;

import java.io.File;

public class ImageResource {

    //pawns
    private final static File blackPawnFile = new File("src/main/resources/images/pawn_black.png");
    final static Image blackPawn = new Image(blackPawnFile.toURI().toString());
    private final static File whitePawnFile = new File("src/main/resources/images/pawn_white.png");
    final static Image whitePawn = new Image(whitePawnFile.toURI().toString());

    //kings
    private final static File blackKingFile = new File("src/main/resources/images/king_black.png");
    final static Image blackKing = new Image(blackKingFile.toURI().toString());

    private final static File whiteKingFile = new File("src/main/resources/images/king_white.png");
    final static Image whiteKing = new Image(whiteKingFile.toURI().toString());

    //queen
    private final static File blackQueenFile = new File("src/main/resources/images/queen_black.png");
    private final static Image blackQueen = new Image(blackQueenFile.toURI().toString());
    private final static File whiteQueenFile = new File("src/main/resources/images/queen_white.png");
    final static Image whiteQueen = new Image(whiteQueenFile.toURI().toString());

    //rooks
    private final static File blackRookFile = new File("src/main/resources/images/rook_black.png");
    final static Image blackRook = new Image(blackRookFile.toURI().toString());
    private final static File whiteRookFile = new File("src/main/resources/images/rook_white.png");
    final static Image whiteRook = new Image(whiteRookFile.toURI().toString());

    //bishops
    private final static File blackBishopFile = new File("src/main/resources/images/bishop_black.png");
    final static Image blackBishop = new Image(blackBishopFile.toURI().toString());
    private final static File whiteBishopFile = new File("src/main/resources/images/bishop_white.png");
    final static Image whiteBishop = new Image(whiteBishopFile.toURI().toString());

    //knights
    private final static File blackKnightFile = new File("src/main/resources/images/knight_black.png");
    final static Image blackKnight = new Image(blackKnightFile.toURI().toString());
    private final static File whiteKnightFile = new File("src/main/resources/images/knight_white.png");
    final static Image whiteKnight = new Image(whiteKnightFile.toURI().toString());

    public static Image findImage(Piece piece) {
        if (piece instanceof Pawn) {
            if (piece.isWhite())
                return whitePawn;
            return blackPawn;
        }
        if (piece instanceof Knight) {
            if (piece.isWhite())
                return whiteKnight;
            return blackKnight;
        }
        if (piece instanceof Bishop) {
            if (piece.isWhite())
                return whiteBishop;
            return blackBishop;
        }
        if (piece instanceof Rook) {
            if (piece.isWhite())
                return whiteRook;
            return blackRook;
        }
        if (piece instanceof King) {
            if (piece.isWhite())
                return whiteKing;
            return blackKing;
        }
        if (piece instanceof Queen) {
            if (piece.isWhite())
                return whiteQueen;
            return blackQueen;
        }
        return null;
    }
}
