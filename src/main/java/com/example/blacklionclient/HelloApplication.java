package com.example.blacklionclient;

import com.example.blacklionclient.controllers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Log_In.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 960);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        LogInPageController.DB_Connection();    //metodo della classe HelloController per la connessione al DB-MySQL
        TicketPageController.DB_connection();
        CreateTicketPageController.DB_connection();
        RemoveTicketPageController.DB_Connection();
        StatisticsPageController.DB_Connection();
        launch();
    }
}