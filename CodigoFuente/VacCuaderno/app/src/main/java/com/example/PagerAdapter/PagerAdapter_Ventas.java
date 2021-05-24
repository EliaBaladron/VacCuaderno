package com.example.PagerAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.Fragment.Compraventa.Fragment_Compraventa_Ventas;
import com.example.Fragment.Compraventa.Fragment_Compraventa_Ventas_Planificadas;
import com.example.Fragment.Compraventa.Fragment_Compraventa_Ventas_Realizadas;
import com.example.MainActivity;

/**
 * @author Elia Baladrón Peral
 */
public class PagerAdapter_Ventas extends FragmentStatePagerAdapter {

    private final int tabsNumber;

    public PagerAdapter_Ventas(@NonNull FragmentManager fm, int behavior, int tabs) {
        super(fm, behavior);
        this.tabsNumber = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Fragment_Compraventa_Ventas();
            case 1:
                return new Fragment_Compraventa_Ventas_Planificadas();
            case 2:
                return new Fragment_Compraventa_Ventas_Realizadas();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.tabsNumber;
    }
}
