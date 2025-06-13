package com.example.blacklionclient.controllers;

import com.example.blacklionclient.Dipendente;
import com.example.blacklionclient.Manager;
import com.example.blacklionclient.Ticket;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class GlobalController {
    public Dipendente curr_user;
    public Manager curr_admin;
    public ArrayList<Ticket> ticketList = new ArrayList<>();

    public GlobalController(Dipendente curr_user){
        this.curr_user=curr_user;
    }
    public GlobalController(Manager curr_admin){
        this.curr_admin=curr_admin;
    }

    public Object changeScene(String url, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/blacklionclient/"+url));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(loader.load(), 1280, 960);
        stage.setScene(scene);
        stage.show();
        return loader.getController();
    }
}
