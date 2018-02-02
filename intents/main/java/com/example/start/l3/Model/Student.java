package com.example.start.l3.Model;

/**
 * Created by start on 2017-11-01.
 */

public class Student {
    private String Name;
    private String Phone;

    public Student(String name, String phone) {
        this.Name = name;
        this.Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    @Override
    public String toString() {
        return "Student{" +
                "Name='" + Name + '\'' +
                ", Phone='" + Phone + '\'' +
                '}';
    }
}
