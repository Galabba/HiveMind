module com.example.blacklionclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.blacklionclient to javafx.fxml;
    exports com.example.blacklionclient;
    exports com.example.blacklionclient.controllers;
    opens com.example.blacklionclient.controllers to javafx.fxml;
}