package com.example.blacklionclient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TicketPageController {
    @FXML
    public TextArea descrField;
    @FXML
    public TextField depField;
    @FXML
    public Pane ticketPageRoot;
    @FXML
    public Button tickName, start, stop, finish;

    public Ticket tickSelected;
    boolean isVisible=false;

    @FXML
    protected void open_close_Tab(){
        if(isVisible){
            ticketPageRoot.setVisible(false);
            isVisible=false;
        }
        else{
            ticketPageRoot.setVisible(true);
            isVisible=true;
            tickName.setText(tickSelected.nome);
            depField.setText(tickSelected.depart);
            descrField.setText(tickSelected.descr);
        }
    }
    @FXML
    protected void onReturnPressed(ActionEvent event) throws IOException {
        changeScene("Log_in", event);
    }
    @FXML
    protected void onLogOut(){
    }
    public Object changeScene(String url, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(loader.load(), 1280, 960);
        stage.setScene(scene);
        stage.show();
        return loader.getController();
    }
}
