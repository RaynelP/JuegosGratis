package com.example.juegosgratis.repository.localDataBase;

import android.content.Context;

public class DataBaseInstance {

    public static DataBaseSQLITE obtenerConexionBaseDeDato(Context context){
        if (dataBase == null){
            dataBase = new DataBaseSQLITE(context);
        }
        return dataBase;
    }

    private static DataBaseSQLITE dataBase;
}
