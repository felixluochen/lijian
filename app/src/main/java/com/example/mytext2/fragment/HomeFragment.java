package com.example.mytext2.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytext2.MainActivity;
import com.example.mytext2.R;
import com.example.mytext2.adapter.Adapter_news;
import com.example.mytext2.adapter.Adapter_servies;
import com.example.mytext2.banner.Adapter_banner;
import com.example.mytext2.banner.Bean_banner;
import com.example.mytext2.bean.Bean_news;
import com.example.mytext2.bean.Bean_servies;
//import com.example.mytext2.iconactivity.findhouse.FindHouse;
import com.example.mytext2.util.API;
import com.google.gson.Gson;
import com.to.aboomy.pager2banner.Banner;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HomeFragment extends BesaFragment {
    private ImageView mImageView;
    private EditText mEditTextTextPersonName;
    private Banner mHomeBanner;
    private RecyclerView mHomeSer;
    private TextView mTextView;
    private RecyclerView mHomeNews;
    private Adapter_banner adapter_banner;
    private List<Bean_banner.Rows> list1;
    private Adapter_servies adapter_servies;
    private List<Bean_servies.RowsBean> list2;
    private List<Bean_news.Rows> list4;
    private Adapter_news adapter_news;
    private Jump jump;
    private String namew;
    @Override
    public View onView() {
        View view = View.inflate(getActivity(), R.layout.homefragment, null);

        mImageView = view.findViewById(R.id.imageView);
        mEditTextTextPersonName = view.findViewById(R.id.editTextTextPersonName);
        mHomeBanner = view.findViewById(R.id.home_banner);
        mHomeSer = view.findViewById(R.id.home_ser);
        mTextView = view.findViewById(R.id.textView);
        mHomeNews = view.findViewById(R.id.home_news);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        jump=(MainActivity)context;
    }

    @Override
    public void onData() {
        super.onData();

        setBanner();

        setServies();

        setNews();

        mEditTextTextPersonName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                namew = mEditTextTextPersonName.getText().toString();
                if (i == EditorInfo.IME_ACTION_SEARCH){
                    if (namew.equals("今日要闻")){
                        jump.jumpTo("今日要闻");
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private void setNews(){
             adapter_news = new Adapter_news();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return  false;
            }
           };
            mHomeNews.setLayoutManager(linearLayoutManager);
            mHomeNews.setAdapter(adapter_news);

             Call call = new API().myGetOkhttp("/prod-api/press/press/list");

             call.enqueue(new Callback() {
                 @Override
                 public void onFailure(Call call, IOException e) {

                 }

                 @Override
                 public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()){
                            Gson gson = new Gson();
                            Bean_news bean_news = gson.fromJson(response.body().string(),Bean_news.class);
                            list4 = bean_news.getRows();
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapter_news.setData(list4);
                                }
                            });
                        }
                 }
             });

    }

    private void setServies(){
        adapter_servies = new Adapter_servies();
        mHomeSer.setLayoutManager(new GridLayoutManager(getActivity(),5));
        mHomeSer.setAdapter(adapter_servies);


        Call call = new API().myGetOkhttp("/prod-api/api/service/list");
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                Bean_servies bean_home_servies = gson.fromJson(response.body().string(),Bean_servies.class);
                list2 = bean_home_servies.getRows();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter_servies.setData(list2);
                        adapter_servies.setListener(new Adapter_servies.Listener() {
                            @Override
                            public void click(int i) {
//                                Intent intent = new Intent();
//                                if (i == 0){
//                                    intent.setClass(getActivity(),stop);
//                                    startActivity(intent);
//                                }else if (i == 1){
//                                    intent.setClass(getActivity(),.class);
//                                    startActivity(intent);
//                                }else if (i == 2){
//                                    intent.setClass(getActivity(), bus.class);
//                                    startActivity(intent);
//                                }else if (i == 3){
//                                    intent.setClass(getActivity(), doc.class);
//                                    startActivity(intent);
//                                }else if (i == 4){
//                                    intent.setClass(getActivity(), FindHouse.class);
//                                    startActivity(intent);
//                                }else if (i == 5){
//                                    intent.setClass(getActivity(), FindHouse.class);
//                                    startActivity(intent);
//                                }else if (i == 6){
//                                    intent.setClass(getActivity(), FindHouse.class);
//                                    startActivity(intent);
//                                }else if (i == 7){
//                                    intent.setClass(getActivity(), FindHouse.class);
//                                    startActivity(intent);
//                                }else if (i == 8){
//                                    intent.setClass(getActivity(), FindHouse.class);
//                                    startActivity(intent);
//                                }else if (i == 9){
//                                    intent.setClass(getActivity(), FindHouse.class);
//                                    startActivity(intent);
//                                }
                            }
                        });
                    }
                });
            }
        });


    }

    private void setBanner(){
        Call call = new API().myGetOkhttp("/prod-api/api/rotation/list");
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    Gson gson = new Gson();
                    Bean_banner bannerr = gson.fromJson(response.body().string(),Bean_banner.class);
                    list1 = bannerr.getRows();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter_banner = new Adapter_banner(list1);
                            mHomeBanner.setIndicator(new API().getIndicatorView(getActivity())).setAdapter(adapter_banner);
                        }
                    });
                }
            }
        });
    }

    public interface Jump{
        void jumpTo(String name);
    }



}
