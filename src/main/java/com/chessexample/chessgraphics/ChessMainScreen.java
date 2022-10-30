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

    private static Spot currentlySelectedSpot, lastClickedSPot;
    private int mousePressCounter = 0;

    public ChessMainScreen() {
        this.canvas = new Canvas(canvasWidth, canvasHeight);
        this.getChildren().addAll(this.canvas);
        this.canvas.setOnMousePressed(this::onMousePressed);

        Chessboard.createSpots();
    }

    // TODO finish implementation of moving a piece by clicking on a starting and then ending spot
    private void onMousePressed(MouseEvent event) {
        double x = event.getX(), y = event.getY();
        int mouseX = (int) x / tileSize + 1, mouseY = (int) y / tileSize + 1;
        Spot clickedSpot = Chessboard.checkSpot(mouseX - 1, mouseY - 1);
        System.out.println(clickedSpot.toString());

        if (mousePressCounter == 0)
            lastClickedSPot = clickedSpot;
        mousePressCounter++;

        if (clickedSpot != lastClickedSPot) {
            lastClickedSPot.setSelected(false);
            lastClickedSPot = clickedSpot;
        }
        if (clickedSpot.isSelected()) {
            clickedSpot.setSelected(false);
            Chessboard.eraseAllPossibleMoves();
            drawBoard();
            return;
        }

        clickedSpot.setSelected(true);
        Chessboard.showPossibleMoves(clickedSpot);
        drawBoard();
    }

    public void drawBoard() {
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        int xCounter = 0, yCounter = 0;
        for (int y = 0; y < tileSize * Chessboard.boardSize; y += tileSize) {
            for (int x = 0; x < tileSize * Chessboard.boardSize; x += tileSize) {
                if (Chessboard.allMoves[x/tileSize][y/tileSize] && (xCounter + yCounter) % 2 == 0)
                    g.setFill(Color.LIGHTGREEN);
                else if (Chessboard.allMoves[x / tileSize][y / tileSize])
                    g.setFill(Color.GREENYELLOW);
                else if ((xCounter + yCounter) % 2 == 0)
                    g.setFill(Color.BURLYWOOD);
                else
                    g.setFill(Color.BEIGE);
                g.fillRect(x, y, tileSize, tileSize);
                xCounter++;

                int posx = x / tileSize, posy = y / tileSize;
                Spot spot = Chessboard.checkSpot(posx, posy);
                Piece piece = spot.getPiece();

                g.drawImage(findImage(piece), x, y);
            }
            yCounter++;
            g.setFill(Color.BURLYWOOD);
        }
    }
}
