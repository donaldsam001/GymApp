package com.example.demopro1.Models;

public class Admin {

    private int id;
    private String name;
    private String password;
    private String email;
    public Admin() {}

    public Admin(int id, String name,String password, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.name = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
