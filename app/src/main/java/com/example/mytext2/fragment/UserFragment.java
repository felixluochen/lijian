package com.example.mytext2.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.mytext2.LoginActivity;
import com.example.mytext2.R;
import com.example.mytext2.User_dingdan;
import com.example.mytext2.User_fixmima;
import com.example.mytext2.User_fixpre;
import com.example.mytext2.User_jianyi;

public class UserFragment extends BesaFragment implements View.OnClickListener {
    private ImageView mImageView5;
    private TextView mTextView6;
    private Button mUserFix;
    private TextView mTextView3;
    private ImageView mImageView2;
    private ConstraintLayout mUserMm;
    private TextView mTextView4;
    private ImageView mImageView3;
    private ConstraintLayout mUserDingdan;
    private TextView mTextView5;
    private ImageView mImageView4;
    private ConstraintLayout mUserYijian;
    private Button mUserBt;

    @Override
    public View onView() {
        View view = View.inflate(getActivity(), R.layout.userfragment, null);

        mUserFix = view.findViewById(R.id.user_fix);
        mUserMm = view.findViewById(R.id.user_mm);
        mUserDingdan = view.findViewById(R.id.user_dingdan);
        mUserYijian = view.findViewById(R.id.user_yijian);
        mUserBt = view.findViewById(R.id.user_bt);
        return view;
    }

    @Override
    public void onData() {
        super.onData();

        mUserDingdan.setOnClickListener(this);
        mUserFix.setOnClickListener(this);
        mUserMm.setOnClickListener(this);
        mUserYijian.setOnClickListener(this);
        mUserBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.user_fix:
                Intent intent7 = new Intent(getActivity(), User_fixpre.class);
                startActivity(intent7);
                break;
            case R.id.user_mm:
                Intent intent = new Intent(getActivity(), User_fixmima.class);
                startActivity(intent);
                break;
            case R.id.user_dingdan:
                Intent intent2 = new Intent(getActivity(), User_dingdan.class);
                startActivity(intent2);
                break;
            case R.id.user_yijian:
                Intent intent3 = new Intent(getActivity(), User_jianyi.class);
                startActivity(intent3);
                break;
            case R.id.user_bt:
                Intent intent4 = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent4);
                getActivity().finish();
                break;
        }
    }
}
