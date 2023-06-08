package com.example.mytext2.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytext2.R;
import com.example.mytext2.adapter.Adapter_serall;
import com.example.mytext2.adapter.Adapter_servies;
import com.example.mytext2.bean.Bean_servies;
import com.example.mytext2.util.API;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ServiesFragment extends BesaFragment {
    private TextView mTextView2;
    private RecyclerView mSeAll;
    private Adapter_serall adapter_servies;
    private List<Bean_servies.RowsBean> list2;
    @Override
    public View onView() {
        View view = View.inflate(getActivity(), R.layout.serviesfragment, null);
        mTextView2 = view.findViewById(R.id.textView2);
        mSeAll = view.findViewById(R.id.se_all);
        return view;
    }

    @Override
    public void onData() {
        super.onData();

        setSre();
    }

    private void setSre(){
        adapter_servies = new Adapter_serall();
        mSeAll.setLayoutManager(new GridLayoutManager(getActivity(),5));
        mSeAll.setAdapter(adapter_servies);


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
                                Intent intent = new Intent();
                                if (i == 1){
//                                    intent.setClass(getActivity(),Subway.class);
//                                    startActivity(intent);
                                }else {
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getActivity(), "暂未开放", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        });
                    }
                });
            }
        });

    }
}
