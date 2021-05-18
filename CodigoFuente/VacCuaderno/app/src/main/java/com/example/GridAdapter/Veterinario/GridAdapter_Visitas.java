package com.example.GridAdapter.Veterinario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ClasesVO.Veterinario.Visitas;
import com.example.prueba03.R;

import java.util.ArrayList;

/**
 * @author Elia Baladrón Peral
 */
public class GridAdapter_Visitas extends BaseAdapter {
    private Context context;
    private ArrayList<Visitas> arrayList;

    /**
     * Constructor que recibe el contexto y un listado con los objetos a mostrar
     * @param cont	Contexto
     * @param al	Listado de objetos
     */
    public GridAdapter_Visitas(Context cont, ArrayList<Visitas> al){
        this.context = cont;
        this.arrayList = al;
    }

    /**
     * Devuelve el número de objetos a mostrar
     * @return	Cantidad de objetos a mostrar
     */
    @Override
    public int getCount() {
        return arrayList.size();
    }

    /**
     * Devuelve el objeto de la posicion pasada
     * @param position	Posicion del articulo
     * @return			Objeto de la posicion introducida
     */
    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    /**
     * Devuelve el id de la posicion pasada
     * @param position	Posicion del articulo
     * @return	id de la posicion
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Crear y devuelve la vista para la posición introducida
     * @param position		Posición del array para obtener los datos
     * @param convertView	Vista que se va a crear e introducir los nuevos datos
     * @param parent		
     * @return	Devuelve la vista creada
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_veterinario, null);
        }

        TextView textViewTitulo = (TextView)convertView.findViewById(R.id.veterinario_titulo);
        textViewTitulo.setText(arrayList.get(position).getTitulo());
        textViewTitulo.setVisibility(View.VISIBLE);

        TextView textViewFecha = (TextView)convertView.findViewById(R.id.veterinario_fecha);
        textViewFecha.setText(arrayList.get(position).getFechaString());
        textViewFecha.setVisibility(View.VISIBLE);

        TextView textViewOtro = (TextView)convertView.findViewById(R.id.veterinario_otro);
        textViewOtro.setText(arrayList.get(position).getPrecioString());
        textViewOtro.setVisibility(View.VISIBLE);

        return convertView;
    }
}
