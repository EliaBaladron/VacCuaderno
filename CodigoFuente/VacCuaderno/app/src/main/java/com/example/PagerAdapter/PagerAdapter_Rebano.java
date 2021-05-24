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

    public PagerAdapter_Rebano(@NonNull FragmentManager fm, int behavior, int tabs) {
        super(fm, behavior);
        this.tabsNumber = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new Fragment_Rebano();
        }
        return null;
    }

    @Override
    public int getCount() {
        return this.tabsNumber;
    }
}
