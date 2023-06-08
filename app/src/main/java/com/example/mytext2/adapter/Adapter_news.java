package com.example.mytext2.adapter;

import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mytext2.R;
import com.example.mytext2.bean.Bean_news;
import com.example.mytext2.util.API;

import java.util.ArrayList;
import java.util.List;

public class Adapter_news extends RecyclerView.Adapter {
    private List<Bean_news.Rows> list = new ArrayList<>();


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_news_item,parent,false);
        return new My(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        View view = holder.itemView;
        ImageView imageView = view.findViewById(R.id.new_img);
        TextView name = view.findViewById(R.id.new_name);
        TextView content = view.findViewById(R.id.new_content);
        TextView textView = view.findViewById(R.id.new_time);
        TextView read = view.findViewById(R.id.new_look);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(view)
                .load(API.url+list.get(position).getCover())
                .into(imageView);
        read.setText("阅读人数："+list.get(position).getReadNum());
        name.setText(list.get(position).getTitle());
        textView.setText(list.get(position).getPublishDate());
        Spanned spanned = Html.fromHtml(list.get(position).getContent());
        content.setText(spanned);


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

    public void setData(List<Bean_news.Rows> list1){
        this.list.clear();
        this.list.addAll(list1);
        notifyDataSetChanged();
    }
}
