package com.example.unitume;

public class User {

    private String Name;
    private String Password;
    private String Email;
    private String Token;

    public User() {
    }

    public User(String name, String password, String email, String token) {
        Name = name;
        Password = password;
        Email = email;
        Token = token;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public String getEmail() {
        return Email;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        this.Token = token;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
