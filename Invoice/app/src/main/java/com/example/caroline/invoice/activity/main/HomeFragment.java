package com.example.caroline.invoice.activity.main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.caroline.invoice.R;

/**
 * Created by asus1 on 2018/4/9.
 */

public  class HomeFragment extends Fragment {

    private View view;
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view=inflater.inflate(R.layout.activity_centre,container,false);

        return view;
    }

    public static HomeFragment newInstance(String name){
        Bundle args=new Bundle();
        args.putString("name",name);
        HomeFragment fragment=new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
