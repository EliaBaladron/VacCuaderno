package com.example.FeedReader;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Clase que extiende SQLiteOpenHelper para el control de la base de datos
 * @author Elia Baladrón Peral
 */
public class FeedReaderDbHelper_VacApp extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "VacCuaderno.db";

    public FeedReaderDbHelper_VacApp(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creacion de las tablas
     * @param db    Instancia de la BD
     */
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FeedReaderContract_Animales.             SQL_DELETE_ENTRIES);
        db.execSQL(FeedReaderContract_Animales.             SQL_CREATE_ENTRIES);

        db.execSQL(FeedReaderContract_Animales_Vacas.       SQL_DELETE_ENTRIES);
        db.execSQL(FeedReaderContract_Animales_Vacas.       SQL_CREATE_ENTRIES);

        db.execSQL(FeedReaderContract_Animales_Terneros.    SQL_DELETE_ENTRIES);
        db.execSQL(FeedReaderContract_Animales_Terneros.    SQL_CREATE_ENTRIES);

        db.execSQL(FeedReaderContract_Rebaño.               SQL_DELETE_ENTRIES);
        db.execSQL(FeedReaderContract_Rebaño.               SQL_CREATE_ENTRIES);

        db.execSQL(FeedReaderContract_Veterinario_Controles.SQL_DELETE_ENTRIES);
        db.execSQL(FeedReaderContract_Veterinario_Controles.SQL_CREATE_ENTRIES);

        db.execSQL(FeedReaderContract_Veterinario_Visitas.  SQL_DELETE_ENTRIES);
        db.execSQL(FeedReaderContract_Veterinario_Visitas.  SQL_CREATE_ENTRIES);

        db.execSQL(FeedReaderContract_Crotales_Faltan.      SQL_DELETE_ENTRIES);
        db.execSQL(FeedReaderContract_Crotales_Faltan.      SQL_CREATE_ENTRIES);

        db.execSQL(FeedReaderContract_Crotales_Pedidos.     SQL_DELETE_ENTRIES);
        db.execSQL(FeedReaderContract_Crotales_Pedidos.     SQL_CREATE_ENTRIES);

        db.execSQL(FeedReaderContract_Crotales_Recibidos.   SQL_DELETE_ENTRIES);
        db.execSQL(FeedReaderContract_Crotales_Recibidos.   SQL_CREATE_ENTRIES);

        db.execSQL(FeedReaderContract_Crotales_SinPoner.    SQL_DELETE_ENTRIES);
        db.execSQL(FeedReaderContract_Crotales_SinPoner.    SQL_CREATE_ENTRIES);

        db.execSQL(FeedReaderContract_Compraventa_Compra.    SQL_DELETE_ENTRIES);
        db.execSQL(FeedReaderContract_Compraventa_Compra.    SQL_CREATE_ENTRIES);

        db.execSQL(FeedReaderContract_Compraventa_Venta.    SQL_DELETE_ENTRIES);
        db.execSQL(FeedReaderContract_Compraventa_Venta.    SQL_CREATE_ENTRIES);
    }


    /**
     * Actualización de la BD
     * @param db            Instancia de la BD
     * @param oldVersion    Version anterior
     * @param newVersion    Nueva versión
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(FeedReaderContract_Animales.             SQL_DELETE_ENTRIES);
        db.execSQL(FeedReaderContract_Animales_Vacas.       SQL_DELETE_ENTRIES);
        db.execSQL(FeedReaderContract_Animales_Terneros.    SQL_DELETE_ENTRIES);
        db.execSQL(FeedReaderContract_Rebaño.               SQL_DELETE_ENTRIES);
        db.execSQL(FeedReaderContract_Veterinario_Controles.SQL_DELETE_ENTRIES);
        db.execSQL(FeedReaderContract_Veterinario_Visitas.  SQL_DELETE_ENTRIES);
        db.execSQL(FeedReaderContract_Crotales_Faltan.      SQL_DELETE_ENTRIES);
        db.execSQL(FeedReaderContract_Crotales_Pedidos.     SQL_DELETE_ENTRIES);
        db.execSQL(FeedReaderContract_Crotales_Recibidos.   SQL_DELETE_ENTRIES);
        db.execSQL(FeedReaderContract_Crotales_SinPoner.    SQL_DELETE_ENTRIES);
        db.execSQL(FeedReaderContract_Compraventa_Compra.    SQL_DELETE_ENTRIES);
        db.execSQL(FeedReaderContract_Compraventa_Venta.    SQL_DELETE_ENTRIES);

        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
