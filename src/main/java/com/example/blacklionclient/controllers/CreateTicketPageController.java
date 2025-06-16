package com.example.blacklionclient.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.*;

public class CreateTicketPageController {

    @FXML
    Text user1, confirmation;
    @FXML
    Button logOutButton;
    @FXML
    TextField departField, nameField;
    @FXML
    TextArea descrField;
    @FXML
    Pane nameAlert1, nameAlert2, descrAlert;
    public GlobalController gbC;
    private static PreparedStatement statement;     //Classe per l'invio delle query
    private static Connection connection;


    public void openTab(){
        user1.setText(gbC.curr_admin.getUsername());
        departField.setText(gbC.curr_admin.getDepart());
        nameAlert1.setVisible(false);
        nameAlert2.setVisible(false);
        descrAlert.setVisible(false);
        confirmation.setVisible(false);
    }

    @FXML
    protected void fireLogOut(){
        logOutButton.fire();
    }
    @FXML
    protected void onLogOut(ActionEvent event) throws IOException {
        LogInPageController loginC = (LogInPageController) gbC.changeScene("Log_in.fxml", event);
    }

    public void onReturnPressed(ActionEvent actionEvent) throws IOException {
        ManagerPageController managerC =(ManagerPageController) gbC.changeScene("manager.fxml", actionEvent);
        managerC.gbC=this.gbC;
        managerC.openTab();
    }

    public void onCreatePressed(ActionEvent actionEvent) throws SQLException {
        confirmation.setVisible(false);
        if (descrField.getText().length()>=255){
            descrAlert.setVisible(true);
        } else if (nameField.getText().length()>=100) {
            nameAlert1.setVisible(true);
        } else if (nameField.getText().length() == 0) {
            nameAlert2.setVisible(true);
        }
        else{
                statement = connection.prepareStatement("INSERT INTO ticket (Nome, Descrizione, Status, Dipartimento) VALUES (?, ?, ?, ?)");
                statement.setString(1, nameField.getText());
                statement.setString(2, descrField.getText());
                statement.setString(3, "start");
                statement.setString(4, gbC.curr_admin.getDepart());
                statement.executeUpdate();
                confirmation.setVisible(true);
                nameField.setText("");
                descrField.setText("");
        }
    }

    public static void DB_connection() {
        try {
            // Try per la connettività al DB
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hivemind",
                    "root", "rocchio");

            // mydb nome database
            // mydbuser nome dell utente
            // mydpassword password del DB
        }
        catch (SQLException ex) {
            // controllo errori per la connessione al DB-MySQL
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        catch(Exception e){
            //controllo errori nella connessione per il driver "jdbc" utilizzato dalla libreria "MySQL"
        }
    }
}
