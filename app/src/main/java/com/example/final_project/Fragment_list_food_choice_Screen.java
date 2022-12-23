package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class Fragment_list_food_choice_Screen extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list_food_choice);
        tabLayout = findViewById(R.id.TabLayout_food_list);
        viewPager = findViewById(R.id.ViewPager_food_list);

        Intent intent=getIntent();
        String loaifood=intent.getExtras().getString("loaifood");

        FoodChoiceViewPagerAdapterFragment foodChoiceViewPagerAdapterFragment = new FoodChoiceViewPagerAdapterFragment(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(foodChoiceViewPagerAdapterFragment);
        tabLayout.setupWithViewPager(viewPager);

    }
}