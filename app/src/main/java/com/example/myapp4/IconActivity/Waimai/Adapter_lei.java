package com.example.myapp4.IconActivity.Waimai;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp4.Adapter_home_rv_servies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Adapter_lei extends RecyclerView.Adapter {
    private Listener listener;
    private List<Bean_lei.Data> list = new ArrayList<>();

    @NonNull
    @Override
    public Mm onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 111);
        TextView textView = new TextView(parent.getContext());
        textView.setLayoutParams(layoutParams);
        return new Mm(textView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView textView = (TextView) holder.itemView;
        textView.setText(list.get(position).getName());

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.click(list.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData( List<Bean_lei.Data> list1){

        this.list.clear();
        this.list.addAll(list1);
        notifyDataSetChanged();
    }

    class Mm extends RecyclerView.ViewHolder{

        public Mm(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface Listener{
        void click(String i);
    }

    public void setListener(Listener listener1){
        this.listener=listener1;
    }
}

