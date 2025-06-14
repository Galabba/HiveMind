package com.example.blacklionclient.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;

public class CreateTicketController {

    @FXML
    Text user1;
    @FXML
    Button logOutButton;
    @FXML
    TextField departField, nameField;
    @FXML
    TextArea descrField;
    @FXML
    Pane nameAlert1, nameAlert2, descrAlert;
    public GlobalController gbC;


    public void openTab(){
        user1.setText(gbC.curr_admin.getUsername());
        departField.setText(gbC.curr_admin.getDepart());
        nameAlert1.setVisible(false);
        nameAlert2.setVisible(false);
        descrAlert.setVisible(false);
    }

    @FXML
    protected void fireLogOut(){
        logOutButton.fire();
    }
    @FXML
    protected void onLogOut(ActionEvent event) throws IOException {
        LogInController loginC = (LogInController) gbC.changeScene("Log_in.fxml", event);
    }

    public void onReturnPressed(ActionEvent actionEvent) throws IOException {
        ManagerPageController managerC =(ManagerPageController) gbC.changeScene("manager.fxml", actionEvent);
        managerC.gbC=this.gbC;
        managerC.openTab();
    }

    public void onCreatePressed(ActionEvent actionEvent) {
        if (descrField.getText().length()>=255){
            descrAlert.setVisible(true);
            System.out.println("ciao");
        } else if (nameField.getText().length()>=100) {
            nameAlert1.setVisible(true);
        } else if (nameField.getText().length() == 0) {
            nameAlert2.setVisible(true);
            System.out.println("ciao3");
        }
        else{
            System.out.println("ciao2");
        }
    }
}
