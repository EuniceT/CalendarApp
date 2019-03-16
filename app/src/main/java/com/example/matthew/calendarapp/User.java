package com.example.matthew.calendarapp;

public class User {
    private static int id = 0;
    private String username;
    private String password;

    public User() {
        id++;
        username = getUsername();
        password = getPassword();
    }

    public User(String u, String ps) {
        id++;
        username = u;
        password = ps;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }
}
