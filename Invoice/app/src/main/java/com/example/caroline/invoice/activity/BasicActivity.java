package com.example.caroline.invoice.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.caroline.invoice.service.InitService;


public class BasicActivity  extends AppCompatActivity {


    protected InitService initService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initService=new InitService();

    }
}
