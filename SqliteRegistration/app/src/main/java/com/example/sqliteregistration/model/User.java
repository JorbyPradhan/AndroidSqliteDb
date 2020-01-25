package com.example.sqliteregistration.model;

public class User {
    private String name;
    private String age;
    private String dob;
    private String pass;
    private String email;
    private int phone;

    public User() {
    }
    public User(String name, String dob,String age) {
        this.name = name;
        this.age = age;
        this.dob = dob;
    }

    public User(String name, String pass, String email, int phone) {
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.phone = phone;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
