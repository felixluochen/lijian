package com.example.myapp4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp4.Bean.Bean_Login;
import com.example.myapp4.Util.API;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText name,password;
    private Button log,rg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);

        name = findViewById(R.id.login_getname);
        password = findViewById(R.id.login_getpassword);
        log = findViewById(R.id.login_logbt);
        rg = findViewById(R.id.login_rgbt);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setlog();
            }
        });
    }

    private void setlog(){
        String namee = name.getText().toString().trim();
        String pas = password.getText().toString().trim();

        Log.i("NAME：", namee);
        Log.i("PASSWORD：", pas);

        OkHttpClient client = new OkHttpClient();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username",namee);
            jsonObject.put("password",pas);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MediaType mediaType = MediaType.parse("application/json;charset=utf-8");

        RequestBody requestBody = RequestBody.create(mediaType,jsonObject.toString());

        Request request = new Request.Builder()
                .post(requestBody)
                .url(API.url+"/prod-api/api/login")
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {Log.i("请求情况：", "请求失败");}
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                  if (response.isSuccessful()){
                      Gson gson = new Gson();
                      Bean_Login bean_login = gson.fromJson(response.body().string(),Bean_Login.class);

                      Log.i("content",bean_login.toString());

                      if (bean_login.getCode().equals("200")){
                          SharedPreferences.Editor editor = getSharedPreferences("get_token",MODE_PRIVATE).edit();
                          editor.putString("token",bean_login.getToken());
                          editor.apply();

                          Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                          startActivity(intent);
                      }else {
                          runOnUiThread(new Runnable() {
                              @Override
                              public void run() {
                                  Toast.makeText(LoginActivity.this, "登录失败，账号或密码错误！", Toast.LENGTH_SHORT).show();
                              }
                          });
                      }
                  }
            }
        });


    }

}
