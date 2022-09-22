package com.example.juegosgratis.repository.localDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.juegosgratis.repository.network.Interfaces.IFavoriteRepository;
import com.example.juegosgratis.model.BusquedaReciente;
import com.example.juegosgratis.model.game.GameFavorite;

import java.util.ArrayList;

public class FuenteDeDatosFavoritosYVistos implements IFavoriteRepository {

    private static FuenteDeDatosFavoritosYVistos fuenteDeDatosFavoritosYVistos = null;
    private DataBaseSQLITE baseDeDatos;

    public static FuenteDeDatosFavoritosYVistos instanciar(Context context){

        if(fuenteDeDatosFavoritosYVistos == null){
            fuenteDeDatosFavoritosYVistos = new FuenteDeDatosFavoritosYVistos(context);
        }
        return fuenteDeDatosFavoritosYVistos;

    }

    private FuenteDeDatosFavoritosYVistos(Context context) {
        baseDeDatos = DataBaseInstance
                .obtenerConexionBaseDeDato(context);
    }

    @Override
    public void addGame(GameFavorite favoriteGame) {
        agregar(favoriteGame, DataBaseConstans.TABLE_FAVORITOS_NOMBRE);
    }

    @Override
    public void agregarJuegoAVistosRecientemente(GameFavorite favoriteGame) {
        agregar(favoriteGame, DataBaseConstans.TABLE_VISTOSRECIENTEMENTE_NOMBRE);
    }

    @Override
    public void deleteGame(int gameId) {
        SQLiteDatabase writer = baseDeDatos.getWritableDatabase();
        writer.execSQL(DataBaseConstans.queryParaELiminarRegistro(DataBaseConstans.TABLE_FAVORITOS_NOMBRE, gameId));
        writer.close();
    }

    @Override
    public void eliminarJuegoDeVistosRecienteMente(int gameId) {
        SQLiteDatabase writer = baseDeDatos.getWritableDatabase();
        writer.execSQL(DataBaseConstans.queryParaELiminarRegistro(DataBaseConstans.TABLE_VISTOSRECIENTEMENTE_NOMBRE, gameId));
        writer.close();
    }

    @Override
    public ArrayList<GameFavorite> getAllGames() {
        return obtener(DataBaseConstans.queryObtenerTabla(DataBaseConstans.TABLE_FAVORITOS_NOMBRE));
    }

    @Override
    public ArrayList<GameFavorite> obtenerJuegosVistosRecientemente() {
        return obtener(DataBaseConstans.queryObtenerTabla(DataBaseConstans.TABLE_VISTOSRECIENTEMENTE_NOMBRE));
    }

    @Override
    public void agregarBusquedaReciente(String busqueda) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseConstans.CAMPO_BUSQUEDA, busqueda);

        SQLiteDatabase writer = baseDeDatos.getWritableDatabase();
        writer.insert(DataBaseConstans.TABLE_BUSQUEDASRECIENTES_NOMBRE, null, contentValues);
        baseDeDatos.close();
    }

    @Override
    public void eliminarBusquedaReciente(BusquedaReciente busqueda) {
        SQLiteDatabase writer = baseDeDatos.getWritableDatabase();
        writer.execSQL(DataBaseConstans.queryParaELiminarRegistro(DataBaseConstans.TABLE_BUSQUEDASRECIENTES_NOMBRE, busqueda.getId()));
        writer.close();
    }

    @Override
    public ArrayList<BusquedaReciente> obtenerBusquedasRecientes() {
        SQLiteDatabase reader = baseDeDatos.getReadableDatabase();
        ArrayList<BusquedaReciente> busquedaRecientes;
        try (Cursor cursor = reader.rawQuery(DataBaseConstans.queryObtenerTabla(DataBaseConstans.TABLE_BUSQUEDASRECIENTES_NOMBRE), null)) {
            busquedaRecientes = new ArrayList<>(cursor.getCount());

            int i = 0;
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String busqueda = cursor.getString(1);

                busquedaRecientes.add(i, new BusquedaReciente(id, busqueda));
                i++;
            }
        }
        return busquedaRecientes;
    }

    private void agregar(GameFavorite j, String tabla){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseConstans.CAMPO_ID_JUEGO, j.getId());
        contentValues.put(DataBaseConstans.CAMPO_NOMBRE_JUEGO, j.getNombre());
        contentValues.put(DataBaseConstans.CAMPO_LINKIMAGEN_JUEGO, j.getImagen());

        SQLiteDatabase writer = baseDeDatos.getWritableDatabase();
        writer.insert(tabla, null, contentValues);
        baseDeDatos.close();
    }

    private ArrayList<GameFavorite> obtener(String query){
        SQLiteDatabase reader = baseDeDatos.getReadableDatabase();
        ArrayList<GameFavorite> juegos;
        try (Cursor cursor = reader.rawQuery(query, null)) {
            juegos = new ArrayList<>(cursor.getCount());

            int i = 0;
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String nombre = cursor.getString(1);
                String linkImagen = cursor.getString(2);

                juegos.add(i, new GameFavorite(linkImagen, nombre, id));
                i++;
            }
        }
        return juegos;
    }

}
