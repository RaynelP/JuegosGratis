package com.example.juegosgratis.repository.network.Interfaces;

import com.example.juegosgratis.model.BusquedaReciente;
import com.example.juegosgratis.model.game.GameFavorite;

import java.util.ArrayList;

public interface IFavoriteRepository {

    public void addGame(GameFavorite j);

    public void agregarJuegoAVistosRecientemente(GameFavorite j);

    public void deleteGame(int idJuego);

    public void eliminarJuegoDeVistosRecienteMente(int idJuego);

    public ArrayList<GameFavorite> getAllGames();

    public ArrayList<GameFavorite> obtenerJuegosVistosRecientemente();

    public void agregarBusquedaReciente(String busqueda);

    public void eliminarBusquedaReciente(BusquedaReciente busqueda);

    public ArrayList<BusquedaReciente> obtenerBusquedasRecientes();

}
