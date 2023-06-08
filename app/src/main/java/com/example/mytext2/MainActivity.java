package com.example.mytext2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.mytext2.fragment.HomeFragment;
import com.example.mytext2.fragment.NewsFragment;
import com.example.mytext2.fragment.ServiesFragment;
import com.example.mytext2.fragment.UserFragment;
import com.example.mytext2.fragment.danjianFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements HomeFragment.Jump{

    private ViewPager mMainVp;
    private TabLayout mMainTb;
    private String[] title = {"首页","全部服务","智慧党建","新闻","个人中心"};
    private List<Fragment> list = new ArrayList<>();
    private My my;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainVp = findViewById(R.id.main_vp);
        mMainTb = findViewById(R.id.main_tb);


        setVp();

    }

    private void setVp(){
        list.add(new HomeFragment());
        list.add(new ServiesFragment());
        list.add(new danjianFragment());
        list.add(new NewsFragment());
        list.add(new UserFragment());

        my = new My(getSupportFragmentManager());

        mMainVp.setAdapter(my);

        mMainTb.setupWithViewPager(mMainVp);

        for (int i =0;i<title.length;i++){
            TabLayout.Tab tab = mMainTb.getTabAt(i);
            tab.setCustomView(my.getView(i));
        }

        mMainTb.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                ImageView imageView = view.findViewById(R.id.icon_img);
                TextView textView  = view.findViewById(R.id.icon_name);
                String name = textView.getText().toString();
                if (name.equals("首页")){
                    imageView.setImageResource(R.drawable.home_press);
                }else if(name.equals("全部服务")){
                    imageView.setImageResource(R.drawable.home_press);
                }else if(name.equals("智慧党建")){
                    imageView.setImageResource(R.drawable.home_press);
                }else if(name.equals("新闻")){
                    imageView.setImageResource(R.drawable.home_press);
                }else if(name.equals("个人中心")){
                    imageView.setImageResource(R.drawable.home_press);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                ImageView imageView = view.findViewById(R.id.icon_img);
                TextView textView  = view.findViewById(R.id.icon_name);
                String name = textView.getText().toString();
                if (name.equals("首页")){
                    imageView.setImageResource(R.drawable.home);
                }else if(name.equals("全部服务")){
                    imageView.setImageResource(R.drawable.home);
                }else if(name.equals("智慧党建")){
                    imageView.setImageResource(R.drawable.home);
                }else if(name.equals("新闻")){
                    imageView.setImageResource(R.drawable.home);
                }else if(name.equals("个人中心")){
                    imageView.setImageResource(R.drawable.home);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void jumpTo(String name) {
        if (name.equals("今日要闻")){
            mMainVp.setCurrentItem(3);
        }
    }


    class My extends FragmentPagerAdapter {

        public My(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            if (position==0){
                return list.get(position);
            }else if (position==1){
                return list.get(position);
            }else if (position==2){
                return list.get(position);
            }else if (position==3){
                return list.get(position);
            }else if (position==4){
                return list.get(position);
            }
            return list.get(0);
        }

        @Override
        public int getCount() {
            return title.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }


        public View getView(int i){
            View view = View.inflate(MainActivity.this,R.layout.main_icon_item,null);
            ImageView imageView = view.findViewById(R.id.icon_img);
            TextView textView = view.findViewById(R.id.icon_name);
            textView.setText(title[i]);
            textView.setTextColor(mMainTb.getTabTextColors());
            if (i==0){
                imageView.setImageResource(R.drawable.home_press);
            }else {
                imageView.setImageResource(R.drawable.home);
            }
            return view;
        }
    }

}