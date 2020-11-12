package com.example.test.model;

public class User {
    private String email;
    private String password;
    private int id;

    public User(String email, String password,int id) {
        this.email = email;
        this.password = password;
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPassword() {
        return password;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }


}