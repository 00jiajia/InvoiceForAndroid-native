package com.example.caroline.invoice.activity.main;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.caroline.invoice.R;

/**
 * Created by asus1 on 2018/4/9.
 */

public class DataGenerator {
    public static final int []mTabRes = new int[]{R.mipmap.home,R.mipmap.other,R.mipmap.invoice,R.mipmap.me};
    public static final int []mTabResPressed = new int[]{R.mipmap.home1,R.mipmap.other1,R.mipmap.invoice1,R.mipmap.me1};
    public static final String []mTabTitle = new String[]{"菜单","其他","发票","我的"};

    public static Fragment[] getFragments(String from){
        Fragment fragments[] = new Fragment[4];
        fragments[0] = HomeFragment.newInstance(from);//菜单页
        fragments[1] = DiscoveryFragment.newInstance(from);//其他
        fragments[2] = AttentionFragment.newInstance(from);//发票
        fragments[3] = ProfileFragment.newInstance(from);//首页
        return fragments;
    }

    /**
     * 获取Tab 显示的内容
     * @param context
     * @param position
     * @return
     */
    public static View getTabView(Context context, int position){
        //这个地方还是有问题的
        View view = LayoutInflater.from(context).inflate(R.layout.home_tab_content,null);
        ImageView tabIcon = (ImageView) view.findViewById(R.id.tab_content_image);
        tabIcon.setImageResource(DataGenerator.mTabRes[position]);
        TextView tabText = (TextView) view.findViewById(R.id.tab_content_text);
        tabText.setText(mTabTitle[position]);
        return view;
    }
}
