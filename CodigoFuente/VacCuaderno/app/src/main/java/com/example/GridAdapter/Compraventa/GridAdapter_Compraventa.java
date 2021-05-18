package com.example.GridAdapter.Compraventa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ClasesVO.CompraVenta.CompraVenta;
import com.example.prueba03.R;

import java.util.ArrayList;

public class GridAdapter_Compraventa extends BaseAdapter {
    private Context context;
    private ArrayList arrayList;
    
    public <C extends CompraVenta>GridAdapter_Compraventa(Context cont, ArrayList<C> al){
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
            convertView = layoutInflater.inflate(R.layout.item_compraventa, null);
        }

        CompraVenta compraVenta = (CompraVenta) arrayList.get(position);

        TextView textViewCrotal = (TextView)convertView.findViewById(R.id.compraventa_crotal);
        textViewCrotal.setText(compraVenta.getCrotal());
        textViewCrotal.setVisibility(View.VISIBLE);

        TextView textViewFecha = (TextView)convertView.findViewById(R.id.compraventa_fecha);
        textViewFecha.setText(compraVenta.getFechaString());
        textViewFecha.setVisibility(View.VISIBLE);

        TextView textViewPrecio = (TextView)convertView.findViewById(R.id.compraventa_precio);
        textViewPrecio.setText(compraVenta.getPrecioString());
        textViewPrecio.setVisibility(View.VISIBLE);

        return convertView;
    }
}
