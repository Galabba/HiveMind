package com.example.blacklionclient.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class ManagerPageController {
    @FXML
    private TextField departField;
    @FXML
    private Text user1;
    @FXML
    private Button logOutButton;

    public GlobalController gbC;

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
        LogInPageController loginC = (LogInPageController) gbC.changeScene("Log_in.fxml", event);
    }

    public void onCreateTicketPressed(ActionEvent actionEvent) throws IOException {
        CreateTicketPageController createticketC =(CreateTicketPageController) gbC.changeScene("CreateTicket.fxml", actionEvent);
        createticketC.gbC=this.gbC;
        createticketC.openTab();
    }
    public void onRemoveTicketPressed(ActionEvent actionEvent) throws IOException, SQLException {
        RemoveTicketPageController removeTicketC = (RemoveTicketPageController) gbC.changeScene("RemoveTicket.fxml", actionEvent);
        removeTicketC.gbC = this.gbC;
        removeTicketC.openTab();
    }
    public void onStatisticsPressed(ActionEvent actionEvent) throws SQLException, IOException {
        StatisticsPageController statisticsC = (StatisticsPageController) gbC.changeScene("Statistics.fxml", actionEvent);
        statisticsC.gbC = this.gbC;
        statisticsC.openTab();
    }
}
