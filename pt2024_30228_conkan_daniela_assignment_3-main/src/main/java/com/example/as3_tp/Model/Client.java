package com.example.as3_tp.Model;

/**
 * used to represent clients
 */
public class Client {
    private int id;
    private String name;
    private String mail;
    private String address;
    private int age;

    public Client(int id, String name, String mail, String address, int age) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.address = address;
        this.age = age;
    }

    public Client() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
