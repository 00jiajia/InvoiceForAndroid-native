package com.example.caroline.invoice.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.caroline.invoice.R;
import com.example.caroline.invoice.activity.main.LoginActivity;
import com.example.caroline.invoice.model.BillingSettingInfo;
import com.example.caroline.invoice.model.QuotaInfo;
import com.example.caroline.invoice.util.BasicUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddBillingLimitationActivity extends BasicActivity {

    private ListView listView;
    private Button button_save;
    private Button button_cancel;
    private List<Map<String,Object>> mapList=new ArrayList<Map<String,Object>>();
    private List<BillingSettingInfo> billingSettingInfos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_billing_limitation);
        Init();

    }

    private void Init(){
        listView=(ListView) findViewById(R.id.list_billings);
        View head=View.inflate(this,R.layout.billing_listview_head,null);
        listView.addHeaderView(head);

        AsyncTask<String,Void,List<BillingSettingInfo>> asyncTask=new AsyncTask<String, Void, List<BillingSettingInfo>>() {
            @Override
            protected void onPreExecute(){
                super.onPreExecute();
            }
            @Override
            protected List<BillingSettingInfo> doInBackground(String... strings) {
                billingSettingInfos= initService.queryForBillingSetting(BasicUrl.billingSettingUrl);
                return billingSettingInfos;
            }
            @Override
            protected void onPostExecute(List<BillingSettingInfo> list){
                SpinnerBillingAdapter spinnerBillingAdapter=new SpinnerBillingAdapter(AddBillingLimitationActivity.this,list,R.layout.billing_listview_item);
                listView.setAdapter(spinnerBillingAdapter);
            }
        };
        asyncTask.execute(BasicUrl.billingSettingUrl);

        button_save=(Button) findViewById(R.id.invoice_next);
        button_cancel=(Button) findViewById(R.id.invoice_delete);

        //保存
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(BillingSettingInfo _info:billingSettingInfos){
                    if(_info.getKpoption()<=0) _info.setKpoption(_info.getKps().get(0).getKPXE());
                }
                //然后将这些参数存储到数据库里面。
                Intent intent=new Intent(AddBillingLimitationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //取消[退出整个应用程序，然后发送一条记录，表示当前添加的所有的数据进行取消]
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //向服务器发送一个信息，并且将所有的信息全部销毁掉
                Runtime.getRuntime().exit(0);
            }
        });
    }
}
