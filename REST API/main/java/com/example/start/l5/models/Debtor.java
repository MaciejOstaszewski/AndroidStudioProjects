package com.example.start.l5.models;


import android.media.Image;

/**
 * Created by start on 2017-11-10.
 */

public class Debtor {
    private int id;
    private String phoneNumber;
    private String name;
    private String photo;

    public Debtor() {
    }

    public Debtor(String phoneNumber, String name, String photo) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.photo = photo;
    }

    public Debtor(int id, String name, String phoneNumber, String photo) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


}
