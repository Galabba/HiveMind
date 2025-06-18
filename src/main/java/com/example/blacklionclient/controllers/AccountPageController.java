package com.example.blacklionclient.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountPageController {
    @FXML
    public Button lastResTick, lastStartTick;
    @FXML
    Button logOutButton;
    @FXML
    Pane managerPage, userPage;
    @FXML
    Text user1;
    @FXML
    TextField usernameM, usernameD, nameM, nameD, surnameM, surnameD, departmentM, departmentD, cityD, nTickResD, nTickResDepart;

    public GlobalController gbC;
    private static Connection connection;
    private Statement statement;

    public void openTab() throws SQLException {
        if(gbC.curr_user!=null){
            user1.setText(gbC.curr_user.getUsername());
            managerPage.setVisible(false);
            userPage.setVisible(true);
            usernameD.setText(gbC.curr_user.getUsername());
            nameD.setText(gbC.curr_user.getName());
            surnameD.setText(gbC.curr_user.getSurname());
            departmentD.setText(gbC.curr_user.getDepart());
            cityD.setText(gbC.curr_user.getCity());
            nTickResD.setText(""+gbC.curr_user.getnTicketRis());
            if(gbC.curr_user.lastResolvedTicket!=null){
                lastResTick.setText(gbC.curr_user.lastResolvedTicket.getNome());
                lastResTick.setOpacity(1);
            }
            else{
                lastResTick.setText("None");
                lastResTick.setOpacity(0.30);
            }
            if(gbC.curr_user.lastStartedTicket!=null){
                lastStartTick.setText(gbC.curr_user.lastStartedTicket.getNome());
                lastStartTick.setOpacity(1);
            }
            else{
                lastStartTick.setText("None");
                lastStartTick.setOpacity(0.30);
            }
        }
        else if(gbC.curr_admin!=null){
            user1.setText(gbC.curr_admin.getUsername());
            managerPage.setVisible(true);
            userPage.setVisible(false);
            usernameM.setText(gbC.curr_admin.getUsername());
            nameM.setText(gbC.curr_admin.getName());
            surnameM.setText(gbC.curr_admin.getSurname());
            departmentM.setText(gbC.curr_admin.getDepart());
            statement = connection.createStatement();
            statement.executeQuery("SELECT SUM(nTicketRis) as num FROM dipendente WHERE Dipartimento = '"+gbC.curr_admin.getDepart()+"'");
            statement.getResultSet().next();
            nTickResDepart.setText(""+(Integer) statement.getResultSet().getInt("num"));
        }
    }

    @FXML
    protected void fireLogOut(){
        logOutButton.fire();
    }
    @FXML
    protected void onLogOut(ActionEvent event) throws IOException {
        LogInPageController loginC = (LogInPageController) gbC.changeScene("Log_in.fxml", event);
    }
    @FXML
    public void onReturnPressed(ActionEvent actionEvent) throws IOException, SQLException {
        if(managerPage.isVisible()){
            ManagerPageController managerC = (ManagerPageController) gbC.changeScene("manager.fxml", actionEvent);
            managerC.gbC=this.gbC;
            managerC.openTab();
        }
        else if(userPage.isVisible()){
            LogInPageController logInC = (LogInPageController) gbC.changeScene("Log_in.fxml", actionEvent);
            logInC.gbC=this.gbC;
            logInC.home.setVisible(true);
            logInC.login.setVisible(false);
            logInC.loadTicketPane();
        }
    }


    public static void DB_Connection(){
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
