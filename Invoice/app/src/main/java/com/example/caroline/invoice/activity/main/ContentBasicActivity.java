package com.example.caroline.invoice.activity.main;


import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;


import com.example.caroline.invoice.R;

public class ContentBasicActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private Fragment[]mFragmensts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_basic);

        mFragmensts = DataGenerator.getFragments("TabLayout Tab");

        initView();



    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.bottom_tab_layout);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                onTabItemSelected(tab.getPosition());

                //改变Tab 状态
                for(int i=0;i< mTabLayout.getTabCount();i++){
                    if(i == tab.getPosition()){
                        mTabLayout.getTabAt(i).setIcon(getResources().getDrawable(DataGenerator.mTabResPressed[i]));
                    }else{
                        mTabLayout.getTabAt(i).setIcon(getResources().getDrawable(DataGenerator.mTabRes[i]));
                    }
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mTabLayout.addTab(mTabLayout.newTab().setIcon(getResources().getDrawable(R.mipmap.home)).setText(DataGenerator.mTabTitle[0]));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(getResources().getDrawable(R.mipmap.other)).setText(DataGenerator.mTabTitle[1]));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(getResources().getDrawable(R.mipmap.invoice)).setText(DataGenerator.mTabTitle[2]));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(getResources().getDrawable(R.mipmap.me)).setText(DataGenerator.mTabTitle[3]));

    }

    private void onTabItemSelected(int position){
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = mFragmensts[0];
                break;
            case 1:
                fragment = mFragmensts[1];
                break;

            case 2:
                fragment = mFragmensts[2];
                break;
            case 3:
                fragment = mFragmensts[3];
                break;
        }


        if(fragment!=null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.home_container,fragment).commit();
        }
    }


}
