package com.example.FeedReader;

import android.provider.BaseColumns;

/**
 * Interfaz que contiene la estructura de la tabla,
 * así como el código preformateado para la creación y eliminación de la misma
 * 
 * @author Elia Baladrón Peral
 */
public interface FeedReaderContract_Animales_Terneros {

    class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "Terneros";

        public static final String CROTAL = "Crotal";
        public static final String FECHA_DESTETE = "FechaDestete";
        public static final String PROPOSITO = "Proposito";
    }

    String SQL_CREATE_ENTRIES = "CREATE TABLE " +
            FeedEntry.TABLE_NAME + " ("+
            FeedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FeedEntry.CROTAL + " TEXT," +
            /*"FOREIGN KEY (" + FeedReaderContract_Animales_Terneros.FeedEntry.CROTAL +
            ") REFERENCES " + FeedReaderContract_Animales.FeedEntry.TABLE_NAME +
            "(" +FeedReaderContract_Animales.FeedEntry.CROTAL +"),"+*/
            FeedEntry.FECHA_DESTETE + " TEXT," +
            FeedEntry.PROPOSITO + " TEXT)"
            ;

    String SQL_DELETE_ENTRIES ="DROP TABLE IF EXISTS " +
            FeedEntry.TABLE_NAME;
}
