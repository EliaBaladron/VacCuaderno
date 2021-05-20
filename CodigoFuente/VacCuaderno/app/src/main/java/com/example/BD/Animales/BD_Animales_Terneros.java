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

/**
 * @author Elia Baladrón Peral
 */
public class BD_Animales_Terneros extends AppCompatActivity {
    FeedReaderDbHelper_VacApp dbHelper;

    /**
     * Constructor que obtiene la base de datos
     * @param dbHelperVacApp	Base de datos
     */
    public BD_Animales_Terneros(FeedReaderDbHelper_VacApp dbHelperVacApp){
        this.dbHelper = dbHelperVacApp;

        //insertarDatos();
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
        insertarDatos(new Ternero("ES 1234 1234 1234", "2020-08-14", "Proposito4"));
        insertarDatos(new Ternero("ES 1234 1234 1235", "2020-08-15", "Proposito5"));
        insertarDatos(new Ternero("ES 1234 1234 1236", "2020-08-16", "Proposito6"));
    }

    /**
     * Método que inserta un nuevo objeto
     * @param ternero	Objeto a guardar en la BD
     * @return			Objeto creado, con el ID automático añadido
     */
    public Ternero insertarDatos(Ternero ternero){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract_Animales_Terneros.FeedEntry.CROTAL, ternero.getCrotal());
        if(ternero.getFechaDestete() == null)
            values.put(FeedReaderContract_Animales_Terneros.FeedEntry.FECHA_DESTETE, "");
        else
            values.put(FeedReaderContract_Animales_Terneros.FeedEntry.FECHA_DESTETE, ternero.getFechaDestete().toString());
        values.put(FeedReaderContract_Animales_Terneros.FeedEntry.PROPOSITO, ternero.getProposito());

        long newRowId = db.insert(
                FeedReaderContract_Animales_Terneros.FeedEntry.TABLE_NAME,
                null,
                values
        );
        ternero.setId(newRowId);
        db.close();

        Log.println(Log.INFO, "Ternero insertado ", ternero.toString());

        return ternero;
    }
    /**
     * Obtiene los datos de los crotales de todos los terneros de la tabla
     * @return	Listado de los crotales
     */
    public ArrayList<String> getDatosCrotales(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sortOrder = FeedReaderContract_Animales_Terneros.FeedEntry.CROTAL + " ASC";

        Cursor cursor = db.query(
                FeedReaderContract_Animales_Terneros.FeedEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                sortOrder
        );

        ArrayList<String> items = getCrotales(cursor);

        cursor.close();
        db.close();

        return items;
    }
    /**
     * Obtiene todos los datos de la tabla y los guarda en objetos de la clase Ternero
     * @return	Listado de Ternero
     */
    public ArrayList<Ternero> getDatosObjetos(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sortOrder = FeedReaderContract_Animales_Terneros.FeedEntry.CROTAL + " ASC";

        Cursor cursor = db.query(
                FeedReaderContract_Animales_Terneros.FeedEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                sortOrder
        );

        ArrayList<Ternero> items = new ArrayList<>(getObjetos(cursor));

        cursor.close();
        db.close();

        return items;
    }
    /**
     * Obtiene los datos de la tabla con el crotal pasado
     * @param crotal    Crotal que deben tener los objetos obtenidos
     * @return          Listado de Ternero cuyo crotal es igual al pasado
     */
    public ArrayList<Ternero> getDatosObjeto(String crotal){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sortOrder = FeedReaderContract_Animales_Terneros.FeedEntry.CROTAL + " DESC";

        String selection = FeedReaderContract_Animales_Terneros.FeedEntry.CROTAL + " = ?";
        String[] selectionArgs = { crotal };

        Cursor cursor = db.query(
                FeedReaderContract_Animales_Terneros.FeedEntry.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        ArrayList<Ternero> items = new ArrayList<>(getObjetos(cursor));

        cursor.close();
        db.close();

        return items;
    }
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
    /**
     * Elimina los datos del objeto pasaso de la base de datos
     * @param ternero	Objeto a borrar
     */
    public void borrarDatos(Ternero ternero){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String selection = FeedReaderContract_Animales_Terneros.FeedEntry._ID + " LIKE ?";

        String[] selectionArgs = new String[1];
        selectionArgs[0] = ternero.getIdString();

        db.delete(FeedReaderContract_Animales_Terneros.FeedEntry.TABLE_NAME, selection, selectionArgs);
        Log.println(Log.INFO, "Ternero eliminado ", ternero.toString());
    }
    
    /**
     * Actualiza los datos del objeto pasado en la base de datos
     * @param ternero	Objeto a actualizar
     */
    public void actualizarBD(Ternero ternero){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract_Animales_Terneros.FeedEntry.CROTAL, ternero.getCrotal());
        values.put(FeedReaderContract_Animales_Terneros.FeedEntry.FECHA_DESTETE, ternero.getFechaDestete().toString());
        values.put(FeedReaderContract_Animales_Terneros.FeedEntry.PROPOSITO, ternero.getProposito());

        String selection = FeedReaderContract_Animales_Terneros.FeedEntry._ID + " LIKE ?";
        String[] selectionArgs = {ternero.getIdString()};

        int count = db.update(
                FeedReaderContract_Animales_Terneros.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        db.close();

        Log.println(Log.INFO, "Ternero actualizado ", ternero.toString() + count + " filas actualizadas");
    }
}
