package com.example.mytext2.util;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;

import com.to.aboomy.pager2banner.IndicatorView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class API {
    public final static String url = "http://124.93.196.45:10001";
    public final static String addHeader = "Authorization";

    public Call myGetOkhttp(String urll){
        OkHttpClient client = new OkHttpClient();
        Request request =new  Request.Builder()
                .url(url+urll)
                .build();
        Call call = client.newCall(request);
        return call;
    }

    public Call myGetTokenOkhttp(String urlll,String token){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url+urlll)
                .addHeader("Authorization",token)
                .build();
        Call call = client.newCall(request);
        return call;
    }

    public IndicatorView getIndicatorView(Context context){
        IndicatorView indicatorView = new IndicatorView(context);
        indicatorView.setIndicatorStyle(IndicatorView.IndicatorStyle.INDICATOR_DASH);
        indicatorView.setIndicatorColor(Color.WHITE);
        indicatorView.setIndicatorSelectorColor(Color.rgb(1,2,3));
        return indicatorView;
    }

//    public AlertDialog getDialog(Context context){
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        AlertDialog alertDialog = builder.create();
//        alertDialog.setView();
//        alertDialog.show();
//        return  alertDialog;
//
//    }
}
