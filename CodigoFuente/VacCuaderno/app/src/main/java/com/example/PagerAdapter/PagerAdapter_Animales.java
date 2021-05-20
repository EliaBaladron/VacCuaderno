package com.example.PagerAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.Fragment.Animales.*;
import com.example.MainActivity;

/**
 * @author Elia Baladrón Peral
 */
public class PagerAdapter_Animales extends FragmentStatePagerAdapter {

    private final int tabsNumber;
    MainActivity main;

    public PagerAdapter_Animales(@NonNull FragmentManager fm, int behavior, int tabs, MainActivity main) {
        super(fm, behavior);
        this.tabsNumber = tabs;
        this.main = main;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Fragment_Animales(main);
            case 1:
                return new Fragment_Animales_Vacas(main);
            case 2:
                return new Fragment_Animales_Terneros(main);
            case 3:
                return new Fragment_Animales_Toros(main);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.tabsNumber;
    }
}
