package com.example.mytext2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class User_jianyi extends AppCompatActivity {

    private Button mButton;
    private Button mButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_jianyi);
        mButton = findViewById(R.id.button);
        mButton3 = findViewById(R.id.button3);

        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(User_jianyi.this, "提交成功", Toast.LENGTH_SHORT).show();
                    }
                });
                finish();
            }
        });
    }
}