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

public class GridAdapter_Vaca extends BaseAdapter {
    private Context context;
    private ArrayList<Vaca> arrayList;

    public GridAdapter_Vaca(Context cont, ArrayList<Vaca> al){
        this.context = cont;
        this.arrayList = al;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
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

        TextView textViewRebaño = (TextView)convertView.findViewById(R.id.animal_idReb);
        textViewRebaño.setText(arrayList.get(position).getAnimal().getIdReb());
        textViewRebaño.setVisibility(View.VISIBLE);

        return convertView;
    }
}
