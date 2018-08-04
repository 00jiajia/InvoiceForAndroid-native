package com.example.caroline.invoice.activity.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.caroline.invoice.R;

import org.w3c.dom.Text;

public class ContentDetailActivity extends AppCompatActivity {
    private TextView text_invoice_input;
    private TextView text_invoice_verfiy;
    private TextView text_invoice_make_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_detail);
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

        //发票录入
        text_invoice_input=(TextView) findViewById(R.id.content_detail_invoice_input);
        text_invoice_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //发票簿校验加锁
        text_invoice_verfiy=(TextView) findViewById(R.id.content_detail_invoice_verify);
        text_invoice_verfiy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //开票
        text_invoice_make_out=(TextView) findViewById(R.id.content_detail_invoice_make_out);
        text_invoice_make_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
