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

public class GridAdapter_Visitas extends BaseAdapter {
    private Context context;
    private ArrayList<Visitas> arrayList;

    public GridAdapter_Visitas(Context cont, ArrayList<Visitas> al){
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
