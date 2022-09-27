package com.chessexample.javafxdemo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {
        GridPane gridPane = new GridPane();

        int squareNumber = 0;
        double size = 90;
        for (int i = 0; i < 8; i++) {
            squareNumber++;
            for (int j = 0; j < 8; j++) {
                Rectangle rectangle = new Rectangle(size,size,size,size);
                if (squareNumber % 2 == 0)
                    rectangle.setFill(Color.WHITE);
                else
                    rectangle.setFill(Color.BURLYWOOD);
                gridPane.add(rectangle,j,i);
                squareNumber++;
            }
        }

        Scene scene = new Scene(gridPane);
        stage.setTitle("Welcome to the chessboard!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}