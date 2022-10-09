module com.chessexample.javafxdemo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.chessexample.chessrules to javafx.fxml;
    exports com.chessexample.chessrules;
    exports com.chessexample.chessgraphics;
    opens com.chessexample.chessgraphics to javafx.fxml;
}