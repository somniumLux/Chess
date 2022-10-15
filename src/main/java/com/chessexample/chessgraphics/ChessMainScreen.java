package com.chessexample.chessgraphics;

import com.chessexample.chessrules.Chessboard;
import com.chessexample.chessrules.Piece;
import com.chessexample.chessrules.Spot;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static com.chessexample.chessgraphics.ImageResource.findImage;

public class ChessMainScreen extends VBox {

    private final Canvas canvas;
    static final double canvasWidth = 800, canvasHeight = 760;
    static final int tileSize = 60;

    public ChessMainScreen() {
        this.canvas = new Canvas(canvasWidth, canvasHeight);
        this.getChildren().addAll(this.canvas);
        this.canvas.setOnMousePressed(this::onMousePressed);

        Chessboard.createSpots();
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

                Piece piece = new Piece();
                int posx = x/tileSize, posy = y/tileSize;
                for (Spot spot : Chessboard.allSpots) {
                    if (spot.hasPiece()) {
                        if (spot.getPositionX() == posx && spot.getPositionY() == posy)
                            piece = spot.getPiece();
                    }
                }
                g.drawImage(findImage(piece), x, y);
                xCounter++;
            }
            yCounter++;
            g.setFill(Color.BURLYWOOD);
        }
    }
}
