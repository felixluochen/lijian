package com.example.myapp4.IconActivity.Waimai;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.myapp4.Fragment.PersonFragment;
import com.example.myapp4.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Waimai_Detial_Activity extends AppCompatActivity {
    private String id;
    private String[] title = {"点菜","评论"};
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> list = new ArrayList<>();
    private Myppp myppp;
    private Diancai_fragment fragment;
    private PopupWindow popupWindow;
    private Map<Integer,Bean_cai.Data> pracar = new HashMap<>();
    private Button car,pay;
    private  Adapter_waimai_car_rv adapter_waimai_car_rv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waimai_detial_activity);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");


        car = findViewById(R.id.waimai_detial_car);
        pay = findViewById(R.id.waimai_detial_jiesuan);
        tabLayout = findViewById(R.id.waimai_detial_tb);
        viewPager = findViewById(R.id.waimai_detial_vp);

        seT();


        fragment = (Diancai_fragment) list.get(0);



        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("content",fragment.getValue().toString());
                pracar.clear();
                pracar.putAll(fragment.getValue());
                setPopup();
            }
        });

    }

    private void setPopup(){
         View view = LayoutInflater.from(Waimai_Detial_Activity.this).inflate(R.layout.waimai_popup,null,false);
         View view1 = LayoutInflater.from(Waimai_Detial_Activity.this).inflate(R.layout.waimai_detial_activity,null,false);

         RecyclerView recyclerView = view.findViewById(R.id.popup_rv);

         adapter_waimai_car_rv = new Adapter_waimai_car_rv();
         recyclerView.setAdapter(adapter_waimai_car_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        Log.i("content",pracar.toString());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter_waimai_car_rv.setData(pracar);

                adapter_waimai_car_rv.getData(new Adapter_waimai_car_rv.Mapp() {
                    @Override
                    public void getMapp(Map<Integer, Bean_cai.Data> pracar) {
                        fragment.setValue(pracar);
                    }
                });
            }
        });

        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,true);
//        popupWindow.setAnimationStyle();
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.showAtLocation(view1, Gravity.BOTTOM,0,0);
    }

    public String getIdd(){
        return id;
    }

    private  void  seT(){
          list.add(new Diancai_fragment());
          list.add(new pinglun_fragment());

          myppp = new Myppp(getSupportFragmentManager());

          viewPager.setAdapter(myppp);

          tabLayout.setupWithViewPager(viewPager);

    }

    class Myppp extends FragmentPagerAdapter{

        public Myppp(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            if (position == 0){
                return list.get(position);
            }else if (position==1){
                return list.get(position);
            }
            return list.get(0);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }

}
