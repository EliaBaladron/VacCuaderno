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

/**
 * @author Elia Baladrón Peral
 */
public class BD_Veterinario_Controles extends AppCompatActivity {
    FeedReaderDbHelper_VacApp dbHelper;

    /**
     * Constructor que obtiene la base de datos
     * @param dbHelperVacApp	Base de datos
     */
    public BD_Veterinario_Controles(FeedReaderDbHelper_VacApp dbHelperVacApp){
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
        animales.add("ES 1234 1234 1236");
        animales.add("ES 1234 1234 1235");
        animales.add("ES 1234 1234 1234");
        insertarDatos(new Controles("Control1", "", "2020-04-15", animales, "1A34 1B34"));
        insertarDatos(new Controles("Control2", "", "2019-04-15", "ES 1234 1234 1238", "1A78 1B78"));
    }

    /**
     * Método que inserta un nuevo objeto
     * @param controles	Objeto a guardar en la BD
     * @return			Objeto creado, con el ID automático añadido
     */
    public Controles insertarDatos(Controles controles){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract_Veterinario_Controles.FeedEntry.TITULO, controles.getTitulo());
        values.put(FeedReaderContract_Veterinario_Controles.FeedEntry.DESCRIPCION, controles.getDescripcion());
        values.put(FeedReaderContract_Veterinario_Controles.FeedEntry.FECHA, controles.getFecha().toString());
        values.put(FeedReaderContract_Veterinario_Controles.FeedEntry.ANIMALES, controles.getAnimalesString());
        values.put(FeedReaderContract_Veterinario_Controles.FeedEntry.NUM_REGISTRO, controles.getNumRegistro());

        long newRowId = db.insert(
                FeedReaderContract_Veterinario_Controles.FeedEntry.TABLE_NAME,
                null,
                values
        );
        controles.setId(newRowId);
        db.close();

        Log.println(Log.INFO, "Control insertado", controles.toString());

        return controles;
    }
    /**
     * Obtiene todos los datos de la tabla y los guarda en objetos de la clase Controles
     * @return	Listado de Crotales
     */
    public ArrayList<Controles> getDatosObjetos(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sortOrder = FeedReaderContract_Veterinario_Controles.FeedEntry.FECHA + " ASC";

        Cursor cursor = db.query(
                FeedReaderContract_Veterinario_Controles.FeedEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                sortOrder
        );

        ArrayList<Controles> items = getObjetos(cursor);

        cursor.close();
        db.close();

        return items;
    }
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

    /**
     * Elimina los datos del objeto pasaso de la base de datos
     * @param controles	Objeto a borrar
     */
    public void borrarDatos(Controles controles){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String selection = FeedReaderContract_Veterinario_Controles.FeedEntry._ID + " LIKE ?";

        String[] selectionArgs = new String[1];
        selectionArgs[0] = controles.getIdString();

        db.delete(FeedReaderContract_Veterinario_Controles.FeedEntry.TABLE_NAME, selection, selectionArgs);

        Log.println(Log.INFO, "Control eliminado", controles.toString());
    }

    /**
     * Actualiza los datos del objeto pasado en la base de datos
     * @param controles	Objeto a actualizar
     */
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
