module com.example.hescoin {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.hescoin to javafx.fxml;
    exports com.example.hescoin;
    exports com.example.hescoin.Threads;
    opens com.example.hescoin.ServiceData to javafx.fxml;
    opens com.example.hescoin.Controller to javafx.fxml;
    opens com.example.hescoin.Model to javafx.base;
}