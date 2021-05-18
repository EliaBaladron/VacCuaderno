 package com.example.BD.Animales;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ClasesVO.Animales.Animal;
import com.example.prueba03.R;
import com.example.FeedReader.FeedReaderDbHelper_VacApp;
import com.example.FeedReader.FeedReaderContract_Animales;

import java.util.ArrayList;

/**
 * @author Elia Baladrón Peral
 */
public class BD_Animales extends AppCompatActivity {

    FeedReaderDbHelper_VacApp dbHelper;

    /**
     * Constructor que obtiene la base de datos
     * @param dbHelperVacApp	Base de datos
     */
    public BD_Animales(FeedReaderDbHelper_VacApp dbHelperVacApp){
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
        //Vacas
        insertarDatos(new Animal("Nombre1", "ES 1234 1234 1231", "2010-04-11", "H", "Raza1", "", "1"));
        insertarDatos(new Animal("Nombre2", "ES 1234 1234 1232", "2010-04-12", "H", "Raza2", "", "2"));
        insertarDatos(new Animal("Nombre3", "ES 1234 1234 1233", "2010-04-13", "H", "Raza3", "", "3"));
        //Terneros
        insertarDatos(new Animal("Nombre4", "ES 1234 1234 1234", "2020-04-14", "H", "Raza1", "ES 1234 1234 1231", "4"));
        insertarDatos(new Animal("Nombre5", "ES 1234 1234 1235", "2020-04-15", "M", "Raza2", "ES 1234 1234 1232", "5"));
        insertarDatos(new Animal("Nombre6", "ES 1234 1234 1236", "2020-04-16", "H", "Raza3", "ES 1234 1234 1233", "6"));
        //Toros
        insertarDatos(new Animal("Nombre7", "ES 1234 1234 1237", "2010-04-17", "M", "Raza1", "", "7"));
        insertarDatos(new Animal("Nombre8", "ES 1234 1234 1238", "2010-04-18", "M", "Raza2", "", "8"));
        insertarDatos(new Animal("Nombre9", "ES 1234 1234 1239", "2010-04-19", "M", "Raza3", "", "9"));
    }


    /**
     * Método que inserta un nuevo objeto
     * @param animal	Objeto a guardar en la BD
     * @return			Objeto creado, con el ID automático añadido
     */
    public Animal insertarDatos(Animal animal){
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract_Animales.FeedEntry.NOMBRE, animal.getNombre());
        values.put(FeedReaderContract_Animales.FeedEntry.CROTAL, animal.getCrotal());
        values.put(FeedReaderContract_Animales.FeedEntry.FECHA_NAC, animal.getFechaNacString());
        values.put(FeedReaderContract_Animales.FeedEntry.SEXO, animal.getSexo());
        values.put(FeedReaderContract_Animales.FeedEntry.RAZA, animal.getRaza());
        values.put(FeedReaderContract_Animales.FeedEntry.COD_MADRE, animal.getCodMadre());
        values.put(FeedReaderContract_Animales.FeedEntry.ID_REB, animal.getIdReb());

        // Insert the new row, returning the primary key value of the new row
        //insert() devuelve el ID de la fila recién creada o -1 si hubo un error al insertar los datos.
        long newRowId = db.insert(
                //nombre de la tabla
                FeedReaderContract_Animales.FeedEntry.TABLE_NAME,
                //indica al framework qué hacer en caso de que ContentValues esté vacío(si no se incluye ningún valor con put)
                //si se especifica el nombre de una columna, el framework inserta una fila y establece el valor de esa columna como nulo
                //Si se especifica null, el framework no insertará una fila cuando no haya valores
                null,
                values
        );
        animal.setId(newRowId);
        db.close();

        Log.println(Log.INFO, "Animal insertado ", animal.toString());

        return animal;
    }
    /**
     * Obtiene los datos de los crotales de todos los animales de la tabla
     * @return	Listado de los crotales
     */
    public ArrayList<String> getDatosCrotales(){
        //Obtiene el repositorio de la BD en modo lectura
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // How you want the results sorted in the resulting Cursor
        String sortOrder = FeedReaderContract_Animales.FeedEntry.CROTAL + " ASC";

        Cursor cursor = db.query(
                FeedReaderContract_Animales.FeedEntry.TABLE_NAME,   // The table to query
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
    /**
     * Obtiene todos los datos de la tabla y los guarda en objetos de la clase Animal
     * @return	Listado de Animal
     */
    public ArrayList<Animal> getDatosObjetos(){
        //Obtiene el repositorio de la BD en modo lectura
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // How you want the results sorted in the resulting Cursor
        String sortOrder = FeedReaderContract_Animales.FeedEntry.CROTAL + " ASC";

        Cursor cursor = db.query(
                FeedReaderContract_Animales.FeedEntry.TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        ArrayList<Animal> items = getObjetos(cursor);

        cursor.close();
        db.close();

        return items;
    }
    /**
     * 
     * @param crotal
     * @return
     */
    public ArrayList<Animal> getDatosObjeto(String crotal){
        //Obtiene el repositorio de la BD en modo lectura
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // How you want the results sorted in the resulting Cursor
        String sortOrder = FeedReaderContract_Animales.FeedEntry.CROTAL + " DESC";

        String selection = FeedReaderContract_Animales.FeedEntry.CROTAL + " = ?";
        String[] selectionArgs = { crotal };

        Cursor cursor = db.query(
                FeedReaderContract_Animales.FeedEntry.TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        ArrayList<Animal> items = getObjetos(cursor);

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
                FeedReaderContract_Animales.FeedEntry.CROTAL,
                FeedReaderContract_Animales.FeedEntry.FECHA_NAC,
                FeedReaderContract_Animales.FeedEntry.SEXO,
                FeedReaderContract_Animales.FeedEntry.RAZA,
                FeedReaderContract_Animales.FeedEntry.COD_MADRE,
                FeedReaderContract_Animales.FeedEntry.ID_REB
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = FeedReaderContract_Animales.FeedEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = { "My Title" };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = FeedReaderContract_Animales.FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor cursor = db.query(
                FeedReaderContract_Animales.FeedEntry.TABLE_NAME,   // The table to query
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
        ArrayList<Long> items = new ArrayList<Long>();
        while(cursor.moveToNext()) {
            long item = cursor.getLong(
                    cursor.getColumnIndexOrThrow(FeedReaderContract_Animales.FeedEntry._ID));
            items.add(item);
        }
        return items;
    }*/
    ArrayList<String> getCrotales(Cursor cursor){
        ArrayList<String> items = new ArrayList<>();
        while(cursor.moveToNext()) {
            String item = cursor.getString(
                    cursor.getColumnIndex(FeedReaderContract_Animales.FeedEntry.CROTAL));
            items.add(item);
        }
        return items;
    }
    ArrayList<Animal> getObjetos(Cursor cursor){
        ArrayList<Animal> items = new ArrayList<>();
        while(cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(FeedReaderContract_Animales.FeedEntry._ID));
            String nombre = cursor.getString(cursor.getColumnIndex(FeedReaderContract_Animales.FeedEntry.NOMBRE));
            String crotal = cursor.getString(cursor.getColumnIndex(FeedReaderContract_Animales.FeedEntry.CROTAL));
            String fechaNacimiento = cursor.getString(cursor.getColumnIndex(FeedReaderContract_Animales.FeedEntry.FECHA_NAC));
            String sexo = cursor.getString(cursor.getColumnIndex(FeedReaderContract_Animales.FeedEntry.SEXO));
            String raza = cursor.getString(cursor.getColumnIndex(FeedReaderContract_Animales.FeedEntry.RAZA));
            String codMadre = cursor.getString(cursor.getColumnIndex(FeedReaderContract_Animales.FeedEntry.COD_MADRE));
            String idRebano = cursor.getString(cursor.getColumnIndex(FeedReaderContract_Animales.FeedEntry.ID_REB));

            items.add(new Animal(id, nombre, crotal, fechaNacimiento, sexo, raza, codMadre, idRebano));
        }
        return items;
    }
    /*void borrarDatos(String[] columnas, String[] datos){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Define 'where' part of query.
        //String selection = columnas + " LIKE ?";
        String selection = "";
        for(int i = 0; i<columnas.length; i++){
            if(i!=0)
                selection+=", ";
            selection += columnas[i];
        }
        selection += " LIKE ";
        for(int i = 0; i<columnas.length; i++){
            if(i!=0)
                selection+=", ";
            selection += "?";
        }

        // Specify arguments in placeholder order.
        String[] selectionArgs = datos;
        // Issue SQL statement.
        int deletedRows = db.delete(FeedReaderContract_Animales.FeedEntry.TABLE_NAME, selection, selectionArgs);
    }*/
    /**
     * Elimina los datos del objeto pasaso de la base de datos
     * @param animal	Objeto a borrar
     */
    public void borrarDatos(Animal animal){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String selection = FeedReaderContract_Animales.FeedEntry._ID + " LIKE ?";

        String[] selectionArgs = new String[1];
        selectionArgs[0] = animal.getIdString();

        db.delete(FeedReaderContract_Animales.FeedEntry.TABLE_NAME, selection, selectionArgs);

        Log.println(Log.INFO, "Animal eliminado ", animal.toString());
    }


    /*void actualizarBD(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // New value for one column
        String title = "MyNewTitle";
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract_Animales.FeedEntry.COLUMN_NAME_TITLE, title);

        // Which row to update, based on the title
        String selection = FeedReaderContract_Animales.FeedEntry.COLUMN_NAME_TITLE + " LIKE ?";
        String[] selectionArgs = { "MyOldTitle" };

        int count = db.update(
                //FeedReaderDbHelper.FeedEntry.TABLE_NAME,
                FeedReaderContract_Animales.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
    }*/
    /**
     * Actualiza los datos del objeto pasado en la base de datos
     * @param animal	Objeto a actualizar
     */
    public void actualizarBD(Animal animal){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract_Animales.FeedEntry.NOMBRE, animal.getNombre());
        values.put(FeedReaderContract_Animales.FeedEntry.CROTAL, animal.getCrotal());
        values.put(FeedReaderContract_Animales.FeedEntry.FECHA_NAC, animal.getFechaNacString());
        values.put(FeedReaderContract_Animales.FeedEntry.SEXO, animal.getSexo());
        values.put(FeedReaderContract_Animales.FeedEntry.RAZA, animal.getRaza());
        values.put(FeedReaderContract_Animales.FeedEntry.COD_MADRE, animal.getCodMadre());
        values.put(FeedReaderContract_Animales.FeedEntry.ID_REB, animal.getIdReb());

        String selection = FeedReaderContract_Animales.FeedEntry._ID + " LIKE ?";
        String[] selectionArgs = {animal.getIdString()};

        int count = db.update(
                //FeedReaderDbHelper.FeedEntry.TABLE_NAME,
                FeedReaderContract_Animales.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        db.close();

        Log.println(Log.INFO, "Animal actualizado ", animal.toString() + count + " filas actualizadas");
    }
}
