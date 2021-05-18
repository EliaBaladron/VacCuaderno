package com.example.PagerAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.Fragment.Veterinario.Fragment_Veterinario_Controles;
import com.example.Fragment.Veterinario.Fragment_Veterinario_Visitas;
import com.example.MainActivity;

public class PagerAdapter_Veterinario extends FragmentStatePagerAdapter {

    private int tabsNUmber;
    MainActivity main;

    public PagerAdapter_Veterinario(@NonNull FragmentManager fm, int behavior, int tabs, MainActivity main) {
        super(fm, behavior);
        this.tabsNUmber = tabs;
        this.main = main;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Fragment_Veterinario_Controles(main);
            case 1:
                return new Fragment_Veterinario_Visitas(main);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.tabsNUmber;
    }
}
