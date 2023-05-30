package com.example.myapp4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapp4.Fragment.HomeFragment;
import com.example.myapp4.Fragment.NewsFragment;
import com.example.myapp4.Fragment.PersonFragment;
import com.example.myapp4.Fragment.ServiesFragment;
import com.example.myapp4.Fragment.SharedFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String[] tital = {"首页","新闻","服务","分享","个人"};
    private int unselect = R.drawable.home;
    private int selected =R.drawable.home_press ;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragments = new ArrayList<>();
//    private Myr myr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.M_vp);
        tabLayout = findViewById(R.id.M_tb);

        setvp();



    }



    private void setvp(){
        fragments.add(new HomeFragment());
        fragments.add(new NewsFragment());
        fragments.add(new ServiesFragment());
        fragments.add(new SharedFragment());
        fragments.add(new PersonFragment());

        Myr myr = new Myr(getSupportFragmentManager());

        viewPager.setAdapter(myr);

        tabLayout.setupWithViewPager(viewPager);

        for (int i=0;i<tital.length;i++){
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            Log.i("view",myr.getview(i).toString());
            tab.setCustomView(myr.getview(i));
        }



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                ImageView imageView = view.findViewById(R.id.mainactivity_tb_item_img);
                TextView textView = view.findViewById(R.id.mainactivity_tb_item_name);
                String n = textView.getText().toString();
                if (n.equals("首页")){
                    imageView.setImageResource(selected);
                }else if(n.equals("新闻")){
                    imageView.setImageResource(selected);
                }else if(n.equals("服务")){
                    imageView.setImageResource(selected);
                }else if(n.equals("分享")){
                    imageView.setImageResource(selected);
                }else if(n.equals("个人")){
                    imageView.setImageResource(selected);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                ImageView imageView = view.findViewById(R.id.mainactivity_tb_item_img);
                TextView textView = view.findViewById(R.id.mainactivity_tb_item_name);
                String n = textView.getText().toString();
                if (n.equals("首页")){
                    imageView.setImageResource(unselect);
                }else if(n.equals("新闻")){
                    imageView.setImageResource(unselect);
                }else if(n.equals("服务")){
                    imageView.setImageResource(unselect);
                }else if(n.equals("分享")){
                    imageView.setImageResource(unselect);
                }else if(n.equals("个人")){
                    imageView.setImageResource(unselect);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private class Myr extends FragmentPagerAdapter{

        public Myr(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            if (position==0){
                return fragments.get(position);
            }else if (position==1){
                return fragments.get(position);
            }else if (position==2){
                return fragments.get(position);
            }else if (position==3){
                return fragments.get(position);
            }else if (position==4){
                return fragments.get(position);
            }
            return fragments.get(0);
        }

        @Override
        public int getCount() {
            return tital.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tital[position];
        }

        private View getview(int ii){
            View view = View.inflate(MainActivity.this,R.layout.mainactivity_tb_item,null);
            ImageView imageView = view.findViewById(R.id.mainactivity_tb_item_img);
            TextView textView = view.findViewById(R.id.mainactivity_tb_item_name);
            textView.setText(tital[ii]);
            textView.setTextColor(tabLayout.getTabTextColors());
            imageView.setImageResource(unselect);
            return view;
        }
    }
}