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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class TicketPageController {
    @FXML
    public TextArea descrField;
    @FXML
    public TextField depField;
    @FXML
    public Button tickName, start, stop, finish, logOutButton;
    @FXML
    private Text user;

    public GlobalController gbC;
    private LogInController loginC;
    private static PreparedStatement statement;     //Classe per l'invio delle query
    private static ResultSet resultSet;     //Classe per l'output delle query
    private static Connection connection;

    @FXML
    protected void openTab(){
        tickName.setText(gbC.curr_user.ticket.nome);
        depField.setText(gbC.curr_user.ticket.depart);
        descrField.setText(gbC.curr_user.ticket.descr);
        user.setText(gbC.curr_user.getUsername());
        start.setOpacity(1);
        start.setDisable(false);
        finish.setOpacity(1);
        finish.setDisable(false);
        stop.setOpacity(1);
        stop.setDisable(false);
        if(gbC.curr_user.ticket.getStatus().equals("start")){
            finish.setOpacity(0.3);
            finish.setDisable(true);
            stop.setOpacity(0.3);
            stop.setDisable(true);
        }
        else if (gbC.curr_user.ticket.getStatus().equals("progress")) {
            start.setOpacity(0.3);
            start.setDisable(true);
        }
        else if (gbC.curr_user.ticket.getStatus().equals("stop")) {
            stop.setOpacity(0.3);
            stop.setDisable(true);
            finish.setOpacity(0.3);
            finish.setDisable(true);
        }
    }
    @FXML
    protected void onReturnPressed(ActionEvent event) throws IOException, SQLException {
        loginC=(LogInController) changeScene("Log_in.fxml", event);
        loginC.gbC = this.gbC;
        loginC.home.setVisible(true);
        loginC.login.setVisible(false);
        loginC.loadTicketPane();
    }

    @FXML
    protected void fireLogOut(){
        logOutButton.fire();
    }
    @FXML
    protected void onLogOut(ActionEvent event) throws IOException {
        loginC=(LogInController) changeScene("Log_in.fxml", event);
    }

    public Object changeScene(String url, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(loader.load(), 1280, 960);
        stage.setScene(scene);
        stage.show();
        return loader.getController();
    }

    public void onStartPressed(ActionEvent event) throws SQLException {
        statement = connection.prepareStatement("UPDATE ticket\n" +
                "SET status = 'progress'\n" +
                "WHERE idTicket= ?;");
        statement.setInt(1, gbC.curr_user.ticket.getIdTicket());
        statement.executeUpdate();
        gbC.curr_user.ticket.setStatus("progress");
        openTab();
    }
    public void onStopPressed(ActionEvent event)  throws SQLException {
        statement = connection.prepareStatement("UPDATE ticket\n" +
                "SET status = 'stop'\n" +
                "WHERE idTicket= ?;");
        statement.setInt(1, gbC.curr_user.ticket.getIdTicket());
        statement.executeUpdate();
        gbC.curr_user.ticket.setStatus("stop");
        openTab();
    }
    public void onFinishPressed(ActionEvent event)  throws SQLException {
        statement = connection.prepareStatement("UPDATE ticket\n" +
                "SET status = 'finish'\n" +
                "WHERE idTicket= ?;");
        statement.setInt(1, gbC.curr_user.ticket.getIdTicket());
        statement.executeUpdate();
        gbC.curr_user.ticket.setStatus("finish");
        openTab();
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
            System.out.println(e);
        }
    }
}
