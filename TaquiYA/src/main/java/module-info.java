module com.taquiya {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.taquiya to javafx.fxml;
    exports com.taquiya;
    requires java.sql;
    requires java.base;
    opens com.taquiya.controlador to javafx.fxml;
}
