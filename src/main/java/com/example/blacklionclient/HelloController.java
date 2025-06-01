package com.example.blacklionclient;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import java.sql.*;

public class HelloController {
    @FXML
    private Pane home, login;
    @FXML
    private GridPane table;
    @FXML
    private TextField User, Pass;
    public Dipendente curr_user;
    public Manager curr_admin;
    private static Statement statement;     //Classe per l'invio delle query
    private static ResultSet resultSet;     //Classe per l'output delle query
    private static Connection connection;

    //Controllo Credenziali e Login
    @FXML
    protected void onLogIn() {
        try {
           statement = connection.createStatement();
           //controlla se l'user è un dipendente
           statement.executeQuery("SELECT * FROM dipendente");
           resultSet = statement.getResultSet();
           while (resultSet.next()){
               if(resultSet.getString("User").equals(User.getText()) && resultSet.getString("Password").equals(Pass.getText())){
                   home.setVisible(true);
                   login.setVisible(false);
                   curr_user = new Dipendente(resultSet.getString("User"), resultSet.getString("Password"),
                           resultSet.getString("Nome"), resultSet.getString("Cognome"),
                           resultSet.getString("Dipartimento"), resultSet.getString("Sede"), resultSet.getInt("nTicketRis"));
                   curr_user.setLogin(true);
               }
           }
           //controlla se l'user è un manager
           statement.executeQuery("SELECT * FROM manager");
           resultSet = statement.getResultSet();
           while (resultSet.next()){
               if(resultSet.getString("User").equals(User.getText()) && resultSet.getString("Password").equals(Pass.getText())){
                   home.setVisible(true);
                   login.setVisible(false);
                   curr_admin = new Manager(resultSet.getString("User"), resultSet.getString("Password"),
                           resultSet.getString("Nome"), resultSet.getString("Cognome"),
                           resultSet.getString("Dipartimento"));
                   curr_admin.setLogin(true);
               }
           }
        } catch (SQLException e) {
           throw new RuntimeException(e);
       }
       if (home.isVisible()){
           try {
               fillTicketPane();
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }
       }


    }

    //cambio scena Home-->Login
    @FXML
    protected void onLogOut(){
        home.setVisible(false);
        login.setVisible(true);
    }

    private void fillTicketPane() throws SQLException {
        statement = connection.createStatement();
        statement.executeQuery("SELECT * FROM ticket WHERE Dipartimento=\""+curr_user.getDepart()+"\"");
        resultSet=statement.getResultSet();
        for (Node node : table.getChildren()) {
            if (node instanceof Button){
                if(resultSet.next()){
                    ((TextArea) node).setText(resultSet.getString("Nome"));
                }
                else{
                    break;
                }
            }
            else if (node instanceof TextArea){
                ((TextArea) node).setText(resultSet.getString("Descrizione"));
            }
            else if (node instanceof ImageView){
            //    ((ImageView) node).setImage();
            }
        }

    }

    //metodo per la connessione al DB
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
            System.out.println(e);
        }
    }


}