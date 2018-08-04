package com.example.caroline.invoice.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.caroline.invoice.R;
import com.example.caroline.invoice.model.InvoiceInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//添加发票记录
public class AddInitDataItemActivity extends AppCompatActivity {

    private Button button_save;
    private Button button_cancel;
    private EditText edit_invoice_code;
    private EditText edit_start_code;
    private EditText edit_end_code;
    private EditText edit_count;
    private List<InvoiceInfo> invoiceInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        invoiceInfos=(ArrayList<InvoiceInfo>)getIntent().getSerializableExtra("invoice_list");

        if(invoiceInfos==null) invoiceInfos=new ArrayList<InvoiceInfo>();
        setContentView(R.layout.activity_add_init_data_item);

        edit_count=(EditText) findViewById(R.id.invoice_add_quantity);
        edit_invoice_code=(EditText) findViewById(R.id.invoice_add_code);
        edit_start_code=(EditText) findViewById(R.id.invoice_add_ticketNo_start);
        edit_end_code=(EditText) findViewById(R.id.invoice_add_ticketNo_end);
        button_save=(Button) findViewById(R.id.add_invoice);
        button_cancel=(Button) findViewById(R.id.cancel_invoice);

        //保存
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这个地方需要做一个信息的处理
                InvoiceInfo info=new InvoiceInfo();
                info.setFPDM(edit_invoice_code.getText().toString());
                info.setFPHS(edit_start_code.getText().toString());
                info.setFPHZ(edit_end_code.getText().toString());
                info.setFS(Integer.parseInt(edit_count.getText().toString()));
                invoiceInfos.add(info);
                //还缺少一个时间/日期
                //缺少一个方法将其发送到后端服务
                Intent intent=new Intent(AddInitDataItemActivity.this,AddInitDataActivity.class);
                intent.putExtra("invoice_list",(Serializable) invoiceInfos);
                startActivity(intent);
                finish();
            }
        });

        //取消
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddInitDataItemActivity.this,AddInitDataActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
