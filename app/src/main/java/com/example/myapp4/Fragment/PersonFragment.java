package com.example.myapp4.Fragment;

import android.view.View;

import com.example.myapp4.R;

public class PersonFragment extends BaseFragment{
    @Override
    public View setView() {
        View view = View.inflate(getActivity(), R.layout.personfragment,null);
        return view;
    }
}
