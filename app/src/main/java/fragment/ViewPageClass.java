package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.example.foushi.myapplication.R;

import java.util.List;

import fragment.TabClothes;
import fragment.TabWeather;

public class ViewPageClass extends Fragment {

    private PagerSlidingTabStrip tabs;
    private static ViewPager viewPager;

    public ViewPageClass() {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        List<Fragment> fragments = getChildFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                fragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View x = inflater.inflate(R.layout.viewpager, container, false);
        tabs = (PagerSlidingTabStrip) x.findViewById(R.id.tab_strip);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
        tabs.post(new Runnable() {
            @Override
            public void run() {
                tabs.setViewPager(viewPager);
            }
        });
        tabs.setTextColorResource(R.color.white);
        return x;
    }

    public class MyAdapter extends FragmentPagerAdapter { // Tab Adapter

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new TabWeather();
                case 1:
                    return new TabClothes();
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
                    return getResources().getString(R.string.Temps);
                case 1:
                    return getResources().getString(R.string.Vetement);
            }
            return null;
        }
    }

}