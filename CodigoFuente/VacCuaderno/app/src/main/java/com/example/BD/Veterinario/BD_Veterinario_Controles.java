package com.example.BD.Veterinario;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ClasesVO.Veterinario.Controles;
import com.example.FeedReader.FeedReaderContract_Veterinario_Controles;
import com.example.FeedReader.FeedReaderDbHelper_VacApp;
import com.example.prueba03.R;

import java.util.ArrayList;

public class BD_Veterinario_Controles extends AppCompatActivity {
    FeedReaderDbHelper_VacApp dbHelper;

    public BD_Veterinario_Controles(FeedReaderDbHelper_VacApp dbHelperVacApp){
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

    public void insertarDatos(){
        ArrayList<String> animales = new ArrayList<>();
        animales.add("ES 1234 1234 1236");
        animales.add("ES 1234 1234 1235");
        animales.add("ES 1234 1234 1234");
        insertarDatos(new Controles("Control1", "", "2020-04-15", animales, "1A34 1B34"));
        insertarDatos(new Controles("Control2", "", "2019-04-15", "ES 1234 1234 1238", "1A78 1B78"));
    }

    public Controles insertarDatos(Controles controles){
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract_Veterinario_Controles.FeedEntry.TITULO, controles.getTitulo());
        values.put(FeedReaderContract_Veterinario_Controles.FeedEntry.DESCRIPCION, controles.getDescripcion());
        values.put(FeedReaderContract_Veterinario_Controles.FeedEntry.FECHA, controles.getFecha().toString());
        values.put(FeedReaderContract_Veterinario_Controles.FeedEntry.ANIMALES, controles.getAnimalesString());
        values.put(FeedReaderContract_Veterinario_Controles.FeedEntry.NUM_REGISTRO, controles.getNumRegistro());

        // Insert the new row, returning the primary key value of the new row
        //insert() devuelve el ID de la fila recién creada o -1 si hubo un error al insertar los datos.
        long newRowId = db.insert(
                //nombre de la tabla
                FeedReaderContract_Veterinario_Controles.FeedEntry.TABLE_NAME,
                //indica al framework qué hacer en caso de que ContentValues esté vacío(si no se incluye ningún valor con put)
                //si se especifica el nombre de una columna, el framework inserta una fila y establece el valor de esa columna como nulo
                //Si se especifica null, el framework no insertará una fila cuando no haya valores
                null,
                values
        );
        controles.setId(newRowId);
        db.close();

        Log.println(Log.INFO, "Control insertado", controles.toString());

        return controles;
    }
    public ArrayList<Controles> getDatosobjetos(){
        //Obtiene el repositorio de la BD en modo lectura
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // How you want the results sorted in the resulting Cursor
        String sortOrder = FeedReaderContract_Veterinario_Controles.FeedEntry.FECHA + " ASC";

        Cursor cursor = db.query(
                FeedReaderContract_Veterinario_Controles.FeedEntry.TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        ArrayList<Controles> items = getObjetos(cursor);

        cursor.close();
        db.close();

        return items;
    }
    /*ArrayList<Long> obtenerIDs(Cursor cursor){
        ArrayList<Long> items = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(FeedReaderContract_Veterinario_Controles.FeedEntry._ID));
            items.add(itemId);
        }
        return items;
    }*/
    /*ArrayList<String> obtenerTitulos(Cursor cursor){
        ArrayList<String> items = new ArrayList<>();
        while(cursor.moveToNext()) {
            String titulo = cursor.getString(cursor.getColumnIndex(FeedReaderContract_Veterinario_Controles.FeedEntry.TITULO));
            items.add(titulo);
        }
        return items;
    }*/
    ArrayList<Controles> getObjetos(Cursor cursor){
        ArrayList<Controles> items = new ArrayList<>();
        while(cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(FeedReaderContract_Veterinario_Controles.FeedEntry._ID));
            String titulo = cursor.getString(cursor.getColumnIndex(FeedReaderContract_Veterinario_Controles.FeedEntry.TITULO));
            String descripcion = cursor.getString(cursor.getColumnIndex(FeedReaderContract_Veterinario_Controles.FeedEntry.DESCRIPCION));
            String fecha = cursor.getString(cursor.getColumnIndex(FeedReaderContract_Veterinario_Controles.FeedEntry.FECHA));
            String animales = cursor.getString(cursor.getColumnIndex(FeedReaderContract_Veterinario_Controles.FeedEntry.ANIMALES));
            String precio = cursor.getString(cursor.getColumnIndex(FeedReaderContract_Veterinario_Controles.FeedEntry.NUM_REGISTRO));

            items.add(new Controles(id, titulo, descripcion, fecha, animales, precio));
        }
        return items;
    }

    public void borrarDatos(Controles controles){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String selection = FeedReaderContract_Veterinario_Controles.FeedEntry._ID + " LIKE ?";

        String[] selectionArgs = new String[1];
        selectionArgs[0] = controles.getIdString();

        db.delete(FeedReaderContract_Veterinario_Controles.FeedEntry.TABLE_NAME, selection, selectionArgs);

        Log.println(Log.INFO, "Control eliminado", controles.toString());
    }

    public void actualizarBD(Controles controles){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract_Veterinario_Controles.FeedEntry.TITULO, controles.getTitulo());
        values.put(FeedReaderContract_Veterinario_Controles.FeedEntry.DESCRIPCION, controles.getDescripcion());
        values.put(FeedReaderContract_Veterinario_Controles.FeedEntry.FECHA, controles.getFecha().toString());
        values.put(FeedReaderContract_Veterinario_Controles.FeedEntry.ANIMALES, controles.getAnimalesString());
        values.put(FeedReaderContract_Veterinario_Controles.FeedEntry.NUM_REGISTRO, controles.getNumRegistro());

        String selection = FeedReaderContract_Veterinario_Controles.FeedEntry._ID + " LIKE ?";
        String[] selectionArgs = {controles.getIdString()};

        int count = db.update(
                FeedReaderContract_Veterinario_Controles.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        db.close();

        Log.println(Log.INFO, "Control actualizado", controles.toString() + count + " filas actualizadas");
    }
}
