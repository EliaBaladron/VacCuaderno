package com.example.Activity.Datos;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ajts.androidmads.library.ExcelToSQLite;
import com.ajts.androidmads.library.SQLiteToExcel;
import com.example.FeedReader.FeedReaderContract_Animales;

/**
 * @author Elia Baladrón Peral
 */
public class Activity_Exportar extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        exportar();

        onBackPressed();
    }

    void exportar(){
        SQLiteToExcel sqliteToExcel = new SQLiteToExcel(this, "VacCuaderno.db");

        sqliteToExcel.exportSingleTable(FeedReaderContract_Animales.FeedEntry.TABLE_NAME, FeedReaderContract_Animales.FeedEntry.TABLE_NAME+".xls", new SQLiteToExcel.ExportListener() {
            @Override
            public void onStart() {
            }
            @Override
            public void onCompleted(String filePath) {
                Log.println(Log.INFO, "Exportar", "Datos animales exportados");
                //Toast.makeText(getApplicationContext(), "Datos exportados", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onError(Exception e) {
            }
        });

        ExcelToSQLite excelToSQLite = new ExcelToSQLite(getApplicationContext(), "helloworld.db");
    }
}