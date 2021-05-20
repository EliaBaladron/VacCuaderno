package com.example.FeedReader;

import android.provider.BaseColumns;

import java.util.Date;

/**
 * Interfaz que contiene la estructura de la tabla,
 * así como el código preformateado para la creación y eliminación de la misma
 * 
 * @author Elia Baladrón Peral
 */
public interface FeedReaderContract_Animales {

    class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "Animales";

        public static final String NOMBRE = "Nombre";
        public static final String CROTAL = "Crotal";
        public static final String FECHA_NAC = "FechaNacimiento";
        public static final String SEXO = "Sexo";
        public static final String RAZA = "Raza";
        public static final String COD_MADRE = "CodigoMadre";
        public static final String ID_REB = "IdRebaño";
    }


    String SQL_CREATE_ENTRIES = "CREATE TABLE " +
            FeedEntry.TABLE_NAME + " ("+
            FeedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FeedEntry.NOMBRE + " TEXT," +
            FeedEntry.CROTAL + " TEXT," +
            FeedEntry.FECHA_NAC + " TEXT," +
            FeedEntry.SEXO + " TEXT," +
            FeedEntry.RAZA + " TEXT," +
            FeedEntry.COD_MADRE + " TEXT," +
            FeedEntry.ID_REB + " TEXT)"
            ;

    String SQL_DELETE_DATA ="DELETE FROM " +
            FeedEntry.TABLE_NAME;

    String SQL_DELETE_ENTRIES ="DROP TABLE IF EXISTS " +
            FeedEntry.TABLE_NAME;
}
