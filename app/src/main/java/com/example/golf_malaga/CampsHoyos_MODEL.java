package com.example.golf_malaga;

public class CampsHoyos_MODEL {


    int image;
    String name, city;

    public CampsHoyos_MODEL(int image, String name, String city) {
        this.image = image;
        this.name = name;
        this.city = city;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
