package com.example.myapp4.Util;



import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.to.aboomy.pager2banner.Banner;
import com.to.aboomy.pager2banner.Indicator;
import com.to.aboomy.pager2banner.IndicatorView;

import org.json.JSONObject;

import java.io.IOException;
import java.security.PublicKey;

import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class API {
    private String getresponse;

    public static final String url = "http://124.93.196.45:10001";
    public final static String addHeader = "Authorization";


    public Call GetOkhttp(String url){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API.url+url)
                .build();
        Call call = client.newCall(request);
        return call;
    }

    public Call GetTokenOkhttp(String url,String token){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API.url+url)
                .addHeader(API.addHeader,token)
                .build();
        Call call = client.newCall(request);
        return call;
    }

    public Call PostTokenOkhttp(JSONObject jsonObject,String urll){
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType,jsonObject.toString());
        Request request = new Request.Builder()
                .url(url+urll)
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        return  call;

    }
    public IndicatorView getIndicatorView(Context context){
        IndicatorView indicatorView = new IndicatorView(context);
        indicatorView.setIndicatorStyle(IndicatorView.IndicatorStyle.INDICATOR_DASH);
        indicatorView.setIndicatorColor(Color.WHITE);
        indicatorView.setIndicatorSelectorColor(Color.rgb(1,2,3));
        return indicatorView;
    }





}
