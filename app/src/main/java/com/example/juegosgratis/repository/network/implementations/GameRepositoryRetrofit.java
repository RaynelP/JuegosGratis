package com.example.juegosgratis.repository.network.implementations;

import com.example.juegosgratis.repository.network.Interfaces.IGameRepository;
import com.example.juegosgratis.repository.network.retrofit.IEndPoinsGamesApiRetrofit;
import com.example.juegosgratis.model.game.GameDetail;
import com.example.juegosgratis.model.game.Game;
import com.example.juegosgratis.repository.network.retrofit.FreeGamesApiClient;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GameRepositoryRetrofit implements IGameRepository {

    @Override
    public List<Game> getAllGames() {
        return obtenerListaDeJuegos(TODOS_LOS_JUEGOS, null);
    }

    @Override
    public List<Game> getGameByCategory(String categoria) {
        return obtenerListaDeJuegos(JUEGOS_POR_CATEGORIA, categoria);
    }

    @Override
    public List<Game> getGamesOrderBy(String orden) {
        return obtenerListaDeJuegos(JUEGOS_ORDENADOS_POR, orden);
    }

    @Override
    public List<Game> getGameByPlatform(String platforma) {
        return obtenerListaDeJuegos(JUEGOS_POR_PLATAFORMA, platforma);
    }

    @Override
    public GameDetail getGameDetail(Game juego) {
        return obtenerJuego(JUEGO_PARTICULAR_USANDO_JUEGO_SIN_DETALLES, juego, null);
    }

    @Override
    public GameDetail getGameDetail(int idGame) {
        return obtenerJuego(JUEGO_PARTICULAR, null, idGame);
    }

    private List<Game> obtenerListaDeJuegos(int criterioDeBusqueda, String palabraDeBusqueda){
        IEndPoinsGamesApiRetrofit endsPoins = obtenerInstanciaEndsPoins(null);
        ArrayList<Game> lista_juegos = null;
        Call<ArrayList<Game>> call = null;
        switch (criterioDeBusqueda) {
            case TODOS_LOS_JUEGOS:
                call = endsPoins.getAllGames();
                break;
            case JUEGOS_POR_CATEGORIA:
                call = endsPoins.getGameByCategory(palabraDeBusqueda);
                break;
            case JUEGOS_POR_PLATAFORMA:
                break;
            case JUEGOS_ORDENADOS_POR:
                call = endsPoins.getGameOrderBy(palabraDeBusqueda);
                break;
            default:
                break;
        }
        return hacerLLamada(lista_juegos, call);
    }

    private GameDetail obtenerJuego(int tipoDeBusqueda, Game juego, Integer idJuego){
        GameDetail fullGame = null;
        Call<GameDetail> call = null;
        Gson desearilizadorJuegoCompleto = null;

        IEndPoinsGamesApiRetrofit endsPoins = null;
                switch (tipoDeBusqueda) {
            case JUEGO_PARTICULAR_USANDO_JUEGO_SIN_DETALLES:
                desearilizadorJuegoCompleto = FreeGamesApiClient.obtenerDeserealizadorDeJuegoCompleto(juego);
                endsPoins = obtenerInstanciaEndsPoins(desearilizadorJuegoCompleto);
                call = endsPoins.getGameDetail(juego.getId());
                break;
            case JUEGO_PARTICULAR:
                desearilizadorJuegoCompleto = FreeGamesApiClient.obtenerDeserealizadorDeJuegoCompleto(null);
                endsPoins = obtenerInstanciaEndsPoins(desearilizadorJuegoCompleto);
                call = endsPoins.getGameDetail(idJuego);
                break;
            default:
                break;
        }
        return hacerLLamada(fullGame, call);
    }

    private ArrayList<Game> hacerLLamada(ArrayList<Game> lista_juegos, Call<ArrayList<Game>> call){
        try {
            Response<ArrayList<Game>> r = call.execute();
            lista_juegos = r.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista_juegos;
    }

    private GameDetail hacerLLamada(GameDetail fullGame, Call<GameDetail> call){
        try {
            fullGame = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fullGame;
    }

    private IEndPoinsGamesApiRetrofit obtenerInstanciaEndsPoins(Gson gson){
        Retrofit retrofit = FreeGamesApiClient.obtenerConexion(gson);
        return retrofit.create(IEndPoinsGamesApiRetrofit.class);
    }

    private List<Game> listaJuegos;

    public static final int JUEGOS_POR_CATEGORIA = 1;
    public static final int TODOS_LOS_JUEGOS = 2;
    public static final int JUEGOS_POR_PLATAFORMA = 3;
    public static final int JUEGOS_ORDENADOS_POR = 4;
    public static final int JUEGO_PARTICULAR_USANDO_JUEGO_SIN_DETALLES = 5;
    public static final int JUEGO_PARTICULAR = 6;
    public static final int JUEGOS_POR_GENERO = 7;
}
