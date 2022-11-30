module com.example.hescoin {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.hescoin to javafx.fxml;
    exports com.example.hescoin;
}