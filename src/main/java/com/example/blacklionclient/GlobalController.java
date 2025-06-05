package com.example.blacklionclient;

import java.util.ArrayList;

public class GlobalController {
    public Dipendente curr_user;
    public Manager curr_admin;
    public ArrayList<Ticket> ticketList = new ArrayList<Ticket>();

    public GlobalController(Dipendente curr_user){
        this.curr_user=curr_user;
    }
    public GlobalController(Manager curr_admin){
        this.curr_admin=curr_admin;
    }
}
