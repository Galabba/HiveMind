package com.example.blacklionclient;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.sql.*;

public class HelloController {
    @FXML
    private Pane home, login;
    private Button log_out;
    private static Statement statement;     //Classe per l'invio delle query
    private static ResultSet resultSet;     //Classe per l'output delle query

    @FXML
    protected void onLogIn() {          //cambio scena Login-->Home

     /*  try {
           statement.executeQuery("SELECT User, Password FROM dipendente");
           resultSet = statement.getResultSet();
           while (resultSet.next()){
               if(resultSet.getString("User")=="")

                   home.setVisible(true);
               login.setVisible(false);
           }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }*/
    }
    @FXML
    protected void onLogOut(){          //cambio scena Home-->Login
        home.setVisible(false);
        login.setVisible(true);
    }
    public static void DB_Connection(){
        Connection connection = null;
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
    }          //metodo per la connessione al DB
}