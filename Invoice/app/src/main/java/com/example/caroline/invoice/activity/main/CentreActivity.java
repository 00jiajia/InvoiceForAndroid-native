package com.example.caroline.invoice.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.caroline.invoice.R;
import com.example.caroline.invoice.activity.BasicActivity;


public class CentreActivity extends BasicActivity {

    private Button button_main;
    private Button button_invoice_detail;
    private Button button_invoice_search;
    private Button button_refund;
    private Button button_invalid;
    private Button button_invoice_buy;
    private Button button_invoice_store;
    private Button button_invoice_mislead;
    private Button button_invoice_hand_in;
    private Button button_invoice_receive;
    private Button button_cargo;
    private Button button_client;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centre);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //首页
        button_main=(Button) findViewById(R.id.button_main);
        button_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CentreActivity.this,ContentDetailActivity.class);
                startActivity(intent);
            }
        });
        //开票详情
        button_invoice_detail=(Button) findViewById(R.id.button_invoice_detail);
        button_invoice_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(CentreActivity.this,InvoiceMakeOutActivity.class);
                startActivity(intent);

            }
        });
        //开票查询
        button_invoice_search=(Button) findViewById(R.id.button_search);
        button_invoice_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CentreActivity.this,InvoiceMakeOutActivity.class);
                startActivity(intent);
            }
        });

        //退票
        button_refund=(Button) findViewById(R.id.button_refund);
        button_refund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CentreActivity.this,InvoiceRefundActivity.class);
                startActivity(intent);
            }
        });

        //作废
        button_invalid=(Button) findViewById(R.id.button_invalid);
        button_invalid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CentreActivity.this,InvoiceInvalidActivity.class);
                startActivity(intent);
            }
        });
        //货品或项目
        button_cargo=(Button) findViewById(R.id.button_cargo);
        button_cargo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CentreActivity.this,CargoActivity.class);
                startActivity(intent);
            }
        });

        //客户
        button_client=(Button) findViewById(R.id.button_client);
        button_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CentreActivity.this,ClientActivity.class);
                startActivity(intent);
            }
        });

        //库存
        button_invoice_store=(Button) findViewById(R.id.button_store);
        button_invoice_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CentreActivity.this,InvoiceStoreActivity.class);
                startActivity(intent);
            }
        });

        //购票记录
        button_invoice_buy=(Button) findViewById(R.id.button_buy);
        button_invoice_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CentreActivity.this,InvoiceBuyRecordActivity.class);
                startActivity(intent);
            }
        });

        //缴销发票
        button_invoice_hand_in=(Button) findViewById(R.id.button_hand_in);
        button_invoice_hand_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CentreActivity.this,InvoiceHandInActivity.class);
                startActivity(intent);
            }
        });

        //遗失发票
        button_invoice_mislead=(Button) findViewById(R.id.button_mislaid);
        button_invoice_mislead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CentreActivity.this,InvoiceMislaidActivity.class);
                startActivity(intent);
            }
        });

        //收发记录
        button_invoice_receive=(Button) findViewById(R.id.button_receive);
        button_invoice_receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CentreActivity.this,InvoiceBuyRecordActivity.class);
                startActivity(intent);
            }
        });
    }

}
