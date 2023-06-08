package com.example.mytext2.fragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytext2.R;
import com.example.mytext2.adapter.Adapter_news;
import com.example.mytext2.bean.Bean_news;
import com.example.mytext2.util.API;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class frgment3 extends BesaFragment{
    private RecyclerView mFrg1;
    private Adapter_news adapter_news;
    private List<Bean_news.Rows> list4;
    @Override
    public View onView() {
        View view = View.inflate(getActivity(), R.layout.frgment1, null);
        mFrg1 = view.findViewById(R.id.frg1);
        return view;
    }

    @Override
    public void onData() {
        super.onData();

        setNews();
    }

    private void setNews(){
        adapter_news = new Adapter_news();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mFrg1.setLayoutManager(linearLayoutManager);
        mFrg1.setAdapter(adapter_news);

        Call call = new API().myGetOkhttp("/prod-api/press/press/list?type=19");

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
}
