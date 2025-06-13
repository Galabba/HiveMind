package com.example.blacklionclient.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ManagerPageController {
    @FXML
    private TextField departField;
    @FXML
    private Text user1;
    @FXML
    private Button logOutButton;

    public GlobalController gbC;
    private LogInController loginC;
    private static PreparedStatement statement;     //Classe per l'invio delle query
    private static Connection connection;

    public void openTab(){
        user1.setText(gbC.curr_admin.getUsername());
        departField.setText(gbC.curr_admin.getDepart());
    }

    @FXML
    protected void fireLogOut(){
        logOutButton.fire();
    }
    @FXML
    protected void onLogOut(ActionEvent event) throws IOException {
        loginC=(LogInController) gbC.changeScene("Log_in.fxml", event);
    }

}
