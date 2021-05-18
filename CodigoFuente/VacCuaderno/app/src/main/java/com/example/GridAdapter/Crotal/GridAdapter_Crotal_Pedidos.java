package com.example.GridAdapter.Crotal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ClasesVO.Otros.Crotal;
import com.example.prueba03.R;

import java.util.ArrayList;

public class GridAdapter_Crotal_Pedidos extends BaseAdapter {
    private Context context;
    private ArrayList<Crotal> arrayList;

    public GridAdapter_Crotal_Pedidos(Context cont, ArrayList<Crotal> al){
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
            convertView = layoutInflater.inflate(R.layout.item_crotal, null);
        }

        TextView textViewCrotal = (TextView)convertView.findViewById(R.id.crotal_crotal);
        textViewCrotal.setText(arrayList.get(position).getCrotal());
        textViewCrotal.setVisibility(View.VISIBLE);

        TextView textViewUnidades = (TextView)convertView.findViewById(R.id.crotal_unidades);
        textViewUnidades.setText(arrayList.get(position).getUnidadesString());
        textViewUnidades.setVisibility(View.VISIBLE);

        return convertView;
    }
}
