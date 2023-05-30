package com.example.myapp4.IconActivity.Waimai;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapp4.Bean_home_rv_servies;
import com.example.myapp4.R;
import com.example.myapp4.Util.API;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Adapter_cai extends RecyclerView.Adapter {
    private Map<Integer,Bean_cai.Data> pracar = new HashMap<>();
    private  List<Bean_cai.Data> list = new ArrayList<>();
    private  Bean_cai.Data data;
    private Lit lit;
    @NonNull
    @Override
    public Mm onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.waimai_detial_diancai_cai,parent,false);
        return new Mm(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        View view = holder.itemView;
        ImageView imageView = view.findViewById(R.id.waimai_cai_img);
        TextView name,detial,xiaoliang,total;
        total = view.findViewById(R.id.waimai_cai_total);
        detial = view.findViewById(R.id.waimai_cai_detial);
        xiaoliang = view.findViewById(R.id.waimai_cai_xiaoliang);
        name = view.findViewById(R.id.waimai_cai_name);
        ImageView add,reduce;
        add = view.findViewById(R.id.waimai_cai_add);
        reduce = view.findViewById(R.id.waimai_cai_reduce);
        total.setText("0");

        data = list.get(position);

        Glide.with(view.getContext())
                .load(API.url+data.getImgUrl())
                .into(imageView);

        detial.setText(data.getDetail());
        name.setText(data.getName());
        xiaoliang.setText("销量："+data.getSaleQuantity());

        if (pracar.containsKey(Integer.valueOf(list.get(position).getId()))){
            Bean_cai.Data data = pracar.get(Integer.valueOf(list.get(position).getId()));
            total.setVisibility(View.VISIBLE);
            reduce.setVisibility(View.VISIBLE);
            Log.i("get111111",String.valueOf(data.getCount()));
            total.setText(String.valueOf(data.getCount()));
        }else{
            total.setVisibility(View.GONE);
            reduce.setVisibility(View.GONE);
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer tot = Integer.valueOf(total.getText().toString());
                if (lit.add(tot,list.get(position))){
                    total.setText(String.valueOf(tot+1));
                    total.setVisibility(View.VISIBLE);
                    reduce.setVisibility(View.VISIBLE);

                }else {
                    total.setText(String.valueOf(tot+1));
                }
            }
        });

        reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer tt = Integer.valueOf(total.getText().toString());
                if (lit.reduce(tt,list.get(position))){
                    total.setText(String.valueOf(tt-1));
                    total.setVisibility(View.GONE);
                    reduce.setVisibility(View.GONE);
                }else {
                    total.setText(String.valueOf(tt-1));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Mm extends RecyclerView.ViewHolder{

        public Mm(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void setPracar(Map<Integer,Bean_cai.Data> pracar1){
         this.pracar.clear();
         this.pracar.putAll(pracar1);
         notifyDataSetChanged();

    }

    public void setData(List<Bean_cai.Data>  list1, Map<Integer,Bean_cai.Data> pracar1){
        this.pracar.clear();
        this.pracar.putAll(pracar1);
        this.list.clear();
        this.list.addAll(list1);
        notifyDataSetChanged();
    }

    public interface Lit{
        boolean add(Integer i,Bean_cai.Data data1);
        boolean reduce(Integer i,Bean_cai.Data data1);
    }

    public void setListener(Lit listener){
        this.lit = listener;
    }
}
