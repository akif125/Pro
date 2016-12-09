package com.example.akif_.pro;

/**
 * Created by akif_ on 12/5/2016.
 */
public class contacts {




    private String name;
    private String email;
    private String password;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public contacts(String name, String email, String password) {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}

