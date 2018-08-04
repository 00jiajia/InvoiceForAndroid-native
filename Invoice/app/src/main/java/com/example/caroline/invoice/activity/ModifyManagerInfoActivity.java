package com.example.caroline.invoice.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.caroline.invoice.R;
import com.example.caroline.invoice.model.DrawerInfo;
import com.example.caroline.invoice.model.IndustryInfo;
import com.example.caroline.invoice.model.QuestionInfo;
import com.example.caroline.invoice.service.InitService;
import com.example.caroline.invoice.util.BasicUrl;

import java.util.ArrayList;
import java.util.List;

//修改管理员信息
public class ModifyManagerInfoActivity extends BasicActivity {

    private EditText edit_name;
    private EditText edit_pass;
    private EditText edit_pass_answer;
    private Spinner spinner_request;
    private DrawerInfo admin_info;
    private List<QuestionInfo> questionInfos;
    private Button button_sure;
    private Button button_cancel;
    private int _position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_manager_info);

        Intent newDrawer_intent=getIntent();
        Bundle bundle=newDrawer_intent.getExtras();
        admin_info=(DrawerInfo) bundle.getSerializable("modify_drawer");

        button_sure=(Button) findViewById(R.id.modify_admin_sure);
        button_cancel=(Button) findViewById(R.id.modify_admin_cancel);
        edit_name=(EditText) findViewById(R.id.admin_username);
        edit_pass=(EditText) findViewById(R.id.admin_pass);
        edit_pass_answer=(EditText) findViewById(R.id.admin_pass_pass);
        spinner_request=(Spinner) findViewById(R.id.admin_pass_req);


        AsyncTask<String,Void,List<QuestionInfo>> asyncTask=new AsyncTask<String, Void, List<QuestionInfo>>() {
            @Override
            protected void onPreExecute(){
                super.onPreExecute();
            }
            @Override
            protected List<QuestionInfo> doInBackground(String... params) {
                List<QuestionInfo> list=initService.queryForQuestions(params[0]);
                return list;
            }
            @Override
            protected void onPostExecute(List<QuestionInfo> list){
                questionInfos=list;
                ArrayList<String> temp= new ArrayList<>();
                for(QuestionInfo info:list){
                    temp.add(info.getMMWT());
                }
                String[] questionItems=(String[]) temp.toArray(new String[list.size()]);
                final ArrayAdapter<String> industryAdapter=new ArrayAdapter<String>(ModifyManagerInfoActivity.this,android.R.layout.simple_spinner_item,questionItems);
                industryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_request.setAdapter(industryAdapter);
            }

        };
        asyncTask.execute(BasicUrl.questionUrl);

        spinner_request.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                _position=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //确认
        button_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String temp_name=edit_name.getText().toString();
                String temp_password=edit_pass.getText().toString();
                String temp_pass_answer=edit_pass_answer.getText().toString();
                if(temp_name==null||temp_name.length()<=0){
                    Toast.makeText(ModifyManagerInfoActivity.this,"请填写姓名",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(temp_pass_answer==null||temp_pass_answer.length()<=0){
                    Toast.makeText(ModifyManagerInfoActivity.this,"请填写密码",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(temp_password==null||temp_password.length()<=0){
                    Toast.makeText(ModifyManagerInfoActivity.this,"请填写密码答案",Toast.LENGTH_SHORT).show();
                    return;
                }if(_position<0){
                    Toast.makeText(ModifyManagerInfoActivity.this,"请选择密码问题",Toast.LENGTH_SHORT).show();
                    return;
                }

//                admin_info.setXM(temp_name);
//                admin_info.setPASSWORD(temp_password);
//                admin_info.setMMWTID(questionInfos.get(_position).getMMWTID());
//                admin_info.setMMWTDA(temp_pass_answer);
                //把这些信息发送到服务器上

                Intent intent=new Intent(ModifyManagerInfoActivity.this,AddDrawerActivity.class);
                Bundle bundle1=new Bundle();
                bundle1.putString("admin_pass_answer",temp_pass_answer);
                intent.putExtras(bundle1);
                startActivity(intent);
                finish();
            }
        });

        //取消
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ModifyManagerInfoActivity.this,AddDrawerActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
