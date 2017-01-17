package com.geniusnine.android.geniusninelifecare.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geniusnine.android.geniusninelifecare.Fragments.Tabs.Patient_Consultion;
import com.geniusnine.android.geniusninelifecare.Fragments.Tabs.Patient_Forum;
import com.geniusnine.android.geniusninelifecare.Fragments.Tabs.Patient_History;
import com.geniusnine.android.geniusninelifecare.Fragments.Tabs.Patient_Labs;
import com.geniusnine.android.geniusninelifecare.Fragments.Tabs.Patient_Reminders;
import com.geniusnine.android.geniusninelifecare.R;

/**
 * Created by Dev on 12-01-2017.
 */

public class Patient_Home extends Fragment {
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 5;
    private int[] tabIcons = {
            R.drawable.ic_supervisor_account_white_24dp,
            R.drawable.ic_date_range_white_24dp,
            R.drawable.ic_history_white_24dp,
            R.drawable.ic_store_white_24dp,
            R.drawable.ic_question_answer_white_24dp
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
        View x = inflater.inflate(R.layout.tab_layout, null);
        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);

        /**
         *Set an Apater for the View Pager
         */
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
                setupTabIcons();
                tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            }
        });

        return x;

    }
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);
    }
    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Patient_Consultion();
                case 1:
                    return new Patient_Reminders();
                case 2:
                    return new Patient_History();
                case 3:
                    return new Patient_Labs();
                case 4:
                    return new Patient_Forum();

            }
            return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }

        /**
         * This method returns the title of the tab according to the position.
         */

  /*      @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:
                    return "Consultaion";
                case 1:
                    return "Reminders";
                case 2:
                    return "History";
                case 3:
                    return "Labs";
                case 4:
                    return "Forums";
            }
            return null;
        }*/
    }
}