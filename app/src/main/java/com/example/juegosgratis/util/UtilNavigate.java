package com.example.juegosgratis.util;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.juegosgratis.view.MainActivity;

public class UtilNavigate {
    public static final String WORD = "word";
    public static final String BUSQUEDA_POR_ORDEN = "pororden";
    public final static String BUSQUEDA_POR_GENERO = "porgenero";
    public final static String BUSQUEDA_POR_NOMBRE = "pornombre";

    public static final String ORDEN = "orden";
    public final static String GENERO = "genero";
    public final static String NOMBRE = "nombre";

    public final static String FAVORITOS = "favoritos";

    public final static String ABRIR_JUEGO_EN_DETALLE = "abrirjuego";
    public final static String TIPO_DE_BUSQUEDA = "tipo";
    public final static String GAME_ID = "id";

    public static void navigate(@NonNull View view, @NonNull int action, @Nullable Bundle bundle){
        NavController navController =
                Navigation.findNavController(view);
        navController.navigate(action, bundle);
    }

    public static Bundle instanceBundleToGameList(String keyWord, String word){
        Bundle bundle = new Bundle();

        bundle.putString(TIPO_DE_BUSQUEDA, keyWord);
        bundle.putString(WORD, word);

        return bundle;
    }

}
