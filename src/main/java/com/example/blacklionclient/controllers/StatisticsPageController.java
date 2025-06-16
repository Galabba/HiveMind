package com.example.blacklionclient.controllers;

import com.example.blacklionclient.Dipendente;
import com.example.blacklionclient.Ticket;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class StatisticsPageController {
    @FXML
    Text user, npag;
    @FXML
    Button logOutButton;
    @FXML
    GridPane table;

    private Statement statement;
    private static Connection connection;
    private  ResultSet resultSet;
    public GlobalController gbC;
    private int curr_pag=1, page_max;

    public void openTab() throws SQLException {
        user.setText(gbC.curr_admin.getUsername());
        orderMaxToMin();
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
    public void onReturnPressed(ActionEvent actionEvent) throws IOException {
        ManagerPageController managerC =(ManagerPageController) gbC.changeScene("manager.fxml", actionEvent);
        managerC.gbC=this.gbC;
        managerC.openTab();
    }
    @FXML
    protected void onNextPage(){
        if(curr_pag < page_max){
            loadTable(curr_pag+1);
        }
    }
    @FXML
    protected void onPrevPage(){
        if(curr_pag > 1){
            loadTable(curr_pag-1);
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

    public void orderMaxToMin() throws SQLException {
        gbC.userList=new ArrayList<>();
        statement = connection.createStatement();
        statement.executeQuery("SELECT * FROM dipendente WHERE dipartimento = '"+gbC.curr_admin.getDepart()+"' ORDER BY nTicketRis DESC;");
        resultSet = statement.getResultSet();
        while(resultSet.next()){
            gbC.userList.add(new Dipendente(resultSet.getString("User"),resultSet.getString("Password"),resultSet.getString("Nome"),
                    resultSet.getString("Cognome"),resultSet.getString("Dipartimento"),resultSet.getString("Sede"),
                    resultSet.getInt("nTicketRis")));
        }
        page_max =(int) Math.ceil((double)gbC.userList.size()/5);
        loadTable(1);
    }
    public void orderMinToMax() throws SQLException {
        gbC.userList=new ArrayList<>();
        statement = connection.createStatement();
        statement.executeQuery("SELECT * FROM dipendente WHERE dipartimento = '"+gbC.curr_admin.getDepart()+"' ORDER BY nTicketRis ASC;");
        resultSet = statement.getResultSet();
        while(resultSet.next()){
            gbC.userList.add(new Dipendente(resultSet.getString("User"),resultSet.getString("Password"),resultSet.getString("Nome"),
                    resultSet.getString("Cognome"),resultSet.getString("Dipartimento"),resultSet.getString("Sede"),
                    resultSet.getInt("nTicketRis")));
        }
        page_max =(int) Math.ceil((double)gbC.userList.size()/5);
        loadTable(1);
    }
    private void loadTable(int page){
        curr_pag=page;
        int i=(5*(page-1));
        for (Node node : table.getChildren()) {
            if (node instanceof TextField){
                node.setVisible(false);
                (getNodeByRowColumnIndex(GridPane.getRowIndex(node), 1,table)).setVisible(false);
                (getNodeByRowColumnIndex(GridPane.getRowIndex(node), 2,table)).setVisible(false);
            }
        }
        for (Node node : table.getChildren()) {
            if (node instanceof TextField){
                if(i<gbC.userList.size() && gbC.userList.get(i)!=null && node.getId()!=null){
                    ((TextField) node).setText(gbC.userList.get(i).getUsername());
                    node.setVisible(true);
                    ((TextField) getNodeByRowColumnIndex(GridPane.getRowIndex(node), 1,table)).setText(gbC.userList.get(i).getName());
                    (getNodeByRowColumnIndex(GridPane.getRowIndex(node), 1,table)).setVisible(true);
                    ((TextField) getNodeByRowColumnIndex(GridPane.getRowIndex(node), 2,table)).setText(gbC.userList.get(i).getSurname());
                    (getNodeByRowColumnIndex(GridPane.getRowIndex(node), 2,table)).setVisible(true);
                    ((TextField) getNodeByRowColumnIndex(GridPane.getRowIndex(node), 3,table)).setText("N°: "+gbC.userList.get(i).getnTicketRis());
                    (getNodeByRowColumnIndex(GridPane.getRowIndex(node), 3,table)).setVisible(true);
                    i++;
                }
            }
        }
        if (gbC.userList.size()>5){
            npag.setText(curr_pag+"/"+ page_max);
        }
    }
}
