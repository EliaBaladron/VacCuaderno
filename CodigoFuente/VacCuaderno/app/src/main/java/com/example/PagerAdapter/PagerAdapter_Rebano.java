package com.example.PagerAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.Fragment.Rebano.Fragment_Rebano;
import com.example.MainActivity;

/**
 * @author Elia Baladrón Peral
 */
public class PagerAdapter_Rebano extends FragmentStatePagerAdapter {

    private final int tabsNumber;
    MainActivity main;

    public PagerAdapter_Rebano(@NonNull FragmentManager fm, int behavior, int tabs, MainActivity main) {
        super(fm, behavior);
        this.tabsNumber = tabs;
        this.main = main;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new Fragment_Rebano(main);
        }
        return null;
    }

    @Override
    public int getCount() {
        return this.tabsNumber;
    }
}
