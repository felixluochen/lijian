package com.example.myapp4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapp4.Util.API;

import java.util.ArrayList;
import java.util.List;

public class Adapter_home_rv_servies extends RecyclerView.Adapter {

    private List<Bean_home_rv_servies.Rows> list = new ArrayList<>();

    private Listener listener;
    @NonNull
    @Override
    public Mm onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_servies_rv_item,parent,false);
        View view = View.inflate(parent.getContext(),R.layout.home_servies_rv_item,null);
        return new Mm(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
             View view = holder.itemView;
        LinearLayout layout = view.findViewById(R.id.home_servies_bt);
        ImageView imageView = view.findViewById(R.id.home_servies_rv_img);
        TextView textView = view.findViewById(R.id.home_servies_rv_name);
        textView.setText(list.get(position).getServiceName());
        Glide.with(view.getContext())
                .load(API.url+list.get(position).getImgUrl())
                .into(imageView);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.click(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size()-6;
    }

    public class Mm extends RecyclerView.ViewHolder{

        public Mm(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void setData(List<Bean_home_rv_servies.Rows> list1){
        this.list.clear();
        this.list.addAll(list1);
        notifyDataSetChanged();
    }


    public interface Listener{
        void click(int i);
    }

    public void setListener(Listener listener1){
        this.listener=listener1;
    }
}
