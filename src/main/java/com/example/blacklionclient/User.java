package com.example.blacklionclient;

public class User {
    private String username, passw, name, surname, depart;
    private Boolean login = false;

    public User(String username, String passw, String name, String surname, String depart) {
        this.username = username;
        this.passw = passw;
        this.name = name;
        this.surname = surname;
        this.depart = depart;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassw() {
        return passw;
    }
    public void setPassw(String passw) {
        this.passw = passw;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getDepart() {
        return depart;
    }
    public void setDepart(String depart) {
        this.depart = depart;
    }
    public Boolean getLogin() {
        return login;
    }
    public void setLogin(Boolean login) {
        this.login = login;
    }
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", passw='" + passw + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", depart='" + depart + '\'' +
                '}';
    }
}
