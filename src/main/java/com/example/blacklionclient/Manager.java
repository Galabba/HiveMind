package com.example.blacklionclient;

public class Manager extends User{

    private Ticket ticketSelected;

    public Manager(String username, String passw, String name, String surname, String depart) {
        super(username, passw, name, surname, depart);
    }

    public Ticket getTicketSelected() {
        return ticketSelected;
    }

    public void setTicketSelected(Ticket ticketSelected) {
        this.ticketSelected = ticketSelected;
    }
}
