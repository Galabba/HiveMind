package com.example.blacklionclient;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class HelloController {
    @FXML
    private Pane home, login;
    private Button log_out;

    @FXML
    protected void onSignIn() {
        home.setVisible(true);
        login.setVisible(false);

    }
    @FXML
    protected void onLogOut(){
        home.setVisible(false);
        login.setVisible(true);
    }
}