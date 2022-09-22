package com.example.juegosgratis.model;

import android.widget.ImageView;

public class Imagen implements Cloneable{

    public Imagen(ImageView imageView){
        imagen = imageView;
    }
    public Imagen clone() throws CloneNotSupportedException {
        return (Imagen) super.clone();
    }

    public ImageView getImagen() {
        return imagen;
    }

    public void setImagen(ImageView imagen) {
        this.imagen = imagen;
    }

    private ImageView imagen;
}
