package com.openclassrooms.entrevoisins.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.openclassrooms.entrevoisins.Controllers.Fragments.FavorisFragment;
import com.openclassrooms.entrevoisins.Controllers.Fragments.NeighbourFragment;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     *
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return NeighbourFragment.newInstance();
            case 1:
                return FavorisFragment.newInstance();
            default:
                return null;
        }
    }

    /**
     * get the number of pages
     *
     * @return
     */
    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Voisins";
            case 1:
                return "Favoris";
            default:
                return null;
        }
    }

}