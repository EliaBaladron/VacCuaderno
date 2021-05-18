package com.example.BD.Animales;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ClasesVO.Animales.Vaca;
import com.example.FeedReader.FeedReaderContract_Animales_Vacas;
import com.example.FeedReader.FeedReaderDbHelper_VacApp;
import com.example.prueba03.R;

import java.util.ArrayList;

public class BD_Animales_Vacas extends AppCompatActivity {
    FeedReaderDbHelper_VacApp dbHelper;

    public BD_Animales_Vacas(FeedReaderDbHelper_VacApp dbHelperVacApp){
        this.dbHelper = dbHelperVacApp;

        //insertarDatos();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_animales);

        //Para acceder a la base de datos, crea una instancia de la subclase de SQLiteOpenHelper
        //dbHelper = new FeedReaderDbHelper_VacApp(getApplicationContext());
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }

    //Método que inserta datos de prueba
    public void insertarDatos(){
        ArrayList<String> terneros1 = new ArrayList<>();
        terneros1.add("ES 1234 1234 1211");
        terneros1.add("ES 1234 1234 1212");
        terneros1.add("ES 1234 1234 1213");
        insertarDatos(new Vaca("ES 1234 1234 1231", "2019-07-04", terneros1));

        ArrayList<String> terneros2 = new ArrayList<>();
        terneros1.add("ES 1234 1234 1234");
        terneros1.add("ES 1234 1234 1235");
        insertarDatos(new Vaca("ES 1234 1234 1232", "2019-07-05", terneros2));

        ArrayList<String> terneros3 = new ArrayList<>();
        terneros1.add("ES 1234 1234 1236");
        insertarDatos(new Vaca("ES 1234 1234 1233", "2019-07-05", terneros3));
    }



    public Vaca insertarDatos(Vaca vaca){
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract_Animales_Vacas.FeedEntry.CROTAL, vaca.getCrotal());
        values.put(FeedReaderContract_Animales_Vacas.FeedEntry.FECHA_EMBARAZO, vaca.getFechaEmbarazoString());
        values.put(FeedReaderContract_Animales_Vacas.FeedEntry.TERNERO, vaca.getTernerosString());

        // Insert the new row, returning the primary key value of the new row
        //insert() devuelve el ID de la fila recién creada o -1 si hubo un error al insertar los datos.
        long newRowId = db.insert(
                //nombre de la tabla
                FeedReaderContract_Animales_Vacas.FeedEntry.TABLE_NAME,
                //indica al framework qué hacer en caso de que ContentValues esté vacío(si no se incluye ningún valor con put)
                //si se especifica el nombre de una columna, el framework inserta una fila y establece el valor de esa columna como nulo
                //Si se especifica null, el framework no insertará una fila cuando no haya valores
                null,
                values
        );
        vaca.setId(newRowId);
        db.close();

        Log.println(Log.INFO, "Vaca insertada ", vaca.toString());

        return vaca;
    }
    /*void leerDatos(){
        //Obtiene el repositorio de la BD en modo lectura
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                FeedReaderContract_Animales_Vacas.FeedEntry.CROTAL,
                FeedReaderContract_Animales_Vacas.FeedEntry.FECHA_EMBARAZO,
                FeedReaderContract_Animales_Vacas.FeedEntry.TERENEROS
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = { "My Title" };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = FeedReaderContract_Animales_Vacas.FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor cursor = db.query(
                FeedReaderContract_Animales_Vacas.FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List itemIds = obtenerIDs(cursor);
    }*/
    public ArrayList<String> getDatosCrotales(){
        //Obtiene el repositorio de la BD en modo lectura
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // How you want the results sorted in the resulting Cursor
        String sortOrder = FeedReaderContract_Animales_Vacas.FeedEntry.CROTAL + " ASC";

        Cursor cursor = db.query(
                FeedReaderContract_Animales_Vacas.FeedEntry.TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        ArrayList<String> items = getCrotales(cursor);

        cursor.close();
        db.close();

        return items;
    }
    public ArrayList<Vaca> getDatosObjetos(){
        //Obtiene el repositorio de la BD en modo lectura
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // How you want the results sorted in the resulting Cursor
        String sortOrder = FeedReaderContract_Animales_Vacas.FeedEntry.CROTAL + " ASC";

        Cursor cursor = db.query(
                FeedReaderContract_Animales_Vacas.FeedEntry.TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        ArrayList<Vaca> items = getObjetos(cursor);

        cursor.close();
        db.close();

        return items;
    }
    /*ArrayList<Long> getIDs(Cursor cursor){
        ArrayList<Long> itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(FeedReaderContract_Animales_Vacas.FeedEntry._ID));
            itemIds.add(itemId);
        }
        return itemIds;
    }*/
    ArrayList<String> getCrotales(Cursor cursor){
        ArrayList<String> items = new ArrayList<>();
        while(cursor.moveToNext()) {
            String item = cursor.getString(cursor.getColumnIndex(FeedReaderContract_Animales_Vacas.FeedEntry.CROTAL));
            items.add(item);
        }
        return items;
    }
    //Adaptado a la clase vaca
    ArrayList<Vaca> getObjetos(Cursor cursor){
        ArrayList<Vaca> itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(FeedReaderContract_Animales_Vacas.FeedEntry._ID));
            String crotal = cursor.getString(cursor.getColumnIndex(FeedReaderContract_Animales_Vacas.FeedEntry.CROTAL));
            String fechaEmbarazo = cursor.getString(cursor.getColumnIndex(FeedReaderContract_Animales_Vacas.FeedEntry.FECHA_EMBARAZO));
            String ternero = cursor.getString(cursor.getColumnIndex(FeedReaderContract_Animales_Vacas.FeedEntry.TERNERO));

            itemIds.add(new Vaca(id, crotal, fechaEmbarazo, ternero));
        }
        return itemIds;
    }
    public void borrarDatos(Vaca vaca){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String selection = FeedReaderContract_Animales_Vacas.FeedEntry._ID + " LIKE ?";

        String[] selectionArgs = new String[1];
        selectionArgs[0] = vaca.getIdString();

        db.delete(FeedReaderContract_Animales_Vacas.FeedEntry.TABLE_NAME, selection, selectionArgs);

        Log.println(Log.INFO, "Vaca eliminada ", vaca.toString());
    }



    public void actualizarBD(Vaca vaca){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract_Animales_Vacas.FeedEntry.CROTAL, vaca.getCrotal());
        values.put(FeedReaderContract_Animales_Vacas.FeedEntry.FECHA_EMBARAZO, vaca.getFechaEmbarazoString());
        values.put(FeedReaderContract_Animales_Vacas.FeedEntry.TERNERO, vaca.getTernerosString());


        String selection = FeedReaderContract_Animales_Vacas.FeedEntry._ID + " LIKE ?";
        String[] selectionArgs = {vaca.getIdString()};

        int count = db.update(
                //FeedReaderDbHelper.FeedEntry.TABLE_NAME,
                FeedReaderContract_Animales_Vacas.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        db.close();

        Log.println(Log.INFO, "Vaca actualizada ", vaca.toString() + count + " filas actualizadas");
    }
}
