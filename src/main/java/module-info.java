module com.example.blacklionclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.blacklionclient to javafx.fxml;
    exports com.example.blacklionclient;
}