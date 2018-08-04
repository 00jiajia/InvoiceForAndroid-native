package com.example.caroline.invoice.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caroline.invoice.R;
import com.example.caroline.invoice.model.DrawerInfo;
import com.example.caroline.invoice.model.IndustryInfo;
import com.example.caroline.invoice.model.RoleInfo;
import com.example.caroline.invoice.util.BasicUrl;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

//修改新增开票人信息
public class ModifyNewDrawerActivity extends BasicActivity {

    private DrawerInfo info;
    private Button button_save;
    private Button button_cancel;
    private EditText edit_login_name;
    private EditText edit_name;
    private EditText edit_pass;
    private TextView view_img_upload;
    private Spinner spinner_role;
    private List<RoleInfo> roleInfos;
    private int _position;
    private int info_position=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_new_drawer);

        //这个地方是有问题的，因为这个里面有一部分数据是重复的

        Intent newDrawer_intent=getIntent();
        Bundle bundle=newDrawer_intent.getExtras();
        if(bundle!=null){
            info=(DrawerInfo) bundle.getSerializable("modify_drawer");
            info_position=bundle.getInt("modify_key");
        }

        button_save=(Button) findViewById(R.id.modify_drawer_save);
        button_cancel=(Button) findViewById(R.id.modify_drawer_cancel);

        edit_login_name=(EditText) findViewById(R.id.modify_drawer_login_name);
        edit_name=(EditText) findViewById(R.id.modify_drawer_name);
        edit_pass=(EditText) findViewById(R.id.modify_drawer_pass);
        view_img_upload=(TextView) findViewById(R.id.modify_drawer_img_upload);

        if(info!=null&&info.getXM().length()>0){
            edit_name.setText(info.getXM());
            edit_login_name.setText(info.getDLM());
            edit_pass.setText(info.getPASSWORD());
        }

        //spinner 如果设置展示的标签位置

        spinner_role=(Spinner) findViewById(R.id.modify_drawer_role);
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
            protected void onPostExecute(List<RoleInfo> list){
                ArrayList<String> temp= new ArrayList<>();
                for(RoleInfo info:list){
                    temp.add(info.getJSMC());
                }
                String[]  roleItems=(String[]) temp.toArray(new String[list.size()]);
                final ArrayAdapter<String> roleyAdapter=new ArrayAdapter<String>(ModifyNewDrawerActivity.this,android.R.layout.simple_spinner_item,roleItems);

                spinner_role.setAdapter(roleyAdapter);
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



        view_img_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ModifyNewDrawerActivity.this, "开始上传", Toast.LENGTH_SHORT).show();
            }
        });
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit_login_name.getText().toString().length()<=0){
                    Toast.makeText(ModifyNewDrawerActivity.this,"请输入登录名",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(edit_name.getText().toString().length()<=0){
                    Toast.makeText(ModifyNewDrawerActivity.this,"请输入姓名",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(edit_pass.getText().toString().length()<=0){
                    Toast.makeText(ModifyNewDrawerActivity.this,"请录入登录密码",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(_position<0){
                    Toast.makeText(ModifyNewDrawerActivity.this,"请选择角色",Toast.LENGTH_SHORT).show();
                    return;
                }

                info.setXM(edit_name.getText().toString());
                info.setDLM(edit_login_name.getText().toString());
                info.setPASSWORD(edit_pass.getText().toString());
                info.setKPRJS(roleInfos.get(_position).getJSID());

                //这个
                Intent intent=new Intent(ModifyNewDrawerActivity.this,AddDrawerActivity.class);
                Bundle bundle1=new Bundle();
                bundle1.putSerializable("modify_new_drawer",info);
                bundle1.putInt("modify_key",info_position);
                intent.putExtras(bundle1);
                startActivity(intent);
                finish();

            }
        });

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ModifyNewDrawerActivity.this,AddDrawerActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
