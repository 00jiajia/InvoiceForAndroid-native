package com.example.caroline.invoice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.caroline.invoice.R;
import com.example.caroline.invoice.model.InvoiceInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//添加期初数据
public class AddInitDataActivity extends BasicActivity {

    private Button button_save;
    private Button button_add;
    private Button button_delete;//这个按钮是有问题的
    private ListView listView;
    private List<Map<String,Object>> mapList=new ArrayList<Map<String,Object>>();
    private List<InvoiceInfo> invoiceInfos=new ArrayList<InvoiceInfo>();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_init_data);

        Init();
    }

    private void Init(){
        invoiceInfos=(ArrayList<InvoiceInfo>)getIntent().getSerializableExtra("invoice_list");

        listView=(ListView)findViewById(R.id.init_data_list);
        View head=View.inflate(this,R.layout.initdata_listview_head,null);
        listView.addHeaderView(head);

        if(invoiceInfos!=null&&invoiceInfos.size()>0){
            for(InvoiceInfo _info:invoiceInfos){
                Map<String,Object> item=new HashMap<String,Object>();
                item.put("invoice_code",_info.getFPDM());
                item.put("invoice_start_code",_info.getFPHS());
                item.put("invoice_end_code",_info.getFPHZ());
                item.put("invoice_count",_info.getFS());
                mapList.add(item);
            }
        }

        SimpleAdapter adapter = new SimpleAdapter(this,mapList, R.layout.initdata_listview_item,
                new String[]{"invoice_code","invoice_start_code","invoice_end_code","invoice_count"},
                new int[]{R.id.init_data_item_invoiceCode,R.id.init_data_item_startCode,R.id.init_data_item_endCode,R.id.init_data_item_count});
        listView.setAdapter(adapter);


        button_add=(Button) findViewById(R.id.add_init_data);
        button_save=(Button) findViewById(R.id.init_data_save);
        button_delete=(Button) findViewById(R.id.delete_init_data);
        //添加
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(AddInitDataActivity.this,AddInitDataItemActivity.class);
                intent.putExtra("invoice_list",(Serializable) invoiceInfos);
                startActivity(intent);
            }
        });

        //删除
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //保存
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //if invoiceInfos 不为空的话，将数据发送到服务端
                Intent intent=new Intent(AddInitDataActivity.this,AddBillingLimitationActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
