package com.example.blacklionclient;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

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


    public TicketPageController(){
    }



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
    protected void onLogOut(){
    }
}
