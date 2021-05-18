package com.example.FeedReader;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FeedReaderDbHelper_VacApp extends SQLiteOpenHelper {


    //Tipos de datos
    //NULL
    //INTEGER
    //REAL
    //TEXT
    //BLOB      +-dato no estructurado


    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "VacCuaderno.db";

    public FeedReaderDbHelper_VacApp(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //Crear las tablas
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
    private void borrarCrear(){

    }


    //Borrar las tablas y crearlas nuevamente
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
