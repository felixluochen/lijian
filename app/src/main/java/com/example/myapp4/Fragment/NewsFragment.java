package com.example.myapp4.Fragment;

import android.view.View;

import com.example.myapp4.R;

public class NewsFragment extends BaseFragment{
    @Override
    public View setView() {
        View view = View.inflate(getActivity(), R.layout.newsfragment,null);
        return view;
    }
}
