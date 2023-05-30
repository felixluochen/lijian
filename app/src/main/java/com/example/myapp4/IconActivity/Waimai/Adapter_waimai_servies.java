package com.example.myapp4.IconActivity.Waimai;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapp4.R;
import com.example.myapp4.Util.API;

import java.util.ArrayList;
import java.util.List;

public class Adapter_waimai_servies extends RecyclerView.Adapter {

    private List<Bean_waimai_rv_servies.Data> list = new ArrayList<>();

    @NonNull
    @Override
    public My onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(),R.layout.home_servies_rv_item,null);

        return new My(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        View view = holder.itemView;
        LinearLayout layout = view.findViewById(R.id.home_servies_bt);
        ImageView imageView = view.findViewById(R.id.home_servies_rv_img);
        TextView textView = view.findViewById(R.id.home_servies_rv_name);
        textView.setText(list.get(position).getThemeName());
        Glide.with(view.getContext())
                .load(API.url+list.get(position).getImgUrl())
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

    public void setData( List<Bean_waimai_rv_servies.Data> list1){
        this.list.clear();
        this.list.addAll(list1);
        notifyDataSetChanged();
    }
}
