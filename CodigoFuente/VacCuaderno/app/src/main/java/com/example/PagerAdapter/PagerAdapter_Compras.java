package com.example.PagerAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.Fragment.Compraventa.Fragment_Compraventa_Compras;
import com.example.MainActivity;

/**
 * @author Elia Baladrón Peral
 */
public class PagerAdapter_Compras extends FragmentStatePagerAdapter {

    private final int tabsNumber;
    MainActivity main;

    public PagerAdapter_Compras(@NonNull FragmentManager fm, int behavior, int tabs, MainActivity main) {
        super(fm, behavior);
        this.tabsNumber = tabs;
        this.main = main;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new Fragment_Compraventa_Compras(main);
        }
        return null;
    }

    @Override
    public int getCount() {
        return this.tabsNumber;
    }
}
