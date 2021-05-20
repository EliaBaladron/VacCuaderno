package com.example.FeedReader;

import android.provider.BaseColumns;

/**
 * Interfaz que contiene la estructura de la tabla,
 * así como el código preformateado para la creación y eliminación de la misma
 * 
 * @author Elia Baladrón Peral
 */
public interface FeedReaderContract_Compraventa_Compra {

    class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "Compras";

        public static final String CROTAL = "Crotal";
        public static final String PRECIO = "Precio";
        public static final String FECHA = "Fecha";
    }


    String SQL_CREATE_ENTRIES = "CREATE TABLE " +
            FeedEntry.TABLE_NAME + " ("+
            FeedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FeedEntry.CROTAL + " TEXT," +
            FeedEntry.PRECIO + " REAL," +
            FeedEntry.FECHA + " TEXT)"
            ;

    String SQL_DELETE_ENTRIES ="DROP TABLE IF EXISTS " +
            FeedEntry.TABLE_NAME;
}
