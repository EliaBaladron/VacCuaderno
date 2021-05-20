package com.example.GridAdapter.Animal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ClasesVO.Animales.Vaca;
import com.example.prueba03.R;

import java.util.ArrayList;

/**
 * @author Elia Baladrón Peral
 */
public class GridAdapter_Vaca extends BaseAdapter {
    private final Context context;
    private final ArrayList<Vaca> arrayList;

    /**
     * Constructor que recibe el contexto y un listado con los objetos a mostrar
     * @param cont	Contexto
     * @param al	Listado de objetos
     */
    public GridAdapter_Vaca(Context cont, ArrayList<Vaca> al){
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
     * @param parent		p
     * @return	Devuelve la vista creada
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_animal, null);
        }

        TextView textViewNombre = (TextView)convertView.findViewById(R.id.animal_nombre);
        textViewNombre.setText(arrayList.get(position).getAnimal().getNombre());
        textViewNombre.setVisibility(View.VISIBLE);

        TextView textViewCrotal = (TextView)convertView.findViewById(R.id.animal_crotal);
        textViewCrotal.setText(arrayList.get(position).getAnimal().getCrotal());
        textViewCrotal.setVisibility(View.VISIBLE);

        TextView textViewSexo = (TextView)convertView.findViewById(R.id.animal_sexo);
        textViewSexo.setText(arrayList.get(position).getAnimal().getSexo());
        textViewSexo.setVisibility(View.VISIBLE);

        TextView textViewRebano = (TextView)convertView.findViewById(R.id.animal_idReb);
        textViewRebano.setText(arrayList.get(position).getAnimal().getIdReb());
        textViewRebano.setVisibility(View.VISIBLE);

        return convertView;
    }
}
