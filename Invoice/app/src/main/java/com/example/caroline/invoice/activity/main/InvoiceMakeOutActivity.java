package com.example.caroline.invoice.activity.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.caroline.invoice.R;

public class InvoiceMakeOutActivity extends AppCompatActivity {

    private ListView listView;
    private Button button_for_all;
    private Button button_normal;
    private Button button_refund;
    private Button button_invalid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_make_out);
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

        listView=(ListView) findViewById(R.id.content_invoice_make_out_view_invoice);
        View head=View.inflate(this,R.layout.content_invoice_make_out_head,null);
        listView.addHeaderView(head);

        /*
        * 这个部分是(异步)加载初始化数据的{这个可以做成一个公共的数据}
        *
        *
        * */


        button_for_all=(Button) head.findViewById(R.id.make_out_button_for_all);
        button_normal=(Button) head.findViewById(R.id.make_out_button_noraml);
        button_refund=(Button) head.findViewById(R.id.make_out_button_refund);
        button_invalid=(Button) head.findViewById(R.id.make_out_button_invalid);

        //搜索全部
        button_for_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //搜索正常
        button_normal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });
        //搜索废票
        button_invalid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //搜索退票
        button_refund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

}
