package com.example.BD.Rebano;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ClasesVO.Otros.Rebano;
import com.example.prueba03.R;
import com.example.FeedReader.FeedReaderDbHelper_VacApp;
import com.example.FeedReader.FeedReaderContract_Rebano;

import java.util.ArrayList;

/**
 * @author Elia Baladrón Peral
 */
public class BD_Rebanos extends AppCompatActivity {
    FeedReaderDbHelper_VacApp dbHelper;

    /**
     * Constructor que obtiene la base de datos
     * @param dbHelperVacApp	Base de datos
     */
    public BD_Rebanos(FeedReaderDbHelper_VacApp dbHelperVacApp){
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
        insertarDatos(new Rebano("Granja"));
        insertarDatos(new Rebano("Campo"));
    }

    /**
     * Método que inserta un nuevo objeto
     * @param rebano	Objeto a guardar en la BD
     * @return			Objeto creado, con el ID automático añadido
     */
    public Rebano insertarDatos(Rebano rebano){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract_Rebano.FeedEntry.NOMBRE, rebano.getNombre());

        long newRowId = db.insert(
                //nombre de la tabla
                FeedReaderContract_Rebano.FeedEntry.TABLE_NAME,
                null,
                values
        );
        rebano.setId(newRowId);
        db.close();

        Log.println(Log.INFO, "Rebaño insertado", rebano.toString());

        return rebano;
    }
    /**
     * Obtiene todos los datos de la tabla y los guarda en objetos de la clase Rebaño
     * @return	Listado de los rebaños
     */
    public ArrayList<Rebano> getDatosObjetos(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sortOrder = FeedReaderContract_Rebano.FeedEntry.NOMBRE + " ASC";

        Cursor cursor = db.query(
                FeedReaderContract_Rebano.FeedEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                sortOrder
        );

        ArrayList<Rebano> items = getObjetos(cursor);

        cursor.close();
        db.close();

        return items;
    }
    ArrayList<Rebano> getObjetos(Cursor cursor){
        ArrayList<Rebano> items = new ArrayList<>();
        while(cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(FeedReaderContract_Rebano.FeedEntry._ID));
            String nombre = cursor.getString(cursor.getColumnIndex(FeedReaderContract_Rebano.FeedEntry.NOMBRE));

            items.add(new Rebano(id, nombre));
        }
        return items;
    }

    /**
     * Elimina los datos del objeto pasaso de la base de datos
     * @param rebano	Objeto a borrar
     */
    public void borrarDatos(Rebano rebano){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String selection = FeedReaderContract_Rebano.FeedEntry._ID + " LIKE ?";

        String[] selectionArgs = new String[1];
        selectionArgs[0] = rebano.getIdString();

        db.delete(FeedReaderContract_Rebano.FeedEntry.TABLE_NAME, selection, selectionArgs);

        Log.println(Log.INFO, "Rebaño eliminado", rebano.toString());
    }

    /**
     * Actualiza los datos del objeto pasado en la base de datos
     * @param rebano	Objeto a actualizar
     */
    public void actualizarBD(Rebano rebano){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract_Rebano.FeedEntry.NOMBRE, rebano.getNombre());

        String selection = FeedReaderContract_Rebano.FeedEntry._ID + " LIKE ?";
        String[] selectionArgs = {rebano.getIdString()};

        int count = db.update(
                FeedReaderContract_Rebano.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        db.close();

        Log.println(Log.INFO, "Rebaño actualizado", rebano.toString() + count + " filas actualizadas");
    }
}
