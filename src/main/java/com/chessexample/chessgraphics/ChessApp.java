package com.chessexample.chessgraphics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChessApp extends Application {

    @Override
    public void start(Stage stage) {
        ChessMainScreen chessMainScreen = new ChessMainScreen();
        Scene scene = new Scene(chessMainScreen, 480, 480);
        stage.setScene(scene);
        stage.show();

        chessMainScreen.drawBoard();
    }

    public static void main(String[] args) {
        launch();
    }
}