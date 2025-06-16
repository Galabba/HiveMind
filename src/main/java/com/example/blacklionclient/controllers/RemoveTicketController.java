package com.example.blacklionclient.controllers;

import com.example.blacklionclient.Ticket;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;


public class RemoveTicketController {
    @FXML
    Text user, npag, confirmation;
    @FXML
    GridPane table;
    @FXML
    Button logOutButton, buttonSelected;
    @FXML
    Pane confirmationAlert;

    private Statement statement;
    private static Connection connection;
    private ResultSet resultSet;
    public GlobalController gbC;
    private int curr_pag=1, page_max;

    public void openTab() throws SQLException {
        user.setText(gbC.curr_admin.getUsername());
        confirmationAlert.setVisible(false);
        confirmation.setVisible(false);
        gbC.ticketList = new ArrayList<>();
        loadTicketPane();
    }

    @FXML
    protected void fireLogOut(){
        logOutButton.fire();
    }
    @FXML
    protected void onLogOut(ActionEvent event) throws IOException {
        LogInController loginC = (LogInController) gbC.changeScene("Log_in.fxml", event);
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
            loadPageTicketPane(curr_pag+1);
        }
    }
    @FXML
    protected void onPrevPage(){
        if(curr_pag > 1){
            loadPageTicketPane(curr_pag-1);
        }
    }
    @FXML
    public void onTicketSelected(ActionEvent actionEvent) throws SQLException {
        if(confirmationAlert.isVisible()){
            Ticket tickSelected=null;
            for (int i = 0; i < 5; i++) {
                if (Integer.parseInt(buttonSelected.getId()) == i) {
                    tickSelected = gbC.ticketList.get(i + (5 * (curr_pag - 1)));
                }
            }
            PreparedStatement stmn = connection.prepareStatement("DELETE FROM ticket WHERE idTicket = ?;");
            stmn.setInt(1, tickSelected.getIdTicket());
            stmn.execute();
            openTab();
            confirmation.setVisible(true);
        }
        else{
            buttonSelected = (Button) actionEvent.getSource();
            confirmationAlert.setVisible(true);
        }
    }

    private void loadTicketPane() throws SQLException {
        statement = connection.createStatement();
        statement.executeQuery("SELECT * FROM ticket WHERE Dipartimento=\""+ gbC.curr_admin.getDepart()+"\"");
        resultSet=statement.getResultSet();
        while(resultSet.next()){
            gbC.ticketList.add(new Ticket(resultSet.getString("Nome"), resultSet.getString("Descrizione"),
                    resultSet.getString("Status"), resultSet.getString("Dipartimento"), resultSet.getInt("idTicket")));
        }
        page_max =(int) Math.ceil((double)gbC.ticketList.size()/5);
        loadPageTicketPane(1);
    }
    private void loadPageTicketPane(int n_page){
        curr_pag=n_page;
        int i=(5*(n_page-1));
        for (Node node : table.getChildren()) {
            if (node instanceof Button){
                node.setVisible(false);
                (getNodeByRowColumnIndex(GridPane.getRowIndex(node), 1,table)).setVisible(false);
                (getNodeByRowColumnIndex(GridPane.getRowIndex(node), 2,table)).setVisible(false);
            }
        }
        for (Node node : table.getChildren()) {
            if (node instanceof Button){
                if(i<gbC.ticketList.size() && gbC.ticketList.get(i)!=null){
                    (node).setVisible(true);
                    ((Button) node).setText(gbC.ticketList.get(i).getNome());
                    (getNodeByRowColumnIndex(GridPane.getRowIndex(node), 1,table)).setVisible(true);
                    ((TextArea) getNodeByRowColumnIndex(GridPane.getRowIndex(node), 1,table)).setText(gbC.ticketList.get(i).getDescr());
                    (getNodeByRowColumnIndex(GridPane.getRowIndex(node), 2,table)).setVisible(true);
                    ((ImageView) getNodeByRowColumnIndex(GridPane.getRowIndex(node), 2,table)).setImage(new Image(getClass().getResourceAsStream("/img/"+gbC.ticketList.get(i).getStatus()+".png")));
                    i++;
                }
                else{
                    break;
                }
            }
        }
        if (gbC.ticketList.size()>5){
            npag.setText(curr_pag+"/"+ page_max);
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


}
