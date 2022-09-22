package com.example.juegosgratis.repository.network.retrofit;

import com.example.juegosgratis.repository.network.adapters.DeserializerFullGame;
import com.example.juegosgratis.model.game.GameDetail;
import com.example.juegosgratis.model.game.Game;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FreeGamesApiClient {

    public static Retrofit obtenerConexion(Gson gson){
        Retrofit retrofit = null;
        GsonConverterFactory gsonConverterFactory;
        if(gson == null){
            gsonConverterFactory = GsonConverterFactory.create();
        }else{
            gsonConverterFactory = GsonConverterFactory.create(gson);
        }

        Retrofit.Builder retrofilBuilder = new Retrofit.Builder();
        retrofilBuilder.baseUrl(ConstansApiFreeGame.BASIC_URL);
        retrofilBuilder.addConverterFactory(gsonConverterFactory);
        return retrofilBuilder.build();
    }

    public static Gson obtenerDeserealizadorDeJuegoCompleto(Game game){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(GameDetail.class, new DeserializerFullGame(game));
        return gsonBuilder.create();
    }
}
