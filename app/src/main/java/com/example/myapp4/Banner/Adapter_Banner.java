package com.example.myapp4.Banner;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapp4.Banner.Bean_banner;
import com.example.myapp4.Util.API;

import java.util.List;

public class Adapter_Banner extends RecyclerView.Adapter {
    private List<Bean_banner.Rows> list;
    private Context context;

    public Adapter_Banner(List<Bean_banner.Rows> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public My onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return new My(imageView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ImageView imageView =(ImageView) holder.itemView;
        Glide.with(context)
                .load(API.url+list.get(position).getAdvImg())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class My extends RecyclerView.ViewHolder{

        public My(@NonNull View itemView) {
            super(itemView);
        }
    }
}
