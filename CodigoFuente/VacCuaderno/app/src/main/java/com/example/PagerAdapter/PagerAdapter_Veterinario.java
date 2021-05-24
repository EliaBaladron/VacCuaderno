package com.example.PagerAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.Fragment.Veterinario.Fragment_Veterinario_Controles;
import com.example.Fragment.Veterinario.Fragment_Veterinario_Visitas;
import com.example.MainActivity;

/**
 * @author Elia Baladrón Peral
 */
public class PagerAdapter_Veterinario extends FragmentStatePagerAdapter {

    private final int tabsNumber;

    public PagerAdapter_Veterinario(@NonNull FragmentManager fm, int behavior, int tabs) {
        super(fm, behavior);
        this.tabsNumber = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Fragment_Veterinario_Controles();
            case 1:
                return new Fragment_Veterinario_Visitas();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.tabsNumber;
    }
}
