 package com.example.BD.Compraventa;

 import android.content.ContentValues;
 import android.database.Cursor;
 import android.database.sqlite.SQLiteDatabase;
 import android.os.Bundle;
 import android.util.Log;

 import androidx.appcompat.app.AppCompatActivity;

 import com.example.ClasesVO.CompraVenta.Compra;
 import com.example.FeedReader.FeedReaderContract_Compraventa_Compra;
 import com.example.FeedReader.FeedReaderDbHelper_VacApp;
 import com.example.prueba03.R;

 import java.util.ArrayList;

/**
 * @author Elia Baladrón Peral
 */
 public class BD_Compraventa_Compras extends AppCompatActivity {
     FeedReaderDbHelper_VacApp dbHelper;

     /**
     * Constructor que obtiene la base de datos
     * @param dbHelperVacApp	Base de datos
      */
     public BD_Compraventa_Compras(FeedReaderDbHelper_VacApp dbHelperVacApp){
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
         insertarDatos(new Compra("ES 1234 1234 1231", 1200.0d, "2018-04-11"));
         insertarDatos(new Compra("ES 1234 1234 1232", 1500.0d, "2018-04-13"));
         insertarDatos(new Compra("ES 1234 1234 1235", 700.0d, "2020-04-12"));
     }


     /**
      * Método que inserta un nuevo objeto
      * @param compra	Objeto a guardar en la BD
      * @return			Objeto creado, con el ID automático añadido
      */
     public Compra insertarDatos(Compra compra){
         SQLiteDatabase db = dbHelper.getWritableDatabase();

         ContentValues values = new ContentValues();
         values.put(FeedReaderContract_Compraventa_Compra.FeedEntry.CROTAL, compra.getCrotal());
         values.put(FeedReaderContract_Compraventa_Compra.FeedEntry.PRECIO, compra.getPrecio());
         values.put(FeedReaderContract_Compraventa_Compra.FeedEntry.FECHA, compra.getFecha().toString());

         long newRowId = db.insert(
                 FeedReaderContract_Compraventa_Compra.FeedEntry.TABLE_NAME,
                 null,
                 values
         );
         compra.setId(newRowId);
         db.close();

         Log.println(Log.INFO, "Compra insertada ", compra.toString());

         return compra;
     }

     /**
      * Obtiene todos los datos de la tabla y los guarda en objetos de la clase Compra
      * @return	Listado de Compra
      */
     public ArrayList<Compra> getDatosObjetos(){
         SQLiteDatabase db = dbHelper.getReadableDatabase();

         String sortOrder = FeedReaderContract_Compraventa_Compra.FeedEntry.CROTAL + " ASC";

         Cursor cursor = db.query(
                 FeedReaderContract_Compraventa_Compra.FeedEntry.TABLE_NAME,
                 null,
                 null,
                 null,
                 null,
                 null,
                 sortOrder
         );

         ArrayList<Compra> items = getObjetos(cursor);

         cursor.close();
         db.close();

         return items;
     }
     
     ArrayList<Compra> getObjetos(Cursor cursor){
         ArrayList<Compra> items = new ArrayList<>();
         while(cursor.moveToNext()) {
             long id = cursor.getLong(cursor.getColumnIndexOrThrow(FeedReaderContract_Compraventa_Compra.FeedEntry._ID));
             String crotal = cursor.getString(cursor.getColumnIndex(FeedReaderContract_Compraventa_Compra.FeedEntry.CROTAL));
             double precio = cursor.getDouble(cursor.getColumnIndex(FeedReaderContract_Compraventa_Compra.FeedEntry.PRECIO));
             String fecha = cursor.getString(cursor.getColumnIndex(FeedReaderContract_Compraventa_Compra.FeedEntry.FECHA));

             items.add(new Compra(id, crotal, precio, fecha));
         }

         return items;
     }

     /**
      * Elimina los datos del objeto pasaso de la base de datos
      * @param compra	Objeto a borrar
      */
     public void borrarDatos(Compra compra){
         SQLiteDatabase db = dbHelper.getWritableDatabase();

         String selection = FeedReaderContract_Compraventa_Compra.FeedEntry._ID + " LIKE ?";

         String[] selectionArgs = new String[1];
         selectionArgs[0] = compra.getIdString();

         db.delete(FeedReaderContract_Compraventa_Compra.FeedEntry.TABLE_NAME, selection, selectionArgs);

         Log.println(Log.INFO, "Compra eliminada ", compra.toString());
     }

     /**
      * Actualiza los datos del objeto pasado en la base de datos
      * @param compra	Objeto a actualizar
      */
     public void actualizarBD(Compra compra){
         SQLiteDatabase db = dbHelper.getWritableDatabase();

         ContentValues values = new ContentValues();
         values.put(FeedReaderContract_Compraventa_Compra.FeedEntry.CROTAL, compra.getCrotal());
         values.put(FeedReaderContract_Compraventa_Compra.FeedEntry.PRECIO, compra.getPrecio());
         values.put(FeedReaderContract_Compraventa_Compra.FeedEntry.FECHA, compra.getFecha().toString());

         String selection = FeedReaderContract_Compraventa_Compra.FeedEntry._ID + " LIKE ?";
         String[] selectionArgs = {compra.getIdString()};

         int count = db.update(
                 FeedReaderContract_Compraventa_Compra.FeedEntry.TABLE_NAME,
                 values,
                 selection,
                 selectionArgs
         );

         db.close();

         Log.println(Log.INFO, "Compra actualizada ", compra.toString() + count + " filas actualizadas");
     }
 }
