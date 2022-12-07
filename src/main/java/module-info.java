module com.example.hescoin {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.example.hescoin to javafx.fxml;
    exports com.example.hescoin;
    exports com.example.hescoin.Data;
    opens com.example.hescoin.Data to javafx.fxml;
}