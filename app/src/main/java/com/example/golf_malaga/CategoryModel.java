package com.example.golf_malaga;

public class CategoryModel {


    int id;
    String titleTown;

    public CategoryModel(int id, String titleTown) {
        this.id = id;
        this.titleTown = titleTown;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleTown() {
        return titleTown;
    }

    public void setTitleTown(String titleTown) {
        this.titleTown = titleTown;
    }
}
