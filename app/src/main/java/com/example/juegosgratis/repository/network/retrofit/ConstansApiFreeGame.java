package com.example.juegosgratis.repository.network.retrofit;

public class ConstansApiFreeGame {
    public static String BASIC_URL = "https://www.freetogame.com/";

    public final static String GAMES = "/games";
    public final static String GAMES_PLATFORM = "/games?platform={pc}";
    public final static String GAMES_CATEGORY = "/games?category={shooter}";
    public final static String GAMES_ID = "/game?id={452}";

    public static String LINK_PETICION_CATEGORIA = "";

    public final static void getCategory(String categoria){
        LINK_PETICION_CATEGORIA = "games?category=" + categoria;
    }
}
