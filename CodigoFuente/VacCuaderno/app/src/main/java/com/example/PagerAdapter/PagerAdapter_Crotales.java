package com.example.PagerAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.Fragment.Crotales.Fragment_Crotales_Faltan;
import com.example.Fragment.Crotales.Fragment_Crotales_Pedidos;
import com.example.Fragment.Crotales.Fragment_Crotales_Recibidos;
import com.example.Fragment.Crotales.Fragment_Crotales_SinPoner;
import com.example.MainActivity;

/**
 * @author Elia Baladrón Peral
 */
public class PagerAdapter_Crotales extends FragmentStatePagerAdapter {

    private final int tabsNumber;

    public PagerAdapter_Crotales(@NonNull FragmentManager fm, int behavior, int tabs) {
        super(fm, behavior);
        this.tabsNumber = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Fragment_Crotales_Faltan();
            case 1:
                return new Fragment_Crotales_Pedidos();
            case 2:
                return new Fragment_Crotales_Recibidos();
            case 3:
                return new Fragment_Crotales_SinPoner();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.tabsNumber;
    }
}
