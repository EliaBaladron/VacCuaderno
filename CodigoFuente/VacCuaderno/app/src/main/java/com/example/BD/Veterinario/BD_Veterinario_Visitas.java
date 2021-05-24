package com.example.BD.Veterinario;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ClasesVO.Veterinario.Visitas;
import com.example.FeedReader.FeedReaderContract_Veterinario_Visitas;
import com.example.FeedReader.FeedReaderDbHelper_VacApp;
import com.example.prueba03.R;

import java.util.ArrayList;

/**
 * @author Elia Baladrón Peral
 */
public class BD_Veterinario_Visitas extends AppCompatActivity {
    FeedReaderDbHelper_VacApp dbHelper;

    /**
     * Constructor que obtiene la base de datos
     * @param dbHelperVacApp	Base de datos
     */
    public BD_Veterinario_Visitas(FeedReaderDbHelper_VacApp dbHelperVacApp){
        this.dbHelper = dbHelperVacApp;
    }

    /**
     * Método invocado en la creación del gestor de la tabla
     * @param savedInstanceState	sis
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_animales);
    }

    /**
     * Método invocado en la destrucción del gestor de la tabla
     */
    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }

    /**
     * Método que inserta datos de prueba
     */
    public void insertarDatos(){
        ArrayList<String> animales = new ArrayList<>();
        animales.add("ES 1234 1234 1234");
        animales.add("ES 1234 1234 1235");
        animales.add("ES 1234 1234 1236");

        insertarDatos(new Visitas("Visita1", "", "2020-04-15", animales, 120.0f));
        insertarDatos(new Visitas("Visita2", "", "2020-05-05", "ES 1234 1234 1238", 50.0f));
    }

    /**
     * Método que inserta un nuevo objeto
     * @param visitas	Objeto a guardar en la BD
     * @return			Objeto creado, con el ID automático añadido
     */
    public Visitas insertarDatos(Visitas visitas){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract_Veterinario_Visitas.FeedEntry.TITULO, visitas.getTitulo());
        values.put(FeedReaderContract_Veterinario_Visitas.FeedEntry.DESCRIPCION, visitas.getDescripcion());
        values.put(FeedReaderContract_Veterinario_Visitas.FeedEntry.FECHA, visitas.getFecha().toString());
        values.put(FeedReaderContract_Veterinario_Visitas.FeedEntry.ANIMALES, visitas.getAnimalesString());
        values.put(FeedReaderContract_Veterinario_Visitas.FeedEntry.PRECIO, visitas.getPrecio());

        long newRowId = db.insert(
                FeedReaderContract_Veterinario_Visitas.FeedEntry.TABLE_NAME,
                null,
                values
        );
        visitas.setId(newRowId);
        db.close();

        Log.println(Log.INFO, "Visita insertada", visitas.toString());

        return visitas;
    }
    /**
     * Obtiene todos los datos de la tabla y los guarda en objetos de la clase Visitas
     * @return	Listado de las visitas
     */
    public ArrayList<Visitas> getDatosObjetos(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sortOrder = FeedReaderContract_Veterinario_Visitas.FeedEntry.TITULO + " ASC";

        Cursor cursor = db.query(
                FeedReaderContract_Veterinario_Visitas.FeedEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                sortOrder
        );

        ArrayList<Visitas> items = getObjetos(cursor);

        cursor.close();
        db.close();

        return items;
    }
    
    ArrayList<Visitas> getObjetos(Cursor cursor){
        ArrayList<Visitas> items = new ArrayList<>();
        while(cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(FeedReaderContract_Veterinario_Visitas.FeedEntry._ID));
            String titulo = cursor.getString(cursor.getColumnIndex(FeedReaderContract_Veterinario_Visitas.FeedEntry.TITULO));
            String descripcion = cursor.getString(cursor.getColumnIndex(FeedReaderContract_Veterinario_Visitas.FeedEntry.DESCRIPCION));
            String fecha = cursor.getString(cursor.getColumnIndex(FeedReaderContract_Veterinario_Visitas.FeedEntry.FECHA));
            String animales = cursor.getString(cursor.getColumnIndex(FeedReaderContract_Veterinario_Visitas.FeedEntry.ANIMALES));
            float precio = cursor.getFloat(cursor.getColumnIndex(FeedReaderContract_Veterinario_Visitas.FeedEntry.PRECIO));

            items.add(new Visitas(id, titulo, descripcion, fecha, animales, precio));
        }
        return items;
    }

    /**
     * Elimina los datos del objeto pasaso de la base de datos
     * @param visitas	Objeto a borrar
     */
    public void borrarDatos(Visitas visitas){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String selection = FeedReaderContract_Veterinario_Visitas.FeedEntry._ID + " LIKE ?";

        String[] selectionArgs = new String[1];
        selectionArgs[0] = visitas.getIdString();

        db.delete(FeedReaderContract_Veterinario_Visitas.FeedEntry.TABLE_NAME, selection, selectionArgs);

        Log.println(Log.INFO, "Visita eliminada", visitas.toString());
    }

    /**
     * Actualiza los datos del objeto pasado en la base de datos
     * @param visitas	Objeto a actualizar
     */
    public void actualizarBD(Visitas visitas){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract_Veterinario_Visitas.FeedEntry.TITULO, visitas.getTitulo());
        values.put(FeedReaderContract_Veterinario_Visitas.FeedEntry.DESCRIPCION, visitas.getDescripcion());
        values.put(FeedReaderContract_Veterinario_Visitas.FeedEntry.FECHA, visitas.getFecha().toString());
        values.put(FeedReaderContract_Veterinario_Visitas.FeedEntry.ANIMALES, visitas.getAnimalesString());
        values.put(FeedReaderContract_Veterinario_Visitas.FeedEntry.PRECIO, visitas.getPrecio());

        String selection = FeedReaderContract_Veterinario_Visitas.FeedEntry._ID + " LIKE ?";
        String[] selectionArgs = {visitas.getIdString()};

        int count = db.update(
                FeedReaderContract_Veterinario_Visitas.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        db.close();

        Log.println(Log.INFO, "Visita actualizada", visitas.toString() + count + " filas actualizadas");
    }
}
