package com.codeego.auchadoseperdidos.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.codeego.auchadoseperdidos.R;
import com.codeego.auchadoseperdidos.ui.fragments.LostPetsFragment;

/**
 * Created by Gustavo on 2/9/16.
 */
public class MainPagesAdapter extends FragmentPagerAdapter {

    Context mContext;

    public MainPagesAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return LostPetsFragment.newInstance();

            case 1:
                return LostPetsFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return mContext.getString(R.string.tab_lost);

            case 1:
                return mContext.getString(R.string.tab_found);

            default:
                return super.getPageTitle(position);
        }
    }
}
