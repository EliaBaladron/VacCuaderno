package com.example.FeedReader;

import android.provider.BaseColumns;

/**
 * Interfaz que contiene la estructura de la tabla,
 * así como el código preformateado para la creación y eliminación de la misma
 * 
 * @author Elia Baladrón Peral
 */
public interface  FeedReaderContract_Animales_Vacas {

    class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "Vacas";
        public static final String CROTAL = "Crotal";
        public static final String FECHA_EMBARAZO = "FechaEmbarazo";
        public static final String TERNERO = "Terneros";
    }


    String SQL_CREATE_ENTRIES = "CREATE TABLE " +
            FeedEntry.TABLE_NAME + " ("+
            FeedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FeedEntry.CROTAL + " TEXT," +
            FeedEntry.FECHA_EMBARAZO + " TEXT," +
            FeedEntry.TERNERO + " TEXT)"
            ;

    String SQL_DELETE_ENTRIES ="DROP TABLE IF EXISTS " +
            FeedEntry.TABLE_NAME;
}
