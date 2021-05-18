package com.example.FeedReader;

import android.provider.BaseColumns;

public interface FeedReaderContract_Rebaño {

    class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "Rebaños";
        public static final String NOMBRE = "Nombre";
    }

    String SQL_CREATE_ENTRIES = "CREATE TABLE " +
            FeedEntry.TABLE_NAME + " ("+
            FeedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FeedEntry.NOMBRE + " TEXT)"
            ;

    String SQL_DELETE_ENTRIES ="DROP TABLE IF EXISTS " +
            FeedEntry.TABLE_NAME;
}
