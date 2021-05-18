package com.example.FeedReader;

import android.provider.BaseColumns;

/**
 * @author Elia Baladrón Peral
 */
public interface FeedReaderContract_Crotales_Recibidos {

    class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "Crotales_Recibidos";
        public static final String CROTAL = "Crotal";
        public static final String UNIDADES = "Unidades";
    }

String SQL_CREATE_ENTRIES = "CREATE TABLE " +
        FeedEntry.TABLE_NAME + " ("+
        FeedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
        FeedEntry.CROTAL + " TEXT," +
        FeedEntry.UNIDADES + " INTEGER)"
        ;

    String SQL_DELETE_ENTRIES ="DROP TABLE IF EXISTS " +
            FeedEntry.TABLE_NAME;
}
