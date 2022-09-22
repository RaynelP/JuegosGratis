package com.example.juegosgratis.repository.localDataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseSQLITE extends SQLiteOpenHelper {
    public DataBaseSQLITE(Context context) {
        super(context, DataBaseConstans.NOMBRE_BASE_DE_DATOS, null, DataBaseConstans.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataBaseConstans.QUERY_TABLA_FAVORITOS);
        db.execSQL(DataBaseConstans.QUERY_TABLA_VISTOSRECIENTE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DataBaseConstans.QUERY_TABLA_BUSQUEDAS_RECIENTES);
    }
}
