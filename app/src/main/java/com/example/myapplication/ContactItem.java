package com.example.myapplication;

import java.io.Serializable;

public class ContactItem implements Serializable {
    private String phone_number;
    private String name;
    private int photo_id = 0;
    private int person_id = 0;
    private int id;

    public ContactItem(String phone_number, String name, int photo_id) {
        this.phone_number = phone_number;
        this.name = name;
        this.photo_id = photo_id;
        this.person_id = 0;
        this.id = 0;
    }

    public String getPhone_number() {
        return this.phone_number;
    }
    public String getName() {
        return this.name;
    }
    public int getPhoto_id() {
        return this.photo_id;
    }
    public int getPerson_id() { return this.person_id; }
    public int getId() {
        return this.id;
    }

    public void setPhone_number(String string) { this.phone_number = string; }
    public void setName(String string) {
        this.name = string;
    }
    public void setPhoto_id(int id) {
        this.photo_id = id;
    }
    public void setPerson_id(int id) {
        this.person_id = id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.phone_number;
    }

    @Override
    public int hashCode() {
        return getPhone_numberChanged().hashCode();
    }
    public String getPhone_numberChanged() {
        return phone_number.replace("-", "");
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ContactItem)
            return getPhone_number().equals(((ContactItem) o).getPhone_numberChanged());
        else return false;
    }
}