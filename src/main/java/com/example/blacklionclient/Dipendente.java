package com.example.blacklionclient;

public class Dipendente extends User {
    private String city;
    private int nTicketRis;

    public Ticket ticket;

    public Dipendente(String username, String passw, String name, String surname, String depart, String city, int nTicketRis) {
        super(username, passw, name, surname, depart);
        this.city=city;
        this.nTicketRis=nTicketRis;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getnTicketRis() {
        return nTicketRis;
    }

    public void setnTicketRis(int nTicketRis) {
        this.nTicketRis = nTicketRis;
    }
}
