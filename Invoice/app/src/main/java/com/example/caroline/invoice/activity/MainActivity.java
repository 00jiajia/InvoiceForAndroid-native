package com.example.caroline.invoice.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.caroline.invoice.R;
import com.example.caroline.invoice.activity.main.CentreActivity;
import com.example.caroline.invoice.model.BasicInfo;
import com.example.caroline.invoice.model.BillingTypeInfo;
import com.example.caroline.invoice.model.IndustryInfo;
import com.example.caroline.invoice.service.InitService;
import com.example.caroline.invoice.util.BasicUrl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class MainActivity extends BasicActivity {
    private EditText edit_username;
    private EditText edit_identificationCode;
    private Button button_save;
    private Button button_cancel;
    private Spinner spinner_industry;
    private Spinner spinner_billingType;
    private int position_industry=-1;
    private int position_billing=-1;
    private boolean isIndustry_Spinner_first=true;
    private boolean isType_Spinner_first=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        clear();
        SharedPreferences preferences=getSharedPreferences("data",MODE_PRIVATE);
        String username=preferences.getString("username","");
        String password=preferences.getString("password","");

        if(username.length()>0&&password.length()>0){
            Intent intent=new Intent(MainActivity.this, CentreActivity.class);
            startActivity(intent);
            finish();
        }
        setContentView(R.layout.activity_main);

        Init_is_need_Init();

        edit_username=(EditText) findViewById(R.id.editText_username);
        edit_identificationCode=(EditText) findViewById(R.id.editText_identificationCode);

        Init_button();
        Init_Spinner();

    }
    public void clear() {
        SharedPreferences preferences=getSharedPreferences("data",MODE_PRIVATE);
        //SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();

        editor.commit();
    }

    private void Init_is_need_Init(){
        //这个地方应该获取一下，本机的机器码，然后配上一个登录人
        //如果如果匹配得上的话，就直接跳过(这个方式可采用两种：1.服务端2.写入到cookie里面，存储到本机上)
    }
    private void Init_button(){
        button_save=(Button) findViewById(R.id.save);
        button_cancel=(Button) findViewById(R.id.cancel);
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(position_billing<0){
                    Toast.makeText(MainActivity.this,"请选择开票种类",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(position_industry<0){
                    Toast.makeText(MainActivity.this,"请选择行业分类",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(edit_username.getText().toString().length()<=0){
                    Toast.makeText(MainActivity.this,"请填写纳税人名称",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(edit_identificationCode.getText().toString().length()<=0){
                    Toast.makeText(MainActivity.this,"请填写纳税人识别码",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(edit_identificationCode.getText().toString().length()!=15){
                    Toast.makeText(MainActivity.this,"请填写纳税人识别码只能为15位",Toast.LENGTH_SHORT).show();
                    return;
                }
                    /*BasicInfo info=new BasicInfo();
                    info.setHYFL(infos_industry.get(position_industry).getHyflid());
                    info.setKPZL(infos_billing.get(position_billing).getKpzlid());
                    info.setNSRMC(edit_username.getText().toString());
                    info.setNSRSBM(edit_identificationCode.getText().toString());
                    info.setZJBZ("00");
                    initService.SaveInitInfo(BasicUrl.saveBasicInfoUrl,info);*/
                Intent intent=new Intent(MainActivity.this,AddDrawerActivity.class);
                startActivity(intent);
                finish();
            }
        });

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_username.setText("");
                edit_identificationCode.setText("");
                position_industry=-1;
                position_billing=-1;
            }
        });

    }

    private void Init_Spinner(){

        spinner_billingType=(Spinner) findViewById(R.id.spinner_ticketType);
        spinner_industry=(Spinner) findViewById(R.id.spinner_industryClassification);

        AsyncTask<String,Void,List<IndustryInfo>> asyncIndustry=new AsyncTask<String, Void, List<IndustryInfo>>() {
            @Override
            protected void onPreExecute(){
                super.onPreExecute();
            }
            @Override
            protected List<IndustryInfo> doInBackground(String... params) {
                List<IndustryInfo> list=initService.queryForIndustryTypeInfos(params[0]);
                return list;
            }
            @Override
           protected void onPostExecute(List<IndustryInfo> list){
                String[] industryItems=new String[list.size()];
                ArrayList<String> temp= new ArrayList<>();
                for(IndustryInfo info:list){
                    temp.add(info.getHyflmc());
                }
                industryItems=(String[]) temp.toArray(new String[list.size()]);
                final ArrayAdapter<String> industryAdapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,industryItems);

                spinner_industry.setAdapter(industryAdapter);
                //spinner_industry.setSelection(-100,true);
            }
        };
        asyncIndustry.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,BasicUrl.industryUrl);

        AsyncTask<String,Void,List<BillingTypeInfo>> asyncType=new AsyncTask<String, Void, List<BillingTypeInfo>>() {
            @Override
            protected void onPreExecute(){
                super.onPreExecute();
            }

            @Override
            protected List<BillingTypeInfo> doInBackground(String... params) {
                List<BillingTypeInfo> list=initService.queryForBillingTypeInfos(params[0]);
                return list;
            }
            @Override
            protected void onPostExecute(List<BillingTypeInfo> list){
               String[] billingTypeItems=new String[list.size()];
                ArrayList<String> tempa= new ArrayList<>();
                for(BillingTypeInfo info :list){
                    tempa.add(info.getKpzlmc());
                }
                billingTypeItems= (String[]) tempa.toArray(new String[list.size()]);
                ArrayAdapter<String> billingTypeAdapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,billingTypeItems);
                spinner_billingType.setAdapter(billingTypeAdapter);
            }
        };
        asyncType.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,BasicUrl.billingTypeUrl);

        spinner_industry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                position_industry=position;
                if(isIndustry_Spinner_first){
                    view.setVisibility(View.INVISIBLE);
                }
                isIndustry_Spinner_first=false;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        spinner_billingType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                position_billing=position;
                if(isType_Spinner_first){
                    view.setVisibility(View.INVISIBLE);
                }
                isType_Spinner_first=false;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
