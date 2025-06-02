package com.example.blacklionclient;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.sql.*;
import java.util.ArrayList;

public class HelloController {
    @FXML
    private Pane home, login;
    @FXML
    private GridPane table;
    @FXML
    private TextField User, Pass;
    @FXML
    private Text npag;

    public ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
    public Dipendente curr_user;
    public Manager curr_admin;
    private static Statement statement;     //Classe per l'invio delle query
    private static ResultSet resultSet;     //Classe per l'output delle query
    private static Connection connection;
    private int curr_pag=1, page_max;

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
               loadTicketPane();
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
    @FXML
    protected void onNextPage(){
        if(curr_pag < page_max){
            loadPageTicketPane(curr_pag+1);
        }else{}
    }
    @FXML
    protected void onPrevPage(){
        if(curr_pag > 1){
            System.out.println(curr_pag);
            loadPageTicketPane(curr_pag-1);
        }else{}
    }

    private void loadTicketPane() throws SQLException {
        statement = connection.createStatement();
        statement.executeQuery("SELECT * FROM ticket WHERE Dipartimento=\""+curr_user.getDepart()+"\"");
        resultSet=statement.getResultSet();
        while(resultSet.next()){
            ticketList.add(new Ticket(resultSet.getString("Nome"), resultSet.getString("Descrizione"),
                    resultSet.getString("Status"), resultSet.getString("Dipartimento"), resultSet.getInt("idTicket")));
        }
        page_max =(int) Math.ceil((double)ticketList.size()/5);
        loadPageTicketPane(1);
    }
    private void loadPageTicketPane(int n_page){
        curr_pag=n_page;
        int i=(5*(n_page-1));
        for (Node node : table.getChildren()) {
            if (node instanceof Button){
                    ((Button) node).setVisible(false);
                    ((TextArea) getNodeByRowColumnIndex(GridPane.getRowIndex(node), 1,table)).setVisible(false);
                    ((ImageView) getNodeByRowColumnIndex(GridPane.getRowIndex(node), 2,table)).setVisible(false);
            }
        }
        for (Node node : table.getChildren()) {
            if (node instanceof Button){
                if(i<ticketList.size() && ticketList.get(i)!=null){
                    ((Button) node).setVisible(true);
                    ((Button) node).setText(ticketList.get(i).getNome());
                    ((TextArea) getNodeByRowColumnIndex(GridPane.getRowIndex(node), 1,table)).setVisible(true);
                    ((TextArea) getNodeByRowColumnIndex(GridPane.getRowIndex(node), 1,table)).setText(ticketList.get(i).getDescr());
                    ((ImageView) getNodeByRowColumnIndex(GridPane.getRowIndex(node), 2,table)).setVisible(true);
                    ((ImageView) getNodeByRowColumnIndex(GridPane.getRowIndex(node), 2,table)).setImage(new Image(getClass().getResourceAsStream("/img/"+ticketList.get(i).getStatus()+".png")));
                    i++;
                }
                else{
                    break;
                }
            }
        }
        if (ticketList.size()>5){
            npag.setText(curr_pag+"/"+ page_max);
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
    public Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        ObservableList<Node> children = gridPane.getChildren();
        for (Node node : children) {
            Integer rowIndex = GridPane.getRowIndex(node);
            Integer colIndex = GridPane.getColumnIndex(node);

            int currentRow = (rowIndex != null) ? rowIndex : 0;
            int currentCol = (colIndex != null) ? colIndex : 0;

            if (currentRow == row && currentCol == column) {
                return node;
            }
        }
        return null;
    }
}