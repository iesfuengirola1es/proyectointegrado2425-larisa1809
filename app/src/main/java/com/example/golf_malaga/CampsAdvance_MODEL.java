package com.example.golf_malaga;

public class CampsAdvance_MODEL {
    int image;
    String name, town;

    public CampsAdvance_MODEL(int image, String name, String town) {
        this.image = image;
        this.name = name;
        this.town = town;
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

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
