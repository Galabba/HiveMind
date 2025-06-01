package com.example.blacklionclient;

public class Ticket {
    public String nome, descr, status, depart;
    private final int idTicket;

    public Ticket(String nome, String descr, String status, String depart, int idTicket) {
        this.nome = nome;
        this.descr = descr;
        this.status = status;
        this.depart = depart;
        this.idTicket = idTicket;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public int getIdTicket() {
        return idTicket;
    }
}
