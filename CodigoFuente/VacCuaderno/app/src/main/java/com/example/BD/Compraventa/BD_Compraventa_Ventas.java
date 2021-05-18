 package com.example.BD.Compraventa;

 import android.content.ContentValues;
 import android.database.Cursor;
 import android.database.sqlite.SQLiteDatabase;
 import android.os.Bundle;
 import android.util.Log;

 import androidx.appcompat.app.AppCompatActivity;

 import com.example.ClasesVO.CompraVenta.Venta;
 import com.example.FeedReader.FeedReaderContract_Compraventa_Venta;
 import com.example.FeedReader.FeedReaderDbHelper_VacApp;
 import com.example.prueba03.R;

 import java.util.ArrayList;

/**
 * @author Elia Baladrón Peral
 */
 public class BD_Compraventa_Ventas extends AppCompatActivity {
     FeedReaderDbHelper_VacApp dbHelper;

     /**
     * Constructor que obtiene la base de datos
     * @param dbHelperVacApp	Base de datos
      */
     public BD_Compraventa_Ventas(FeedReaderDbHelper_VacApp dbHelperVacApp){
         this.dbHelper = dbHelperVacApp;

         //insertarDatos();
     }

     /**
      * 
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
      * 
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
         insertarDatos(new Venta("ES 1234 1234 1231", 1200.0d, "2018-04-11"));
         insertarDatos(new Venta("ES 1234 1234 1232", 1500.0d, "2018-04-13"));
         insertarDatos(new Venta("ES 1234 1234 1235", 700.0d, "2020-04-12"));
     }


     /**
      * 
      * @param venta
      * @return
      */
     public Venta insertarDatos(Venta venta){
         // Gets the data repository in write mode
         SQLiteDatabase db = dbHelper.getWritableDatabase();

         // Create a new map of values, where column names are the keys
         ContentValues values = new ContentValues();
         values.put(FeedReaderContract_Compraventa_Venta.FeedEntry.CROTAL, venta.getCrotal());
         values.put(FeedReaderContract_Compraventa_Venta.FeedEntry.PRECIO, venta.getPrecio());
         values.put(FeedReaderContract_Compraventa_Venta.FeedEntry.FECHA, venta.getFecha().toString());

         // Insert the new row, returning the primary key value of the new row
         //insert() devuelve el ID de la fila recién creada o -1 si hubo un error al insertar los datos.
         long newRowId = db.insert(
                 //nombre de la tabla
                 FeedReaderContract_Compraventa_Venta.FeedEntry.TABLE_NAME,
                 //indica al framework qué hacer en caso de que ContentValues esté vacío(si no se incluye ningún valor con put)
                 //si se especifica el nombre de una columna, el framework inserta una fila y establece el valor de esa columna como nulo
                 //Si se especifica null, el framework no insertará una fila cuando no haya valores
                 null,
                 values
         );
         venta.setId(newRowId);
         db.close();

         Log.println(Log.INFO, "Venta insertada ", venta.toString());

         return venta;
     }

     /**
      * 
      * @return
      */
     public ArrayList<Venta> getDatosObjetos(){
         //Obtiene el repositorio de la BD en modo lectura
         SQLiteDatabase db = dbHelper.getReadableDatabase();

         // How you want the results sorted in the resulting Cursor
         String sortOrder = FeedReaderContract_Compraventa_Venta.FeedEntry.CROTAL + " ASC";

         Cursor cursor = db.query(
                 FeedReaderContract_Compraventa_Venta.FeedEntry.TABLE_NAME,   // The table to query
                 null,             // The array of columns to return (pass null to get all)
                 null,              // The columns for the WHERE clause
                 null,          // The values for the WHERE clause
                 null,                   // don't group the rows
                 null,                   // don't filter by row groups
                 sortOrder               // The sort order
         );

         ArrayList<Venta> items = getObjetos(cursor);

         cursor.close();
         db.close();

         return items;
     }
     ArrayList<Venta> getObjetos(Cursor cursor){
         ArrayList<Venta> items = new ArrayList<>();
         while(cursor.moveToNext()) {
             long id = cursor.getLong(cursor.getColumnIndexOrThrow(FeedReaderContract_Compraventa_Venta.FeedEntry._ID));
             String crotal = cursor.getString(cursor.getColumnIndex(FeedReaderContract_Compraventa_Venta.FeedEntry.CROTAL));
             double precio = cursor.getDouble(cursor.getColumnIndex(FeedReaderContract_Compraventa_Venta.FeedEntry.PRECIO));
             String fecha = cursor.getString(cursor.getColumnIndex(FeedReaderContract_Compraventa_Venta.FeedEntry.FECHA));

             items.add(new Venta(id, crotal, precio, fecha));
         }

         return items;
     }

     /**
      * 
      * @param venta
      */
     public void borrarDatos(Venta venta){
         SQLiteDatabase db = dbHelper.getWritableDatabase();

         String selection = FeedReaderContract_Compraventa_Venta.FeedEntry._ID + " LIKE ?";

         String[] selectionArgs = new String[1];
         selectionArgs[0] = venta.getIdString();

         db.delete(FeedReaderContract_Compraventa_Venta.FeedEntry.TABLE_NAME, selection, selectionArgs);

         Log.println(Log.INFO, "Venta eliminada ", venta.toString());
     }

     /**
      * 
      * @param venta
      */
     public void actualizarBD(Venta venta){
         SQLiteDatabase db = dbHelper.getWritableDatabase();

         ContentValues values = new ContentValues();
         values.put(FeedReaderContract_Compraventa_Venta.FeedEntry.CROTAL, venta.getCrotal());
         values.put(FeedReaderContract_Compraventa_Venta.FeedEntry.PRECIO, venta.getPrecio());
         values.put(FeedReaderContract_Compraventa_Venta.FeedEntry.FECHA, venta.getFecha().toString());

         String selection = FeedReaderContract_Compraventa_Venta.FeedEntry._ID + " LIKE ?";
         String[] selectionArgs = {venta.getIdString()};

         int count = db.update(
                 FeedReaderContract_Compraventa_Venta.FeedEntry.TABLE_NAME,
                 values,
                 selection,
                 selectionArgs
         );

         db.close();

         Log.println(Log.INFO, "Venta actualizada ", venta.toString() + count + " filas actualizadas");
     }
 }
