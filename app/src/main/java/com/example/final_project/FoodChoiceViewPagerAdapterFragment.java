package com.example.final_project;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FoodChoiceViewPagerAdapterFragment extends FragmentStatePagerAdapter{
    public FoodChoiceViewPagerAdapterFragment(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    private String[] tabTitle = new String[]{"Cơm", "Bánh mì", "Nước giải khát", "Bún - Mì"};
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Rice_Fragment();
            case 1:
                return new Banhmi_Fragment();
            case 2:
                return new Drink_Fragment();
            case 3:
                return new Noodles_Fragment();
            default:
                return new Rice_Fragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle[position];
    }

}
