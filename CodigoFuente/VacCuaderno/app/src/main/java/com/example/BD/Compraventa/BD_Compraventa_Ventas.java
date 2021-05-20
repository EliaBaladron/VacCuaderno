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
     }

     /**
      * Método invocado en la creación del gestor de la tabla
      * @param savedInstanceState   sis
      */
     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.tab_animales);
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
      * Método que inserta un nuevo objeto
      * @param venta	Objeto a guardar en la BD
      * @return			Objeto creado, con el ID automático añadido
      */
     public Venta insertarDatos(Venta venta){
         SQLiteDatabase db = dbHelper.getWritableDatabase();

         ContentValues values = new ContentValues();
         values.put(FeedReaderContract_Compraventa_Venta.FeedEntry.CROTAL, venta.getCrotal());
         values.put(FeedReaderContract_Compraventa_Venta.FeedEntry.PRECIO, venta.getPrecio());
         values.put(FeedReaderContract_Compraventa_Venta.FeedEntry.FECHA, venta.getFecha().toString());

         long newRowId = db.insert(
                 FeedReaderContract_Compraventa_Venta.FeedEntry.TABLE_NAME,
                 null,
                 values
         );
         venta.setId(newRowId);
         db.close();

         Log.println(Log.INFO, "Venta insertada ", venta.toString());

         return venta;
     }

     /**
      * Obtiene todos los datos de la tabla y los guarda en objetos de la clase Compra
      * @return	Listado de Compra
      */
     public ArrayList<Venta> getDatosObjetos(){
         SQLiteDatabase db = dbHelper.getReadableDatabase();

         String sortOrder = FeedReaderContract_Compraventa_Venta.FeedEntry.CROTAL + " ASC";

         Cursor cursor = db.query(
                 FeedReaderContract_Compraventa_Venta.FeedEntry.TABLE_NAME,
                 null,
                 null,
                 null,
                 null,
                 null,
                 sortOrder
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
      * Elimina los datos del objeto pasaso de la base de datos
      * @param venta	Objeto a borrar
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
      * Actualiza los datos del objeto pasado en la base de datos
      * @param venta	Objeto a actualizar
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
