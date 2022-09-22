package com.example.juegosgratis.model.game;

public class ScreenShot {

    public ScreenShot() {
    }

    public ScreenShot(int id, String image) {
        this.id = id;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    private int id;
    private String image;
}
