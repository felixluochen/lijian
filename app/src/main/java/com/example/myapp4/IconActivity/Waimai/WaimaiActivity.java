package com.example.myapp4.IconActivity.Waimai;



import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp4.Banner.Adapter_Banner;
import com.example.myapp4.Banner.Bean_banner;
import com.example.myapp4.R;
import com.example.myapp4.Util.API;
import com.google.gson.Gson;
import com.to.aboomy.pager2banner.Banner;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WaimaiActivity extends AppCompatActivity {

    private Banner banner;
    private RecyclerView shoplist,servies;
    private List<Bean_banner.Rows> list;
    private List<Bean_waimai_rv_servies.Data> list1;
    private List<Bean_waimai_rv_shoplist.Rows> list2;
    private  Adapter_Banner adapter_banner;
    private Adapter_waimai_servies serviess;
    private Adapter_waimai_rv_shoplist adapter_waimai_rv_shoplist;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waimaiactivity);

        shoplist = findViewById(R.id.waimai_rv_shoplist);
        banner = findViewById(R.id.waimai_banner);
        servies = findViewById(R.id.waimai_rv_servies);

        setBanner();
        setRvServies();
        setRvshoplist();
    }

    private void setRvshoplist(){
        adapter_waimai_rv_shoplist = new Adapter_waimai_rv_shoplist();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        shoplist.setLayoutManager(linearLayoutManager);
        shoplist.setAdapter(adapter_waimai_rv_shoplist);

        Call call = new API().GetOkhttp("/prod-api/api/takeout/seller/near");
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                 if (response.isSuccessful()){
                     Gson gson = new Gson();
                     Bean_waimai_rv_shoplist bean_waimai_rv_shoplist = gson.fromJson(response.body().string(),Bean_waimai_rv_shoplist.class);
                     list2 = bean_waimai_rv_shoplist.getRows();
                     runOnUiThread(new Runnable() {
                         @Override
                         public void run() {
                             adapter_waimai_rv_shoplist.setData(list2);
                             adapter_waimai_rv_shoplist.setListener(new Adapter_waimai_rv_shoplist.Listener() {
                                 @Override
                                 public void click(String i) {
                                     Intent intent = new Intent(WaimaiActivity.this,Waimai_Detial_Activity.class);
                                     intent.putExtra("id",i);
                                     startActivity(intent);
                                 }
                             });
                         }
                     });
                 }
            }
        });

    }

    private void setRvServies(){
        serviess = new Adapter_waimai_servies();
        servies.setLayoutManager(new GridLayoutManager(this,5));
        servies.setAdapter(serviess);

        Call call = new API().GetOkhttp("/prod-api/api/takeout/theme/list");
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {}
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                 if (response.isSuccessful()){
                       Gson gson = new Gson();
                       Bean_waimai_rv_servies servies = gson.fromJson(response.body().string(),Bean_waimai_rv_servies.class);
                       list1 = servies.getData();
                       runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               serviess.setData(list1);
                           }
                       });
                 }
            }
        });


    }

    private void setBanner(){
        Call call = new API().GetOkhttp("/prod-api/api/takeout/rotation/list");
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {}
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()){
                            Gson gson = new Gson();
                            Bean_banner banner1 = gson.fromJson(response.body().string(),Bean_banner.class);
                            list = banner1.getRows();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapter_banner = new Adapter_Banner(list,WaimaiActivity.this);
                                    banner.setIndicator(new API().getIndicatorView(WaimaiActivity.this)).setAdapter(adapter_banner);
                                }
                            });
                        }
            }
        });


    }
}
