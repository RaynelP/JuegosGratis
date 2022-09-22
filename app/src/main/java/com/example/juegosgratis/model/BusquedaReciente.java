package com.example.juegosgratis.model;

public class BusquedaReciente {
    public BusquedaReciente(int id, String busqueda) {
        this.id = id;
        this.busqueda = busqueda;
    }

    public int getId() {
        return id;
    }

    public String getBusqueda() {
        return busqueda;
    }

    private int id;
    private String busqueda;
}
