package com.example.myapp4.IconActivity.Waimai;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapp4.Fragment.BaseFragment;
import com.example.myapp4.R;
import com.example.myapp4.Util.API;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Diancai_fragment extends BaseFragment {

    private RecyclerView lei,cai;
    private String id = null;
    private List<Bean_lei.Data> list;
    private Adapter_lei adapter_lei;
    private Adapter_cai adapter_cai;
    private List<Bean_cai.Data> list1;
    private Map<Integer,Bean_cai.Data> pracar = new HashMap<>();
    @Override
    public View setView() {
        View view = View.inflate(getActivity(), R.layout.diancai_fragment,null);

        lei = view.findViewById(R.id.waimai_detial_lei);
        cai = view.findViewById(R.id.waimai_detial_cai);

        return view;

    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        id = ((Waimai_Detial_Activity)activity).getIdd();
    }

    @Override
    public void setData() {
        super.setData();
        adapter_lei = new Adapter_lei();
        adapter_cai = new Adapter_cai();

        setLei();

        adapter_cai.setListener(new Adapter_cai.Lit() {
            @Override
            public boolean add(Integer i, Bean_cai.Data data1) {
                if (!pracar.containsKey(data1.getId())){
                    pracar.put(Integer.valueOf(data1.getId()),data1);

                }
                data1.setCount(i+1);
             return true;
            }

            @Override
            public boolean reduce(Integer i, Bean_cai.Data data1) {
                if (i == 1){
                    pracar.remove(Integer.valueOf(data1.getId()));
                    return true;
                }
                Log.i("count",String.valueOf(i-1));
                data1.setCount(i - 1);
                Log.i("getcount",String.valueOf(data1.getCount()));
                return false;
            }
        });


//        setCai();
    }

    private void setCai(String d){
           Call call = new API().GetOkhttp("/prod-api/api/takeout/product/list?sellerId="+id+"&categoryId="+d);
           call.enqueue(new Callback() {
               @Override
               public void onFailure(Call call, IOException e) {

               }

               @Override
               public void onResponse(Call call, Response response) throws IOException {
                       if (response.isSuccessful()){
                           Gson gson = new Gson();
                           Bean_cai bean_cai = gson.fromJson(response.body().string(),Bean_cai.class);
                           list1 = bean_cai.getData();
                           getActivity().runOnUiThread(new Runnable() {
                               @Override
                               public void run() {
                                   cai.setAdapter(adapter_cai);
                                   LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                                   cai.setLayoutManager(linearLayoutManager);
                                   adapter_cai.setData(list1,pracar);
                               }
                           });
                       }
               }
           });
    }

    private void setLei(){
        Call call = new API().GetOkhttp("/prod-api/api/takeout/category/list?sellerId="+id);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {}
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                      if (response.isSuccessful()){
                          Gson gson =new Gson();
                          Bean_lei bean_lei = gson.fromJson(response.body().string(),Bean_lei.class);
                          list = bean_lei.getData();
                          getActivity().runOnUiThread(new Runnable() {
                              @Override
                              public void run() {
                                  LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                                  lei.setLayoutManager(linearLayoutManager);
                                  lei.setAdapter(adapter_lei);
                                  adapter_lei.setData(list);
                                  setCai(list.get(0).getId());
                                  adapter_lei.setListener(new Adapter_lei.Listener() {
                                      @Override
                                      public void click(String i) {
                                              setCai(i);
                                      }
                                  });
                              }
                          });
                      }
            }
        });


    }


   public void setValue(Map<Integer,Bean_cai.Data> pracar1){
       this.pracar.clear();
       this.pracar.putAll(pracar1);
       adapter_cai.setPracar(pracar);
   }

   public Map<Integer,Bean_cai.Data> getValue(){
        return pracar;
   }


}
