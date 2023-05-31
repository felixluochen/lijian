package com.example.review2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         //显示
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,MainActivity2.class);
        intent.putExtra("name","ll");
        startActivity(intent);
         //隐式
        Intent intent1 = new Intent();
        intent1.setAction("intent_PRIVATE");
        Bundle bundle = new Bundle();
        bundle.putString("ww","www");
        intent1.putExtras(bundle);
        startActivity(intent1);



        //接收

        Intent intent2 = new Intent();
        intent2 = getIntent();
        String ss = intent2.getStringExtra("name");
        //或

        Intent intent3 = new Intent();
        Bundle bundle1 = getIntent().getExtras();
        String mm = bundle1.getString("ww");

         //关闭页面
        finish();


        //回传数据
        Intent intent4 = new Intent(MainActivity.this,MainActivity2.class);
        startActivityForResult(intent4,1);

        Intent intent5 = new Intent();
        intent5.putExtra("ee","ee");
        setResult(2,intent5);
        finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( requestCode==1 && resultCode==2 ){
            String ee = data.getStringExtra("ee");

        }

    }
}