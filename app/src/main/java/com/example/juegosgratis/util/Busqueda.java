package com.example.juegosgratis.util;

import com.example.juegosgratis.model.game.GameFavorite;

import java.util.List;

public class Busqueda {

    public static int busquedaLineal(List<GameFavorite> lista, int id){
        int i = 0;
        int longitud = lista.size();
        while (i < longitud && (lista.get(i).getId() != id)) {
            i++;
        }
        if(i < lista.size()){
            return i;
        }else{
            return -1;
        }
    }
}
