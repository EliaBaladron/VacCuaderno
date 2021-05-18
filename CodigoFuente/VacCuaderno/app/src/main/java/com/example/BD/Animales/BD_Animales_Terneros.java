package com.example.BD.Animales;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ClasesVO.Animales.Ternero;
import com.example.prueba03.R;
import com.example.FeedReader.*;

import java.util.ArrayList;

public class BD_Animales_Terneros extends AppCompatActivity {
    FeedReaderDbHelper_VacApp dbHelper;

    public BD_Animales_Terneros(FeedReaderDbHelper_VacApp dbHelperVacApp){
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
        insertarDatos(new Ternero("ES 1234 1234 1234", "2020-08-14", "Propisto4"));
        insertarDatos(new Ternero("ES 1234 1234 1235", "2020-08-15", "Propisto5"));
        insertarDatos(new Ternero("ES 1234 1234 1236", "2020-08-16", "Propisto6"));
    }

    public Ternero insertarDatos(Ternero ternero){
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract_Animales_Terneros.FeedEntry.CROTAL, ternero.getCrotal());
        if(ternero.getFechaDestete() == null)
            values.put(FeedReaderContract_Animales_Terneros.FeedEntry.FECHA_DESTETE, "");
        else
            values.put(FeedReaderContract_Animales_Terneros.FeedEntry.FECHA_DESTETE, ternero.getFechaDestete().toString());
        values.put(FeedReaderContract_Animales_Terneros.FeedEntry.PROPOSITO, ternero.getProposito());

        // Insert the new row, returning the primary key value of the new row
        //insert() devuelve el ID de la fila recién creada o -1 si hubo un error al insertar los datos.
        long newRowId = db.insert(
                //nombre de la tabla
                FeedReaderContract_Animales_Terneros.FeedEntry.TABLE_NAME,
                //indica al framework qué hacer en caso de que ContentValues esté vacío(si no se incluye ningún valor con put)
                //si se especifica el nombre de una columna, el framework inserta una fila y establece el valor de esa columna como nulo
                //Si se especifica null, el framework no insertará una fila cuando no haya valores
                null,
                values
        );
        ternero.setId(newRowId);
        db.close();

        Log.println(Log.INFO, "Ternero insertado ", ternero.toString());

        return ternero;
    }
    public ArrayList<String> getDatosCrotales(){
        //Obtiene el repositorio de la BD en modo lectura
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // How you want the results sorted in the resulting Cursor
        String sortOrder = FeedReaderContract_Animales_Terneros.FeedEntry.CROTAL + " ASC";

        Cursor cursor = db.query(
                FeedReaderContract_Animales_Terneros.FeedEntry.TABLE_NAME,   // The table to query
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
    public ArrayList<Ternero> getDatosObjetos(){
        //Obtiene el repositorio de la BD en modo lectura
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // How you want the results sorted in the resulting Cursor
        String sortOrder = FeedReaderContract_Animales_Terneros.FeedEntry.CROTAL + " ASC";

        Cursor cursor = db.query(
                FeedReaderContract_Animales_Terneros.FeedEntry.TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        ArrayList<Ternero> items = getObjetos(cursor);

        cursor.close();
        db.close();

        return items;
    }
    /*void leerDatos(){
        //Obtiene el repositorio de la BD en modo lectura
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                FeedReaderContract_Animales_Terneros.FeedEntry.CROTAL,
                FeedReaderContract_Animales_Terneros.FeedEntry.FECHA_DESTETE,
                FeedReaderContract_Animales_Terneros.FeedEntry.PROPOSITO
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = FeedReaderContract_Animales_Terneros.FeedEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = { "My Title" };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = FeedReaderContract_Animales_Terneros.FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor cursor = db.query(
                FeedReaderContract_Animales_Terneros.FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List itemIds = obtenerIDs(cursor);
    }*/
    /*ArrayList<Long> getIDs(Cursor cursor){
        ArrayList<Long> itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(FeedReaderContract_Animales_Terneros.FeedEntry._ID));
            itemIds.add(itemId);
        }
        return itemIds;
    }*/
    ArrayList<String> getCrotales(Cursor cursor){
        ArrayList<String> items = new ArrayList<>();
        while(cursor.moveToNext()) {
            String item = cursor.getString(
                    cursor.getColumnIndex(FeedReaderContract_Animales_Terneros.FeedEntry.CROTAL));
            items.add(item);
        }

        return items;
    }
    ArrayList<Ternero> getObjetos(Cursor cursor){
        ArrayList<Ternero> items = new ArrayList<>();
        while(cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(FeedReaderContract_Animales_Terneros.FeedEntry._ID));
            String crotal = cursor.getString(cursor.getColumnIndex(FeedReaderContract_Animales_Terneros.FeedEntry.CROTAL));
            String destete = cursor.getString(cursor.getColumnIndex(FeedReaderContract_Animales_Terneros.FeedEntry.FECHA_DESTETE));
            String proposito = cursor.getString(cursor.getColumnIndex(FeedReaderContract_Animales_Terneros.FeedEntry.PROPOSITO));

            items.add(new Ternero(id, crotal, destete, proposito));
        }
        return items;
    }
    /*void borrarDatos(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Define 'where' part of query.
        String selection = FeedReaderContract_Animales_Terneros.FeedEntry.COLUMN_NAME_TITLE + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { "MyTitle" };
        // Issue SQL statement.
        int deletedRows = db.delete(FeedReaderContract.FeedEntry.TABLE_NAME, selection, selectionArgs);
    }*/
    public void borrarDatos(Ternero ternero){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String selection = FeedReaderContract_Animales_Terneros.FeedEntry._ID + " LIKE ?";

        String[] selectionArgs = new String[1];
        selectionArgs[0] = ternero.getIdString();

        db.delete(FeedReaderContract_Animales_Terneros.FeedEntry.TABLE_NAME, selection, selectionArgs);
        Log.println(Log.INFO, "Ternero eliminado ", ternero.toString());
    }


    /*void actualizarBD(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // New value for one column
        String title = "MyNewTitle";
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract_Animales_Terneros.FeedEntry.COLUMN_NAME_TITLE, title);

        // Which row to update, based on the title
        String selection = FeedReaderContract_Animales_Terneros.FeedEntry.COLUMN_NAME_TITLE + " LIKE ?";
        String[] selectionArgs = { "MyOldTitle" };

        int count = db.update(
                //FeedReaderDbHelper.FeedEntry.TABLE_NAME,
                FeedReaderContract_Animales_Terneros.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
    }*/
    public void actualizarBD(Ternero ternero){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract_Animales_Terneros.FeedEntry.CROTAL, ternero.getCrotal());
        values.put(FeedReaderContract_Animales_Terneros.FeedEntry.FECHA_DESTETE, ternero.getFechaDestete().toString());
        values.put(FeedReaderContract_Animales_Terneros.FeedEntry.PROPOSITO, ternero.getProposito());

        String selection = FeedReaderContract_Animales_Terneros.FeedEntry._ID + " LIKE ?";
        String[] selectionArgs = {ternero.getIdString()};

        int count = db.update(
                //FeedReaderDbHelper.FeedEntry.TABLE_NAME,
                FeedReaderContract_Animales_Terneros.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        db.close();

        Log.println(Log.INFO, "Ternero actualizado ", ternero.toString() + count + " filas actualizadas");
    }
}
