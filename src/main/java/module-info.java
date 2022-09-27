module com.chessexample.javafxdemo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.chessexample.javafxdemo to javafx.fxml;
    exports com.chessexample.javafxdemo;
}