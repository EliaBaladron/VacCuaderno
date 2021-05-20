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

/**
 * @author Elia Baladrón Peral
 */
public class BD_Animales_Vacas extends AppCompatActivity {
    FeedReaderDbHelper_VacApp dbHelper;

    /**
     * Constructor que obtiene la base de datos
     * @param dbHelperVacApp	Base de datos
     */
    public BD_Animales_Vacas(FeedReaderDbHelper_VacApp dbHelperVacApp){
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
    
    /**
     * Método que inserta un nuevo objeto
     * @param vaca		Objeto a guardar en la BD
     * @return			Objeto creado, con el ID automático añadido
     */
    public Vaca insertarDatos(Vaca vaca){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract_Animales_Vacas.FeedEntry.CROTAL, vaca.getCrotal());
        values.put(FeedReaderContract_Animales_Vacas.FeedEntry.FECHA_EMBARAZO, vaca.getFechaEmbarazoString());
        values.put(FeedReaderContract_Animales_Vacas.FeedEntry.TERNERO, vaca.getTernerosString());

        long newRowId = db.insert(
                FeedReaderContract_Animales_Vacas.FeedEntry.TABLE_NAME,
                null,
                values
        );
        vaca.setId(newRowId);
        db.close();

        Log.println(Log.INFO, "Vaca insertada ", vaca.toString());

        return vaca;
    }
    /**
     * Obtiene los datos de los crotales de todos las vacas de la tabla
     * @return	Listado de los crotales
     */
    public ArrayList<String> getDatosCrotales(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sortOrder = FeedReaderContract_Animales_Vacas.FeedEntry.CROTAL + " ASC";

        Cursor cursor = db.query(
                FeedReaderContract_Animales_Vacas.FeedEntry.TABLE_NAME,
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
     * Obtiene todos los datos de la tabla y los guarda en objetos de la clase Vaca
     * @return	Listado de Vaca
     */
    public ArrayList<Vaca> getDatosObjetos(){
        /*
         * Obtiene el repositorio de la BD en modo lectura
         */
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        /*
         * Orden de los resultados
         */
        String sortOrder = FeedReaderContract_Animales_Vacas.FeedEntry.CROTAL + " ASC";

        Cursor cursor = db.query(
                FeedReaderContract_Animales_Vacas.FeedEntry.TABLE_NAME,/* Tabla para la consulta */
                null,       /* Columnas que devuelve la consulta */
                null,       /* Columnas de la clausula WHERE */
                null,       /* Valores de la clausula WHERE */
                null,       /* Forma de agrupación de los resultados */
                null,
                sortOrder   /* Orden */
        );

        ArrayList<Vaca> items = new ArrayList<>(getObjetos(cursor));

        cursor.close();
        db.close();

        return items;
    }
    /**
     * Obtiene los datos de la tabla con el crotal pasado
     * @param crotal    Crotal que deben tener los objetos obtenidos
     * @return          Listado de Vaca cuyo crotal es igual al pasado
     */
    public ArrayList<Vaca> getDatosObjeto(String crotal){
        /*
         * Obtiene el repositorio de la BD en modo lectura
         */
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sortOrder = FeedReaderContract_Animales_Vacas.FeedEntry.CROTAL + " DESC";

        /*
         * Filtro
         */
        String selection = FeedReaderContract_Animales_Vacas.FeedEntry.CROTAL + " = ?";
        String[] selectionArgs = { crotal };

        Cursor cursor = db.query(
                FeedReaderContract_Animales_Vacas.FeedEntry.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        ArrayList<Vaca> items = new ArrayList<>(getObjetos(cursor));

        cursor.close();
        db.close();

        return items;
    }
    ArrayList<String> getCrotales(Cursor cursor){
        ArrayList<String> items = new ArrayList<>();
        while(cursor.moveToNext()) {
            String item = cursor.getString(cursor.getColumnIndex(FeedReaderContract_Animales_Vacas.FeedEntry.CROTAL));
            items.add(item);
        }
        return items;
    }
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
    /**
     * Elimina los datos del objeto pasaso de la base de datos
     * @param vaca	Objeto a borrar
     */
    public void borrarDatos(Vaca vaca){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String selection = FeedReaderContract_Animales_Vacas.FeedEntry._ID + " LIKE ?";

        String[] selectionArgs = new String[1];
        selectionArgs[0] = vaca.getIdString();

        db.delete(FeedReaderContract_Animales_Vacas.FeedEntry.TABLE_NAME, selection, selectionArgs);

        Log.println(Log.INFO, "Vaca eliminada ", vaca.toString());
    }

    /**
     * Actualiza los datos del objeto pasado en la base de datos
     * @param vaca	Objeto a actualizar
     */
    public void actualizarBD(Vaca vaca){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract_Animales_Vacas.FeedEntry.CROTAL, vaca.getCrotal());
        values.put(FeedReaderContract_Animales_Vacas.FeedEntry.FECHA_EMBARAZO, vaca.getFechaEmbarazoString());
        values.put(FeedReaderContract_Animales_Vacas.FeedEntry.TERNERO, vaca.getTernerosString());


        String selection = FeedReaderContract_Animales_Vacas.FeedEntry._ID + " LIKE ?";
        String[] selectionArgs = {vaca.getIdString()};

        int count = db.update(
                FeedReaderContract_Animales_Vacas.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        db.close();

        Log.println(Log.INFO, "Vaca actualizada ", vaca.toString() + count + " filas actualizadas");
    }
}
