package com.example.mytext2.banner;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mytext2.util.API;

import java.util.List;

public class Adapter_banner extends RecyclerView.Adapter {
    private List<Bean_banner.Rows> list;

    public Adapter_banner(List<Bean_banner.Rows> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        ImageView imageView = new ImageView(parent.getContext());
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        return new My(imageView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ImageView imageView = (ImageView) holder.itemView;

        Glide.with(imageView)
                .load(API.url+list.get(position).getAdvImg())
                .into(imageView);
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
}
