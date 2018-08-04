package com.example.caroline.invoice.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caroline.invoice.R;
import com.example.caroline.invoice.model.DrawerInfo;
import com.example.caroline.invoice.model.RoleInfo;
import com.example.caroline.invoice.util.BasicUrl;

import java.util.ArrayList;
import java.util.List;


//添加开票人
public class AddNewDrawerActivity extends BasicActivity {

    private ImageView imgView;
    private TextView textView;
    private Button button_sure;
    private Button button_cancel;
    private EditText edit_login_name;
    private EditText edit_name;
    private EditText edit_pass;
    private Spinner spinner_role;
    private int _position;
    private List<RoleInfo> roleInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_drawer);
        initView();

    }

    private void initView(){
        roleInfos=new ArrayList<RoleInfo>();
        imgView=(ImageView) findViewById(R.id.admin_png);
        textView=(TextView) findViewById(R.id.img_upload);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddNewDrawerActivity.this, "开始上传", Toast.LENGTH_SHORT).show();

                //这个地方需要实现一个图片上传的功能
            }
        });
        edit_login_name=(EditText) findViewById(R.id.add_drawer_login_name);
        edit_name=(EditText) findViewById(R.id.add_drawer_name);
        edit_pass=(EditText) findViewById(R.id.add_drawer_pass);
        spinner_role=(Spinner) findViewById(R.id.add_drawer_role);

        AsyncTask<String,Void,List<RoleInfo>> asyncTask=new AsyncTask<String, Void, List<RoleInfo>>() {
            @Override
            protected void onPreExecute(){
                super.onPreExecute();
            }
            @Override
            protected List<RoleInfo> doInBackground(String... params) {
                roleInfos=initService.queryForJs(params[0]);
                return roleInfos;
            }
            @Override
            protected void onPostExecute(List<RoleInfo> list){
                        ArrayList<String> temp = new ArrayList<>();
                        for (RoleInfo info : list) {
                            temp.add(info.getJSMC());
                        }
                        String[] roleItems = (String[]) temp.toArray(new String[list.size()]);
                        ArrayAdapter<String> industryAdapter = new ArrayAdapter<String>(AddNewDrawerActivity.this, android.R.layout.simple_spinner_item, roleItems);
                        industryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_role.setAdapter(industryAdapter);
            }
        };

        asyncTask.execute(BasicUrl.kprjsUrl);

        spinner_role.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                _position=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        button_sure=(Button) findViewById(R.id.new_drawer_sure);
        button_cancel=(Button) findViewById(R.id.new_drawer_cancel);

        //确定
        button_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这边做两步操作，一个是将添加的对象传回到服务器，另外一个操作并将这个对象返回给主界面

                if(edit_login_name.getText().toString().length()<=0){
                    Toast.makeText(AddNewDrawerActivity.this,"请填写登录名",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(edit_name.getText().toString().length()<=0){
                    Toast.makeText(AddNewDrawerActivity.this,"请填写姓名",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(edit_pass.getText().toString().length()<=0){
                    Toast.makeText(AddNewDrawerActivity.this,"请填密码",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(_position<0){
                    Toast.makeText(AddNewDrawerActivity.this,"请填选择角色",Toast.LENGTH_SHORT).show();
                    return;
                }

                DrawerInfo info=new DrawerInfo();
                info.setDLM(edit_login_name.getText().toString().trim());
                info.setXM(edit_name.getText().toString().trim());
                info.setPASSWORD(edit_pass.getText().toString());
                info.setKPRJS(roleInfos.get(_position).getJSID());

                Intent intent=new Intent(AddNewDrawerActivity.this,AddDrawerActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("new_drawer",info);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddNewDrawerActivity.this,AddDrawerActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


}
