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
    }

    /**
     * Método invocado en la creación del gestor de la tabla
     * @param savedInstanceState	s
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
        //Vacas
        insertarDatos(new Animal("Nombre1", "ES 1234 1234 1231", "2010-04-11", "H", "Rubia gallega", "", "1"));
        insertarDatos(new Animal("Nombre2", "ES 1234 1234 1232", "2010-04-12", "H", "Avileña", "", "2"));
        insertarDatos(new Animal("Nombre3", "ES 1234 1234 1233", "2010-04-13", "H", "Asturiana", "", "3"));
        //Terneros
        insertarDatos(new Animal("Nombre4", "ES 1234 1234 1234", "2020-04-14", "H", "Rubia gallega", "ES 1234 1234 1231", "4"));
        insertarDatos(new Animal("Nombre5", "ES 1234 1234 1235", "2020-04-15", "M", "Avileña", "ES 1234 1234 1232", "5"));
        insertarDatos(new Animal("Nombre6", "ES 1234 1234 1236", "2020-04-16", "H", "Asturiana", "ES 1234 1234 1233", "6"));
        //Toros
        insertarDatos(new Animal("Nombre7", "ES 1234 1234 1237", "2010-04-17", "M", "Limusin", "", "7"));
        insertarDatos(new Animal("Nombre8", "ES 1234 1234 1238", "2010-04-18", "M", "Berrenda", "", "8"));
        insertarDatos(new Animal("Nombre9", "ES 1234 1234 1239", "2010-04-19", "M", "Cachena", "", "9"));
    }


    /**
     * Método que inserta un nuevo objeto
     * @param animal	Objeto a guardar en la BD
     * @return			Objeto creado, con el ID automático añadido
     */
    public Animal insertarDatos(Animal animal){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract_Animales.FeedEntry.NOMBRE, animal.getNombre());
        values.put(FeedReaderContract_Animales.FeedEntry.CROTAL, animal.getCrotal());
        values.put(FeedReaderContract_Animales.FeedEntry.FECHA_NAC, animal.getFechaNacString());
        values.put(FeedReaderContract_Animales.FeedEntry.SEXO, animal.getSexo());
        values.put(FeedReaderContract_Animales.FeedEntry.RAZA, animal.getRaza());
        values.put(FeedReaderContract_Animales.FeedEntry.COD_MADRE, animal.getCodMadre());
        values.put(FeedReaderContract_Animales.FeedEntry.ID_REB, animal.getIdReb());

        long newRowId = db.insert(
                /** Nombre de la tabla */
                FeedReaderContract_Animales.FeedEntry.TABLE_NAME,
                /**
                 * indica al framework qué hacer en caso de que ContentValues esté vacío(si no se incluye ningún valor con put)
                 * si se especifica el nombre de una columna, el framework inserta una fila y establece el valor de esa columna como nulo
                 * Si se especifica null, el framework no insertará una fila cuando no haya valores
                 */
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
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sortOrder = FeedReaderContract_Animales.FeedEntry.CROTAL + " ASC";

        Cursor cursor = db.query(
                FeedReaderContract_Animales.FeedEntry.TABLE_NAME,
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
     * Obtiene todos los datos de la tabla y los guarda en objetos de la clase Animal
     * @return	Listado de Animal
     */
    public ArrayList<Animal> getDatosObjetos(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sortOrder = FeedReaderContract_Animales.FeedEntry.CROTAL + " ASC";

        Cursor cursor = db.query(
                FeedReaderContract_Animales.FeedEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                sortOrder
        );

        ArrayList<Animal> items = getObjetos(cursor);

        cursor.close();
        db.close();

        return items;
    }
    /**
     * Obtiene los datos de la tabla con el crotal pasado
     * @param crotal    Crotal que deben tener los objetos obtenidos
     * @return          Listado de Animal cuyo crotal es igual al pasado
     */
    public ArrayList<Animal> getDatosObjeto(String crotal){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sortOrder = FeedReaderContract_Animales.FeedEntry.CROTAL + " DESC";

        String selection = FeedReaderContract_Animales.FeedEntry.CROTAL + " = ?";
        String[] selectionArgs = { crotal };

        Cursor cursor = db.query(
                FeedReaderContract_Animales.FeedEntry.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        ArrayList<Animal> items = getObjetos(cursor);

        cursor.close();
        db.close();

        return items;
    }
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
                FeedReaderContract_Animales.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        db.close();

        Log.println(Log.INFO, "Animal actualizado ", animal.toString() + count + " filas actualizadas");
    }
}
