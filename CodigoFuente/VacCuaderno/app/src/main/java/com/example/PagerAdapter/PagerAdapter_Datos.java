package com.example.PagerAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.Fragment.Datos.Fragment_Datos_Destete;
import com.example.Fragment.Datos.Fragment_Datos_Dinero;
import com.example.Fragment.Datos.Fragment_Datos_Exportar;
import com.example.Fragment.Datos.Fragment_Datos_Importar;
import com.example.MainActivity;

/**
 * @author Elia Baladrón Peral
 */
public class PagerAdapter_Datos extends FragmentStatePagerAdapter {

    private final int tabsNumber;
    MainActivity main;

    public PagerAdapter_Datos(@NonNull FragmentManager fm, int behavior, int tabs, MainActivity main) {
        super(fm, behavior);
        this.tabsNumber = tabs;
        this.main = main;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Fragment_Datos_Exportar(main);
            case 1:
                return new Fragment_Datos_Importar(main);
            case 2:
                return new Fragment_Datos_Destete(main);
            case 3:
                return new Fragment_Datos_Dinero(main);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.tabsNumber;
    }
}
