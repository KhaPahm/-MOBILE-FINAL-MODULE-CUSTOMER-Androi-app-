package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapterFragment extends FragmentStatePagerAdapter {
    public ViewPagerAdapterFragment(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HomePage();
            case 1:
                return new CartPage();
            case 2:
                return new SettingPage();
            case 3:
                return new InformationPage();
            default:
                return new HomePage();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
