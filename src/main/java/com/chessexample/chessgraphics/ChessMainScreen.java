package com.chessexample.chessgraphics;

import com.chessexample.chessrules.Chessboard;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ChessMainScreen extends VBox {

    private final Canvas canvas;
    static final double canvasWidth = 800, canvasHeight = 760;
    static final int tileSize = 60;

    public ChessMainScreen() {
        this.canvas = new Canvas(canvasWidth, canvasHeight);
        this.getChildren().addAll(this.canvas, ImageResource.blackPawnImageView);
        this.canvas.setOnMousePressed(this::onMousePressed);
    }

    private void onMousePressed(MouseEvent event) {
        double x = event.getX(), y = event.getY();
        int mouseX = (int) x / tileSize + 1, mouseY = (int) y / tileSize + 1;
        System.out.println("x: " + mouseX + ", y: " + mouseY);
    }

    public void drawBoard() {
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        int xCounter = 0, yCounter = 0;
        for (int y = 0; y < tileSize * Chessboard.boardSize; y += tileSize) {
            for (int x = 0; x < tileSize * Chessboard.boardSize; x += tileSize) {
                if ((xCounter + yCounter) % 2 == 0)
                    g.setFill(Color.BURLYWOOD);
                else
                    g.setFill(Color.BEIGE);
                g.fillRect(x, y, tileSize, tileSize);

                if (y == tileSize)
                    g.drawImage(ImageResource.blackPawnImageView.getImage(), x, y);
                if ((x == 0 || x == tileSize*7) && y == 0)
                    g.drawImage(ImageResource.blackRookImageView.getImage(), x, y);
                if ((x == tileSize || x == tileSize*6) && y == 0)
                    g.drawImage(ImageResource.blackKnightImageView.getImage(), x, y);
                if ((x == tileSize*2 || x == tileSize*5) && y == 0)
                    g.drawImage(ImageResource.blackBishopImageView.getImage(), x, y);
                if (x == tileSize*3 && y == 0)
                    g.drawImage(ImageResource.blackQueenImageView.getImage(), x, y);
                if (x == tileSize*4 && y == 0)
                    g.drawImage(ImageResource.blackKingImageView.getImage(), x, y);

                if (y == 6*tileSize)
                    g.drawImage(ImageResource.whitePawnImageView.getImage(), x, y);
                if ((x == 0 || x == tileSize*7) && y == tileSize*7)
                    g.drawImage(ImageResource.whiteRookImageView.getImage(), x, y);
                if ((x == tileSize || x == tileSize*6) && y == tileSize*7)
                    g.drawImage(ImageResource.whiteKnightImageView.getImage(), x, y);
                if ((x == tileSize*2 || x == tileSize*5) && y == tileSize*7)
                    g.drawImage(ImageResource.whiteBishopImageView.getImage(), x, y);
                if (x == tileSize*3 && y == tileSize*7)
                    g.drawImage(ImageResource.whiteQueenImageView.getImage(), x, y);
                if (x == tileSize*4 && y == tileSize*7)
                    g.drawImage(ImageResource.whiteKingImageView.getImage(), x, y);

                xCounter++;
            }
            yCounter++;
            g.setFill(Color.BURLYWOOD);
        }
    }
}
