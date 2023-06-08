package com.example.mytext2.fragment;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.mytext2.R;
import com.example.mytext2.bean.Bean_newtitle;
import com.example.mytext2.util.API;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NewsFragment extends BesaFragment {
    private TabLayout mNewTb;
    private ViewPager mNewVp;
    private List<String> titles = new ArrayList<>();
    private List<Bean_newtitle.DataBean> dataBeans;
    private Myy myy;
    private List<Fragment> fragmentList = new ArrayList<>();
    @Override
    public View onView() {
        View view = View.inflate(getActivity(), R.layout.newsfragment, null);
        mNewTb = view.findViewById(R.id.new_tb);
        mNewVp = view.findViewById(R.id.new_vp);
        return view;
    }

    @Override
    public void onData() {
        super.onData();


        getTital();


    }

    private void setVp(){
        fragmentList.add(new frgment1());
        fragmentList.add(new frgment2());
        fragmentList.add(new frgment3());
        fragmentList.add(new frgment4());
        fragmentList.add(new frgment5());
        fragmentList.add(new frgment6());

        myy = new Myy(getChildFragmentManager());

        mNewVp.setAdapter(myy);

        mNewTb.setupWithViewPager(mNewVp);


    }

    private void getTital(){
        Call call = new API().myGetOkhttp("/prod-api/press/category/list");
      call.enqueue(new Callback() {
          @Override
          public void onFailure(Call call, IOException e) {}
          @Override
          public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    Gson gson = new Gson();
                    Bean_newtitle bean_newtitle = gson.fromJson(response.body().string(),Bean_newtitle.class);
                    dataBeans = bean_newtitle.getData();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            titles.clear();
                            for (int i =0;i<dataBeans.size();i++){
                                titles.add(dataBeans.get(i).getName());
                            }
                            setVp();
                        }
                    });
                }
          }
      });
    }

    class Myy extends FragmentPagerAdapter{


        public Myy(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            if (position==0){
                return fragmentList.get(position);
            }else if (position==1){
                return fragmentList.get(position);
            }else if (position==2){
                return fragmentList.get(position);
            }else if (position==3){
                return fragmentList.get(position);
            }else if (position==4){
                return fragmentList.get(position);
            }else if (position==5){
                return fragmentList.get(position);
            }
            return fragmentList.get(0);
        }

        @Override
        public int getCount() {
            return titles.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }}
