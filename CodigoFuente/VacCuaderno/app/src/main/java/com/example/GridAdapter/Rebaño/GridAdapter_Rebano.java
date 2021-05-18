package com.example.GridAdapter.Rebaño;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ClasesVO.Otros.Rebaño;
import com.example.prueba03.R;

import java.util.ArrayList;

public class GridAdapter_Rebano extends BaseAdapter {
    private Context context;
    private ArrayList<Rebaño> arrayList;

    public GridAdapter_Rebano(Context cont, ArrayList<Rebaño> al){
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
            convertView = layoutInflater.inflate(R.layout.item_rebano, null);
        }

        TextView textViewNombre = (TextView)convertView.findViewById(R.id.rebano_nombre);
        textViewNombre.setText(arrayList.get(position).getNombre());
        textViewNombre.setVisibility(View.VISIBLE);

        TextView textViewId = (TextView)convertView.findViewById(R.id.rebano_id);
        textViewId.setText(Long.toString(arrayList.get(position).getId()));
        textViewId.setVisibility(View.VISIBLE);

        return convertView;
    }
}
