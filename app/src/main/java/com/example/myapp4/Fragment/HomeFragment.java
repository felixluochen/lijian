package com.example.myapp4.Fragment;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp4.Banner.Adapter_Banner;
import com.example.myapp4.Adapter_home_rv_servies;
import com.example.myapp4.Banner.Bean_banner;
import com.example.myapp4.Bean_home_rv_servies;
import com.example.myapp4.IconActivity.Waimai.WaimaiActivity;
import com.example.myapp4.R;
import com.example.myapp4.Util.API;
import com.google.gson.Gson;
import com.to.aboomy.pager2banner.Banner;
import com.to.aboomy.pager2banner.IndicatorView;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HomeFragment extends BaseFragment{

    private Banner banner;
    private RecyclerView ss,news;
    private Bean_banner bannerr;
    private List<Bean_banner.Rows> list;
    private Adapter_home_rv_servies adapter_home_rv_servies;
    private List<Bean_home_rv_servies.Rows> list1;
    @Override
    public View setView() {
        View view = View.inflate(getActivity(), R.layout.homefragment,null);

        banner = view.findViewById(R.id.home_banner);
        ss = view.findViewById(R.id.home_servies_rv);
        news = view.findViewById(R.id.home_new_rv);

        return view;
    }

    @Override
    public void setData() {
        super.setData();

        adapter_home_rv_servies = new Adapter_home_rv_servies();
        ss.setLayoutManager(new GridLayoutManager(getActivity(),5));
        ss.setAdapter(adapter_home_rv_servies);

        setBannerData();
        setRvServies();

    }

    private void setRvServies(){
        Call call = new API().GetOkhttp("/prod-api/api/service/list");
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {}
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                 Gson gson = new Gson();
                 Bean_home_rv_servies bean_home_rv_servies = gson.fromJson(response.body().string(),Bean_home_rv_servies.class);
                 list1 = bean_home_rv_servies.getRows();
                 getActivity().runOnUiThread(new Runnable() {
                     @Override
                     public void run() {
                         adapter_home_rv_servies.setData(list1);
                         adapter_home_rv_servies.setListener(new Adapter_home_rv_servies.Listener() {
                             @Override
                             public void click(int i) {
                                 Intent intent = new Intent();
                                 if (i==0){
                                     intent.setClass(getActivity(), WaimaiActivity.class);
                                     startActivity(intent);
                                 }else if (i==1){
                                     intent.setClass(getActivity(),WaimaiActivity.class);
                                     startActivity(intent);
                                 }else if (i==2){
                                     intent.setClass(getActivity(),WaimaiActivity.class);
                                     startActivity(intent);
                                 }else if (i==3){
                                     intent.setClass(getActivity(),WaimaiActivity.class);
                                     startActivity(intent);
                                 }else if (i==4){
                                     intent.setClass(getActivity(),WaimaiActivity.class);
                                     startActivity(intent);
                                 }else if (i==5){
                                     intent.setClass(getActivity(),WaimaiActivity.class);
                                     startActivity(intent);
                                 }else if (i==6){
                                     intent.setClass(getActivity(),WaimaiActivity.class);
                                     startActivity(intent);
                                 }else if (i==7){
                                     intent.setClass(getActivity(),WaimaiActivity.class);
                                     startActivity(intent);
                                 }else if (i==8){
                                     intent.setClass(getActivity(),WaimaiActivity.class);
                                     startActivity(intent);
                                 }else if (i==9){
                                     intent.setClass(getActivity(),WaimaiActivity.class);
                                     startActivity(intent);
                                 }
                             }
                         });
                     }
                 });
            }
        });

    }
    private  void  setBannerData(){
        Call call = new API().GetOkhttp("/prod-api/api/rotation/list");
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {}
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                bannerr = gson.fromJson(response.body().string(),Bean_banner.class);
                list = bannerr.getRows();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Adapter_Banner adapter_banner = new Adapter_Banner(list,getActivity());
                        IndicatorView indicatorView = new API().getIndicatorView(getActivity());
                        banner.setIndicator(indicatorView).setAdapter(adapter_banner);
                    }
                });
            }
        });
    }
}
