package com.chessexample.chessgraphics;

import com.chessexample.chessrules.Chessboard;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ChessMainScreen extends VBox {

    private Canvas canvas;
    private Chessboard chessboard;
    static final double canvasWidth = 800, canvasHeight = 760;
    static final int tileWidth = 90, tileHeight = 90;

    public ChessMainScreen () {
        this.canvas = new Canvas(canvasWidth, canvasHeight);
        this.getChildren().addAll(this.canvas);
    }

    public void draw() {
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        int xCounter = 0;
        int yCounter = 0;
        for (int y = 0; y < tileHeight*Chessboard.boardSize; y += tileHeight) {
            for (int x = 0; x < tileWidth*Chessboard.boardSize; x += tileWidth) {
                if ((xCounter + yCounter) % 2 == 0)
                    g.setFill(Color.BURLYWOOD);
                else
                    g.setFill(Color.BEIGE);

                g.fillRect(x,y,tileWidth,tileHeight);
                xCounter++;
            }
            yCounter++;
        }
        g.setFill(Color.BURLYWOOD);
    }

}
