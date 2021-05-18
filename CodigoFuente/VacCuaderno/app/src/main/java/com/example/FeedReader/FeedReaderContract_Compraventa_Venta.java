package com.example.FeedReader;

import android.provider.BaseColumns;

public interface FeedReaderContract_Compraventa_Venta {

    class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "Ventas";

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
