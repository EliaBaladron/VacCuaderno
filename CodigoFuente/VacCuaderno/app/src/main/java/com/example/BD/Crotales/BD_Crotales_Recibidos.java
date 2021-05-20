package com.example.BD.Crotales;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ClasesVO.Otros.Crotal;
import com.example.prueba03.R;
import com.example.FeedReader.FeedReaderContract_Crotales_Recibidos;
import com.example.FeedReader.FeedReaderDbHelper_VacApp;

import java.util.ArrayList;

/**
 * @author Elia Baladrón Peral
 */
public class BD_Crotales_Recibidos extends AppCompatActivity {
    FeedReaderDbHelper_VacApp dbHelper;

    /**
     * Constructor que obtiene la base de datos
     * @param dbHelperVacApp	Base de datos
     */
    public BD_Crotales_Recibidos(FeedReaderDbHelper_VacApp dbHelperVacApp){
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
        insertarDatos(new Crotal("ES 1234 1234 1238", 1));
        insertarDatos(new Crotal("ES 1234 1234 1239", 2));
    }

    /**
     * Método que inserta un nuevo objeto
     * @param crotal	Objeto a guardar en la BD
     * @return			Objeto creado, con el ID automático añadido
     */
    public Crotal insertarDatos(Crotal crotal){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract_Crotales_Recibidos.FeedEntry.CROTAL, crotal.getCrotal());
        values.put(FeedReaderContract_Crotales_Recibidos.FeedEntry.UNIDADES, crotal.getUnidades());

        long newRowId = db.insert(
                FeedReaderContract_Crotales_Recibidos.FeedEntry.TABLE_NAME,
                null,
                values
        );
        crotal.setId(newRowId);
        db.close();

        Log.println(Log.INFO, "Crotal insertado", crotal.toString());

        return crotal;
    }
    /**
     * Obtiene todos los datos de la tabla y los guarda en objetos de la clase Crotal
     * @return	Listado de los crotales
     */
    public ArrayList<Crotal> getDatosObjetos(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sortOrder = FeedReaderContract_Crotales_Recibidos.FeedEntry.CROTAL + " ASC";

        Cursor cursor = db.query(
                FeedReaderContract_Crotales_Recibidos.FeedEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                sortOrder
        );

        ArrayList<Crotal> items = getObjetos(cursor);

        cursor.close();
        db.close();

        return items;
    }
    
    ArrayList<Crotal> getObjetos(Cursor cursor){
        ArrayList<Crotal> items = new ArrayList<>();
        while(cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndex(FeedReaderContract_Crotales_Recibidos.FeedEntry._ID));
            String crotal = cursor.getString(cursor.getColumnIndex(FeedReaderContract_Crotales_Recibidos.FeedEntry.CROTAL));
            int unidades = cursor.getInt(cursor.getColumnIndex(FeedReaderContract_Crotales_Recibidos.FeedEntry.UNIDADES));

            items.add(new Crotal(id, crotal, unidades));
        }
        return items;
    }

    /**
     * Elimina los datos del objeto pasaso de la base de datos
     * @param crotal	Objeto a borrar
     */
    public void borrarDatos(Crotal crotal){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String selection = FeedReaderContract_Crotales_Recibidos.FeedEntry._ID + " LIKE ?";

        String[] selectionArgs = new String[1];
        selectionArgs[0] = crotal.getIdString();

        db.delete(FeedReaderContract_Crotales_Recibidos.FeedEntry.TABLE_NAME, selection, selectionArgs);

        Log.println(Log.INFO, "Crotal eliminado", crotal.toString());
    }

    /**
     * Actualiza los datos del objeto pasado en la base de datos
     * @param crotal	Objeto a actualizar
     */
    public void actualizarBD(Crotal crotal){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract_Crotales_Recibidos.FeedEntry.CROTAL, crotal.getCrotal());
        values.put(FeedReaderContract_Crotales_Recibidos.FeedEntry.UNIDADES, crotal.getUnidades());

        String selection = FeedReaderContract_Crotales_Recibidos.FeedEntry._ID + " LIKE ?";
        String[] selectionArgs = {crotal.getIdString()};

        int count = db.update(
                FeedReaderContract_Crotales_Recibidos.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        db.close();

        Log.println(Log.INFO, "Crotal actualizado", crotal.toString() + count + " filas actualizadas");
    }
}
