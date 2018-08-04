package com.example.caroline.invoice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.caroline.invoice.R;
import com.example.caroline.invoice.model.DrawerInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//添加开票人
public class AddDrawerActivity extends BasicActivity {

    private Button button_save;
    private Button button_delete;
    private Button button_add_drawer;
    private static ArrayList<DrawerInfo> drawerInfos;
    private ListView listView;
    private List<Map<String,Object>> mapList=new ArrayList<Map<String,Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drawer);

        listView=(ListView)findViewById(R.id.add_drawer_list);

        Intent newDrawer_intent=getIntent();
        Bundle bundle=newDrawer_intent.getExtras();
        DrawerInfo _info=null;
        if(bundle!=null){
            String pass_answer=(String) bundle.getSerializable("admin_pass_answer");
            if(pass_answer!=null&&pass_answer.length()>0){
                drawerInfos.get(0).setMMWTDA(pass_answer);
            }
            _info =(DrawerInfo) bundle.getSerializable("new_drawer");
            DrawerInfo _temp_info=(DrawerInfo) bundle.getSerializable("modify_new_drawer");
            if(_temp_info!=null){
                int temp_position=bundle.getInt("modify_key");
                DrawerInfo temp_info_=drawerInfos.get(temp_position);
                temp_info_.setXM(_temp_info.getXM());
                temp_info_.setPASSWORD(_temp_info.getPASSWORD());
                temp_info_.setKPRJS(_temp_info.getKPRJS());
                temp_info_.setDLM(_temp_info.getDLM());
            }
        }

        if(drawerInfos==null||drawerInfos.size()<=0){
            drawerInfos=new ArrayList<DrawerInfo>();
            DrawerInfo info=new DrawerInfo();
            info.setDLM("admin");
            info.setXM("企业管理员");
            drawerInfos.add(info);
        }

        if(_info!=null&&_info.getXM().length()>0){
            drawerInfos.add(_info);
        }

        for(DrawerInfo _info_:drawerInfos){
            Map<String,Object> item=new HashMap<String,Object>();
            item.put("login_name",_info_.getDLM());
            item.put("login_role","企业管理员");
            item.put("account_name",_info_.getXM());
            mapList.add(item);
        }

        View head=View.inflate(this,R.layout.drawer_listview_head,null);
        listView.addHeaderView(head);

        SimpleAdapter adapter = new SimpleAdapter(this,mapList, R.layout.drawer_listview_item,
                new String[]{"login_name","login_role","account_name"},
                new int[]{R.id.drawer_item_login_name,R.id.drawer_item_role,R.id.drawer_item_name});

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle1=new Bundle();
                bundle1.putSerializable("modify_drawer",drawerInfos.get(position-1));
                bundle1.putInt("modify_key",position-1);
                if(position==1){
                    Intent intent=new Intent(AddDrawerActivity.this,ModifyManagerInfoActivity.class);
                    intent.putExtras(bundle1);
                    startActivity(intent);
                }else if(position>1){
                    Intent intent=new Intent(AddDrawerActivity.this,ModifyNewDrawerActivity.class);
                    intent.putExtras(bundle1);
                    startActivity(intent);
                }
            }
        });

        button_save=(Button) findViewById(R.id.drawer_next);
        button_delete=(Button) findViewById(R.id.delete_drawer);
        button_add_drawer=(Button) findViewById(R.id.add_new_drawer_one);

        button_add_drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddDrawerActivity.this,AddNewDrawerActivity.class);
                startActivity(intent);
            }
        });
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerInfos.size()<2){
                    Toast.makeText(AddDrawerActivity.this,"请新增加一个开票人账户",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(drawerInfos.get(0).getMMWTDA()==null||drawerInfos.get(0).getMMWTDA().length()<=0){
                    Toast.makeText(AddDrawerActivity.this,"请修改系统管理员的密码，长度至少6位",Toast.LENGTH_SHORT).show();
                    return;
                }


                Intent intent=new Intent(AddDrawerActivity.this,AddInitDataActivity.class);
                startActivity(intent);
                finish();
            }
        });

        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //做一个删除记录的请求操作
            }
        });


}}
