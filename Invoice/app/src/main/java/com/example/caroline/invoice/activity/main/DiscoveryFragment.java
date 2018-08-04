package com.example.caroline.invoice.activity.main;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by asus1 on 2018/4/9.
 */

public class DiscoveryFragment extends Fragment {

    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    public static HomeFragment newInstance(String name){
        Bundle args=new Bundle();
        args.putString("name",name);
        HomeFragment fragment=new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
