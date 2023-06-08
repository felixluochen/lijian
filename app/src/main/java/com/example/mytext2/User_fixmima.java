package com.example.mytext2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class User_fixmima extends AppCompatActivity {

    private Button ture,cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_fixmima);

        ture = findViewById(R.id.user_fix_ture);
        cancel = findViewById(R.id.user_fix_cancel);

        ture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(User_fixmima.this, "密码修改成功！", Toast.LENGTH_SHORT).show();
                    }
                });
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}