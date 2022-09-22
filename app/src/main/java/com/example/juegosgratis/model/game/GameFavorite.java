package com.example.juegosgratis.model.game;

public class GameFavorite {
    public GameFavorite(String imagen, String nombre, int id) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String imagen;
    private String nombre;
    private int id;
}
