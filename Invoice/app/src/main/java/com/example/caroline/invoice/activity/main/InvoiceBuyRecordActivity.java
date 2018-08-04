package com.example.caroline.invoice.activity.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.caroline.invoice.R;

import java.util.List;

public class InvoiceBuyRecordActivity extends AppCompatActivity {

    private ListView listView;
    private Button button_add_invoice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_buy_record);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        listView=(ListView) findViewById(R.id.content_invoice_buy_list_view_invoice);
        View head=View.inflate(this,R.layout.content_invoice_buy_record_head,null);;
        listView.addHeaderView(head);

        //绑定数据
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        //添加
        button_add_invoice=(Button) findViewById(R.id.content_invoice_buy_button_add_invoice);
        button_add_invoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}
