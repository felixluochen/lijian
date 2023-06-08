package com.example.mytext2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class User_fixpre extends AppCompatActivity {

    private Button mUserFixprTure;
    private Button mUserFixprCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_fixpre);
        mUserFixprTure = findViewById(R.id.user_fixpr_ture);
        mUserFixprCancel = findViewById(R.id.user_fixpr_cancel);


        mUserFixprCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mUserFixprTure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(User_fixpre.this, "修改成功！", Toast.LENGTH_SHORT).show();
                    }
                });
                finish();
            }
        });
    }
}