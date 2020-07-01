package com.lidong.abcdefg.main;

import android.Manifest;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Process;
import android.telephony.TelephonyManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.lidong.abcdefg.R;
import com.lidong.abcdefg.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private ViewPager2 mPager;
    private MainPageAdapter mPageAdapter;
    List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = findViewById(R.id.main_pager);

        mFragments = new ArrayList<>();
        mFragments.add(new MainFragment());
        mFragments.add(new DetailFragment());

        mPageAdapter = new MainPageAdapter(this, mFragments);
        mPager.setAdapter(mPageAdapter);
    }

    class MainPageAdapter extends FragmentStateAdapter {

        private List<Fragment> fragments;

        public MainPageAdapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> fragments) {
            super(fragmentActivity);
            this.fragments = fragments;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments.get(position);
        }

        @Override
        public int getItemCount() {
            return fragments.size();
        }
    }
}
