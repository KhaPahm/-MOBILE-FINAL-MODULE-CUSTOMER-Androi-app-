package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    //private BottomNavigationView bottomNavigationView;
    //private ViewPager viewPager;
    //private TabLayout tabLayout;

    Button get_started;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);         // mặc định là intro

        //Man hinh get started.
        get_started = findViewById(R.id.get_started);
        get_started.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Login_Screen.class));
            }
        });


        //Fragment_list_food_choice
        /*tabLayout = findViewById(R.id.TabLayout_food_list);
        viewPager = findViewById(R.id.ViewPager_food_list);

        FoodChoiceViewPagerAdapterFragment foodChoiceViewPagerAdapterFragment = new FoodChoiceViewPagerAdapterFragment(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(foodChoiceViewPagerAdapterFragment);
        tabLayout.setupWithViewPager(viewPager);*/

        //Button_navigationview
        /*viewPager = findViewById(R.id.ViewPager);
        bottomNavigationView = findViewById(R.id.Bottom_navigation);
        setUpViewPager();

        bottomNavigationView.setOnItemSelectedListener((item) -> {
            switch (item.getItemId()){
                case R.id.action_home:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.action_cart:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.action_setting:
                    viewPager.setCurrentItem(2);
                    break;
                case R.id.action_account:
                    viewPager.setCurrentItem(3);
                    break;
            }
            return true;
        });


    }

    private void setUpViewPager(){
        ViewPagerAdapterFragment viewPagerAdapterFragment = new ViewPagerAdapterFragment(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapterFragment);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.action_cart).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.action_setting).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.action_account).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/
    }
}