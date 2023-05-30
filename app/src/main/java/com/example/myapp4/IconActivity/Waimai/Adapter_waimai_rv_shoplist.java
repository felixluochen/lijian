package com.example.myapp4.IconActivity.Waimai;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapp4.Adapter_home_rv_servies;
import com.example.myapp4.Bean_home_rv_servies;
import com.example.myapp4.R;
import com.example.myapp4.Util.API;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Adapter_waimai_rv_shoplist extends RecyclerView.Adapter {
    private Listener listener;
    private List<Bean_waimai_rv_shoplist.Rows> list = new ArrayList<>();


    @NonNull
    @Override
    public Mm onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.waimai_shoplist_item,parent,false);
        return new Mm(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
       View view = holder.itemView;
        ImageView imageView = view.findViewById(R.id.waimai_shoplist_img);
        TextView name,address,dis,xiaoliang;
        LinearLayout layout = view.findViewById(R.id.waimai_shoplist_bt);
        name = view.findViewById(R.id.waimai_shoplist_name);
        address = view.findViewById(R.id.waimai_shoplist_address);
        dis = view.findViewById(R.id.waimai_shoplist_distence);
        xiaoliang = view.findViewById(R.id.waimai_shoplist_xiaoliang);


        Glide.with(view.getContext())
                        .load(API.url+list.get(position).getImgUrl())
                                .into(imageView);
        name.setText(list.get(position).getName());
        address.setText("地址："+list.get(position).getAddress());
        dis.setText("距离："+list.get(position).getDistance());
        xiaoliang.setText("销量："+list.get(position).getSaleQuantity());

        layout.setOnClickListener(new View.OnClickListener() {
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

    public class Mm extends RecyclerView.ViewHolder{

        public Mm(@NonNull View itemView) {
            super(itemView);
        }
    }
    public void setData(List<Bean_waimai_rv_shoplist.Rows> list1){
        this.list.clear();
        this.list.addAll(list1);
        notifyDataSetChanged();
    }

    public interface Listener{
        void click(String i);
    }

    public void setListener(Listener listener1){
        this.listener=listener1;
    }
}
