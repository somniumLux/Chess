package com.chessexample.chessgraphics;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class ImageResource {

    //pawns
    private final static File blackPawnFile = new File("src/main/resources/images/pawn_black.png");
    final static Image blackPawnImage = new Image(blackPawnFile.toURI().toString());
    static ImageView blackPawnImageView = new ImageView(blackPawnImage);

    private final static File whitePawnFile = new File("src/main/resources/images/pawn_white.png");
    final static Image whitePawnImage = new Image(whitePawnFile.toURI().toString());
    static ImageView whitePawnImageView = new ImageView(whitePawnImage);

    //kings
    private final static File blackKingFile = new File("src/main/resources/images/king_black.png");
    final static Image blackKingImage = new Image(blackKingFile.toURI().toString());
    static ImageView blackKingImageView = new ImageView(blackKingImage);

    private final static File whiteKingFile = new File("src/main/resources/images/king_white.png");
    final static Image whiteKingImage = new Image(whiteKingFile.toURI().toString());
    static ImageView whiteKingImageView = new ImageView(whiteKingImage);

    //queen
    private final static File blackQueenFile = new File("src/main/resources/images/queen_black.png");
    private final static Image blackQueenImage = new Image(blackQueenFile.toURI().toString());
    static ImageView blackQueenImageView = new ImageView(blackQueenImage);

    private final static File whiteQueenFile = new File("src/main/resources/images/queen_white.png");
    final static Image whiteQueenImage = new Image(whiteQueenFile.toURI().toString());
    static ImageView whiteQueenImageView = new ImageView(whiteQueenImage);

    //rooks
    private final static File blackRookFile = new File("src/main/resources/images/rook_black.png");
    final static Image blackRookImage = new Image(blackRookFile.toURI().toString());
    static ImageView blackRookImageView = new ImageView(blackRookImage);

    private final static File whiteRookFile = new File("src/main/resources/images/rook_white.png");
    final static Image whiteRookImage = new Image(whiteRookFile.toURI().toString());
    static ImageView whiteRookImageView = new ImageView(whiteRookImage);

    //bishops
    private final static File blackBishopFile = new File("src/main/resources/images/bishop_black.png");
    final static Image blackBishopImage = new Image(blackBishopFile.toURI().toString());
    static ImageView blackBishopImageView = new ImageView(blackBishopImage);

    private final static File whiteBishopFile = new File("src/main/resources/images/bishop_white.png");
    final static Image whiteBishopImage = new Image(whiteBishopFile.toURI().toString());
    static ImageView whiteBishopImageView = new ImageView(whiteBishopImage);

    //knights
    private final static File blackKnightFile = new File("src/main/resources/images/knight_black.png");
    final static Image blackKnightImage = new Image(blackKnightFile.toURI().toString());
    static ImageView blackKnightImageView = new ImageView(blackKnightImage);

    private final static File whiteKnightFile = new File("src/main/resources/images/knight_white.png");
    final static Image whiteKnightImage = new Image(whiteKnightFile.toURI().toString());
    static ImageView whiteKnightImageView = new ImageView(whiteKnightImage);

}
