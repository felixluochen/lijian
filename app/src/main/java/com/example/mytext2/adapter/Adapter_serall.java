package com.example.mytext2.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mytext2.R;
import com.example.mytext2.bean.Bean_servies;
import com.example.mytext2.util.API;

import java.util.ArrayList;
import java.util.List;

public class Adapter_serall extends RecyclerView.Adapter {
    private List<Bean_servies.RowsBean> list = new ArrayList<>();
    private Adapter_servies.Listener listener;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.main_icon_item,null);
        return new My(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        View view = holder.itemView;
        ImageView imageView = view.findViewById(R.id.icon_img);
        TextView textView  = view.findViewById(R.id.icon_name);


        Glide.with(view.getContext())
                .load(API.url+list.get(position).getImgUrl())
                .into(imageView);

        textView.setText(list.get(position).getServiceName());


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.click(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class My extends RecyclerView.ViewHolder{

        public My(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void setData(List<Bean_servies.RowsBean> list1){
        this.list.clear();
        this.list.addAll(list1);
        notifyDataSetChanged();
    }

    public interface Listener{
        void click(int i);
    }

    public void setListener(Adapter_servies.Listener listener){
        this.listener = listener;
    }
}
