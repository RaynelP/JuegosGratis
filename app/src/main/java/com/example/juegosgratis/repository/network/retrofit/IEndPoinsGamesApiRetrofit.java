package com.example.juegosgratis.repository.network.retrofit;

import com.example.juegosgratis.model.game.GameDetail;
import com.example.juegosgratis.model.game.Game;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IEndPoinsGamesApiRetrofit {

    @GET("api/games")
    Call<ArrayList<Game>> getAllGames();

    @GET("api/game")
    Call<GameDetail> getGameDetail(@Query("id") int id);

    @GET("games?platform={plataform}")
    Call<ArrayList<Game>> getGameByPlatform(@Path("plataform") String platform);

    @GET("api/games")
    Call<ArrayList<Game>> getGameByCategory(@Query("category") String c);

    @GET("/api/games?sort-by")
    Call<ArrayList<Game>> getGameOrderBy(@Query("order") String order);
}
