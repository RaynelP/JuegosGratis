package com.example.juegosgratis.util;

import com.example.juegosgratis.viewModel.MainViewModel;
import com.example.juegosgratis.model.game.Game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Filtros {

    public static List<Game> filtrasTresJuegosAleatorios(List<Game> listaDeJuegos){
        //busco tres juegos de manera aleatoria entre todos los de la lista de juegos
        LinkedList<Integer> indicesAleatorios = new LinkedList<>();
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            indicesAleatorios.add(random.nextInt(listaDeJuegos.size()));
        }
        ArrayList<Game> res = new ArrayList<>();
        //agrego los tres juegos elegidos al azar a la lista de juegos a mostrar
        for (int i: indicesAleatorios) {
            res.add(listaDeJuegos.get(i));
        }
        return res;
    }

    public static List<Game> filtrarPrimerosDiezJuegos(List<Game> listaDeJuegosPopulares){
        ArrayList<Game> res = new ArrayList<>(MainViewModel.CANTIDAD_JUEGOS_POPULARES);
        int longitud = listaDeJuegosPopulares.size();
        for (int i = 0; i < MainViewModel.CANTIDAD_JUEGOS_POPULARES && i < longitud; i++) {
            res.add(i, listaDeJuegosPopulares.get(i));
        }
        return res;
    }

    public static List<Game> filtrarListaDeJuegosPorNombre(List<Game> juegos, String nombre){
        LinkedList<Game> listaDeJuegosFiltrados = new LinkedList<>();
        for (Game j: juegos) {
            if(j.getTitle().toLowerCase().contains(nombre.toLowerCase())){
                listaDeJuegosFiltrados.add(j);
            }
        }
        return new ArrayList<>(listaDeJuegosFiltrados);
    }

}
