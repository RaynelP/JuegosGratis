package com.example.juegosgratis.repository.localDataBase;

public class DataBaseConstans {

    public static String NOMBRE_BASE_DE_DATOS = "favoritos_vistosRecientemente";
    public static int VERSION = 4;

    public static String TABLE_FAVORITOS_NOMBRE = "favoritos";
    public static String CAMPO_ID_JUEGO = "id";
    public static String CAMPO_NOMBRE_JUEGO = "nombre";
    public static String CAMPO_LINKIMAGEN_JUEGO = "link";

    public static String TABLE_BUSQUEDASRECIENTES_NOMBRE = "tablabusquedas";
    public static String CAMPO_BUSQUEDA = "busqueda";
    public static String CAMPO_ID = "id";


    public static String TABLE_VISTOSRECIENTEMENTE_NOMBRE = "vistos";

    protected static String QUERY_TABLA_FAVORITOS =
            "CREATE TABLE " + TABLE_FAVORITOS_NOMBRE + "(" +
            CAMPO_ID_JUEGO + " INTEGER PRIMARY KEY" + ", " +
            CAMPO_NOMBRE_JUEGO + " TEXT" + ", " +
            CAMPO_LINKIMAGEN_JUEGO + " TEXT" +
            ");";

    protected static String QUERY_TABLA_VISTOSRECIENTE_QUERY =
            "CREATE TABLE " + TABLE_VISTOSRECIENTEMENTE_NOMBRE + "(" +
                    CAMPO_ID_JUEGO + " INTEGER PRIMARY KEY" + ", " +
                    CAMPO_NOMBRE_JUEGO + " TEXT" + ", " +
                    CAMPO_LINKIMAGEN_JUEGO + " TEXT" +
                    ");";

    protected static String QUERY_TABLA_BUSQUEDAS_RECIENTES =
            "CREATE TABLE " + TABLE_BUSQUEDASRECIENTES_NOMBRE + "(" +
                    CAMPO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + ", " +
                    CAMPO_BUSQUEDA + " TEXT" +
                    ");";

    public static String queryParaELiminarRegistro(String tabla, int idJuego){
        return "DELETE FROM " + tabla + " WHERE " + CAMPO_ID_JUEGO + " = " + idJuego + ";";
    }

    public static String queryObtenerTabla(String tabla){
        return "SELECT * FROM " + tabla + ";";
    }
}
