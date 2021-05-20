package com.example.FeedReader;

import android.provider.BaseColumns;

/**
 * Interfaz que contiene la estructura de la tabla,
 * así como el código preformateado para la creación y eliminación de la misma
 * 
 * @author Elia Baladrón Peral
 */
public interface FeedReaderContract_Veterinario_Controles {

    class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "ControlesVeterinarios";
        public static final String TITULO = "Titulo";
        public static final String DESCRIPCION = "Descripcion";
        public static final String FECHA = "Fecha";
        public static final String ANIMALES = "ListadoAnimales";
        public static final String NUM_REGISTRO = "NumeroRegistro";
    }

    String SQL_CREATE_ENTRIES = "CREATE TABLE " +
            FeedEntry.TABLE_NAME + " ("+
            FeedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FeedEntry.TITULO + " TEXT," +
            FeedEntry.DESCRIPCION + " TEXT," +
            FeedEntry.FECHA + " TEXT," +
            FeedEntry.ANIMALES + " TEXT," +
            FeedEntry.NUM_REGISTRO + " TEXT)"
            ;

    String SQL_DELETE_ENTRIES ="DROP TABLE IF EXISTS " +
            FeedEntry.TABLE_NAME;
}
