package com.example.golf_malaga.AllCampsGolf;

public class CampsGolfList_Model {
    String camps, towns;
    int image,category;



    public CampsGolfList_Model(String camps, String towns, int image, int category) {
        this.camps = camps;
        this.towns = towns;
        this.image = image;
        this.category=category;

    }

    public String getCamps() {
        return camps;
    }

    public void setCamps(String camps) {
        this.camps = camps;
    }

    public String getTowns() {
        return towns;
    }

    public void setTowns(String towns) {
        this.towns = towns;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
