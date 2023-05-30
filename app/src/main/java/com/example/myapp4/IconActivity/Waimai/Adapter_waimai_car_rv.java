package com.example.myapp4.IconActivity.Waimai;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapp4.R;
import com.example.myapp4.Util.API;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Adapter_waimai_car_rv extends RecyclerView.Adapter {

    private  List<Bean_cai.Data> list = new ArrayList<>();
    private  Bean_cai.Data data;
    private Mapp mapp;
    private Map<Integer,Bean_cai.Data> pracar = new HashMap<>();
    private Integer ii;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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

        data = list.get(position);

        Glide.with(view.getContext())
                .load(API.url+data.getImgUrl())
                .into(imageView);

        detial.setText(data.getDetail());
        name.setText(data.getName());
        xiaoliang.setText("销量："+data.getSaleQuantity());

        ii = data.getCount();
//        if (ii == 0){
//            reduce.setVisibility(View.GONE);
//            total.set
//        }
        reduce.setVisibility(View.VISIBLE);
        total.setVisibility(View.VISIBLE);
        total.setText(String.valueOf(ii));

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  ii +=1;
                  total.setText(String.valueOf(ii));
                  Bean_cai.Data data = pracar.get(Integer.valueOf(list.get(position).getId()));
                  data.setCount(ii);
                  mapp.getMapp(pracar);
                freshData();
            }
        });

        reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ii == 1){
                    total.setText("0");
                    total.setVisibility(View.GONE);
                    reduce.setVisibility(View.GONE);
                    pracar.remove(Integer.valueOf(list.get(position).getId()));
                    mapp.getMapp(pracar);
                    freshData();
                }else {
                    ii -= 1;
                    total.setText(String.valueOf(ii));
                    Bean_cai.Data data = pracar.get(Integer.valueOf(list.get(position).getId()));
                    data.setCount(ii);
                    mapp.getMapp(pracar);
                    freshData();
                }
            }
        });


    }

    public void freshData(){
        this.list.clear();
        this.list.addAll(pracar.values());
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void setData(Map<Integer,Bean_cai.Data> pracarr){
        this.pracar.clear();
        this.pracar.putAll(pracarr);
        this.list.clear();
        this.list.addAll(pracarr.values());
        notifyDataSetChanged();

    }

    public class Mm extends RecyclerView.ViewHolder{

        public Mm(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface Mapp{
        void getMapp(java.util.Map<Integer,Bean_cai.Data> pracar);
    }

    public void getData(Mapp mapp){
        this.mapp=mapp;
    }
}
