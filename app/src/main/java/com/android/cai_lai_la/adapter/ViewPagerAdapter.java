package com.android.cai_lai_la.adapter;

import com.android.cai_lai_la.fragment.NavCartFragment;
import com.android.cai_lai_la.fragment.NavClassFragment;
import com.android.cai_lai_la.fragment.NavHomeFragment;
import com.android.cai_lai_la.fragment.NavMeFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * 使用Fragment导航栏的Adapter
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;

    public ViewPagerAdapter(@NonNull FragmentManager fm){
        super(fm);
        fragments = new ArrayList<>();
        fragments.clear();
        fragments.add(NavHomeFragment.newInstance());
        fragments.add(NavClassFragment.newInstance());
        fragments.add(NavCartFragment.newInstance());
        fragments.add(NavMeFragment.newInstance());
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
