module com.example.blacklionclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.blacklionclient to javafx.fxml;
    exports com.example.blacklionclient;
}