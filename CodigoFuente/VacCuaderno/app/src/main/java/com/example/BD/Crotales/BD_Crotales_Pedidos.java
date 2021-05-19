package com.example.BD.Crotales;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ClasesVO.Otros.Crotal;
import com.example.prueba03.R;
import com.example.FeedReader.FeedReaderContract_Crotales_Pedidos;
import com.example.FeedReader.FeedReaderDbHelper_VacApp;

import java.util.ArrayList;

/**
 * @author Elia Baladrón Peral
 */
public class BD_Crotales_Pedidos extends AppCompatActivity {
    FeedReaderDbHelper_VacApp dbHelper;

    /**
     * Constructor que obtiene la base de datos
     * @param dbHelperVacApp	Base de datos
     */
    public BD_Crotales_Pedidos(FeedReaderDbHelper_VacApp dbHelperVacApp){
        this.dbHelper = dbHelperVacApp;

        //insertarDatos();
    }

    /**
     * Método invocado en la creación del gestor de la tabla
     * @param savedInstanceState	
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_animales);

        //Para acceder a la base de datos, crea una instancia de la subclase de SQLiteOpenHelper
        //dbHelper = new FeedReaderDbHelper_VacApp(getApplicationContext());
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
        insertarDatos(new Crotal("ES 1234 1234 1233", 1));
        insertarDatos(new Crotal("ES 1234 1234 1237", 2));
    }

    /**
     * Método que inserta un nuevo objeto
     * @param crotal	Objeto a guardar en la BD
     * @return			Objeto creado, con el ID automático añadido
     */
    public Crotal insertarDatos(Crotal crotal){
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract_Crotales_Pedidos.FeedEntry.CROTAL, crotal.getCrotal());
        values.put(FeedReaderContract_Crotales_Pedidos.FeedEntry.UNIDADES, crotal.getUnidades());

        // Insert the new row, returning the primary key value of the new row
        //insert() devuelve el ID de la fila recién creada o -1 si hubo un error al insertar los datos.
        long newRowId = db.insert(
                //nombre de la tabla
                FeedReaderContract_Crotales_Pedidos.FeedEntry.TABLE_NAME,
                //indica al framework qué hacer en caso de que ContentValues esté vacío(si no se incluye ningún valor con put)
                //si se especifica el nombre de una columna, el framework inserta una fila y establece el valor de esa columna como nulo
                //Si se especifica null, el framework no insertará una fila cuando no haya valores
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
        //Obtiene el repositorio de la BD en modo lectura
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // How you want the results sorted in the resulting Cursor
        String sortOrder = FeedReaderContract_Crotales_Pedidos.FeedEntry.CROTAL + " ASC";

        Cursor cursor = db.query(
                FeedReaderContract_Crotales_Pedidos.FeedEntry.TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        ArrayList<Crotal> items = getObjetos(cursor);

        cursor.close();
        db.close();

        return items;
    }
    ArrayList<Crotal> getObjetos(Cursor cursor){
        ArrayList<Crotal> items = new ArrayList<>();
        while(cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndex(FeedReaderContract_Crotales_Pedidos.FeedEntry._ID));
            String crotal = cursor.getString(cursor.getColumnIndex(FeedReaderContract_Crotales_Pedidos.FeedEntry.CROTAL));
            int unidad = cursor.getInt(cursor.getColumnIndex(FeedReaderContract_Crotales_Pedidos.FeedEntry.UNIDADES));

            items.add(new Crotal(id, crotal, unidad));
        }
        return items;
    }

    /**
     * Elimina los datos del objeto pasaso de la base de datos
     * @param crotal	Objeto a borrar
     */
    public void borrarDatos(Crotal crotal){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String selection = FeedReaderContract_Crotales_Pedidos.FeedEntry._ID + " LIKE ?";

        String[] selectionArgs = new String[1];
        selectionArgs[0] = crotal.getIdString();

        db.delete(FeedReaderContract_Crotales_Pedidos.FeedEntry.TABLE_NAME, selection, selectionArgs);

        Log.println(Log.INFO, "Crotal eliminado", crotal.toString());
    }

    /**
     * Actualiza los datos del objeto pasado en la base de datos
     * @param crotal	Objeto a actualizar
     */
    public void actualizarBD(Crotal crotal){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract_Crotales_Pedidos.FeedEntry.CROTAL, crotal.getCrotal());
        values.put(FeedReaderContract_Crotales_Pedidos.FeedEntry.UNIDADES, crotal.getUnidades());

        String selection = FeedReaderContract_Crotales_Pedidos.FeedEntry._ID + " LIKE ?";
        String[] selectionArgs = {crotal.getIdString()};

        int count = db.update(
                FeedReaderContract_Crotales_Pedidos.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        db.close();

        Log.println(Log.INFO, "Crotal actualizado", crotal.toString() + count + " filas actualizadas");
    }
}