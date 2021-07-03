package com.sqlite;

public class User {
    int id;
    String name;
    String surname;

    public User(String name, String surname) {
        this.name=name;
        this.surname=surname;
    }

    public User(){}

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


}
