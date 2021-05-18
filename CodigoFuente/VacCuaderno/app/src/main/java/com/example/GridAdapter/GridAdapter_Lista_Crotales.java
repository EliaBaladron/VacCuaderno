package com.example.GridAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ClasesVO.Animales.Animal;
import com.example.prueba03.R;

import java.util.ArrayList;

public class GridAdapter_Lista_Crotales extends BaseAdapter {
    private Context context;
    private ArrayList<String> arrayList;

    public GridAdapter_Lista_Crotales(Context cont, ArrayList<String> al){
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
            convertView = layoutInflater.inflate(R.layout.item_lista_crotales, null);
        }

        TextView textViewCrotal = (TextView)convertView.findViewById(R.id.lista_crotal);
        textViewCrotal.setText(arrayList.get(position));
        textViewCrotal.setVisibility(View.VISIBLE);

        return convertView;
    }
}
