package com.example.recyclerviewhardwareroom;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity
public class Hardware{
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name, description, type;
    private float price;
    private int img;
    private Date manufacturedAt;

    public Hardware(String name, String description, String type, float price, int img, Date manufacturedAt) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
        this.img = img;
        this.manufacturedAt = manufacturedAt;
    }

    public Hardware() {
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public Date getManufacturedAt() {
        return manufacturedAt;
    }

    public void setManufacturedAt(Date manufacturedAt) {
        this.manufacturedAt = manufacturedAt;
    }



}
