package com.example.juegosgratis.repository;

import com.example.juegosgratis.util.Filtros;
import com.example.juegosgratis.repository.network.Interfaces.IGameRepository;
import com.example.juegosgratis.repository.network.Interfaces.IFavoriteRepository;
import com.example.juegosgratis.model.game.Game;
import com.example.juegosgratis.model.game.GameDetail;
import com.example.juegosgratis.model.game.GameFavorite;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ExecuterThread {

    public ExecuterThread(IGameRepository fuenteDeDatos) {
        this.fuenteDeDatosJuegos = fuenteDeDatos;
    }

    public ExecuterThread(IFavoriteRepository fuenteDeDatosFavoritos) {
        this.fuenteDeDatosFavoritos = fuenteDeDatosFavoritos;
    }

    public List<Game> obtenerTodosLosJuegos() throws ExecutionException, InterruptedException {
        ExecutorService ejecutor = Thread.obtenerEjecutorHilo();
        Future<List<Game>> result = ejecutor.submit(new Callable<List<Game>>() {
            @Override
            public List<Game> call() throws Exception {
                return fuenteDeDatosJuegos.getAllGames();
            }
        });
        return result.get();
    }

    public List<Game> obtenerJuegosPorCategoria(String genero) throws ExecutionException, InterruptedException {
        ExecutorService ejecutor = Thread.obtenerEjecutorHilo();
        Future<List<Game>> result = ejecutor.submit(() -> fuenteDeDatosJuegos.getGameByCategory(genero));
        return result.get();
    }

    public List<Game> obtenerJuegosOrdenadosPor(String orden) throws ExecutionException, InterruptedException {
        ExecutorService ejecutor = Thread.obtenerEjecutorHilo();
        Future<List<Game>> result = ejecutor.submit(() -> fuenteDeDatosJuegos.getGamesOrderBy(orden));
        return result.get();
    }

    public List<Game> obtenerJuegosPorNombre(String nombre) throws ExecutionException, InterruptedException {
        ExecutorService ejecutor = Thread.obtenerEjecutorHilo();
        Future<List<Game>> result = ejecutor.submit(() -> {
             List<Game> todosLosJuegos = fuenteDeDatosJuegos.getAllGames();
             return Filtros.filtrarListaDeJuegosPorNombre(todosLosJuegos, nombre);
        });
        return result.get();
    }

    public List<Game> obtenerJuegosPorPlataforma(String platforma) throws ExecutionException, InterruptedException {
        ExecutorService ejecutor = Thread.obtenerEjecutorHilo();
        Future<List<Game>> result = ejecutor.submit(() -> fuenteDeDatosJuegos.getGameByPlatform(platforma));
        return result.get();
    }

    public GameDetail obtenerJuegoConDetalles(Game juego) throws ExecutionException, InterruptedException {
        ExecutorService ejecutor = Thread.obtenerEjecutorHilo();
        Future<GameDetail> result = ejecutor.submit(() -> fuenteDeDatosJuegos.getGameDetail(juego));
        return result.get();
    }

    public GameDetail obtenerJuegoConDetalles(int idGame) throws ExecutionException, InterruptedException {
        ExecutorService ejecutor = Thread.obtenerEjecutorHilo();
        Future<GameDetail> result = ejecutor.submit(() -> fuenteDeDatosJuegos.getGameDetail(idGame));
        return result.get();
    }

    public void agregarJuegoAFavotitos(GameFavorite j){
        ExecutorService ejecutor = Thread.obtenerEjecutorHilo();
        ejecutor.submit(() -> fuenteDeDatosFavoritos.addGame(j));
    }

    public List<GameFavorite> obtenerJuegosFavoritos() throws ExecutionException, InterruptedException {
        ExecutorService ejecutor = Thread.obtenerEjecutorHilo();
        return ejecutor.submit(() -> fuenteDeDatosFavoritos.getAllGames()).get();
    }

    public void eliminarJuegoDeFavotitos(int idJuego){
        ExecutorService ejecutor = Thread.obtenerEjecutorHilo();
        ejecutor.submit(() -> fuenteDeDatosFavoritos.deleteGame(idJuego));
    }

    //-------------------------------------------------------------------------------------//
    private IGameRepository fuenteDeDatosJuegos;
    private IFavoriteRepository fuenteDeDatosFavoritos;
}
