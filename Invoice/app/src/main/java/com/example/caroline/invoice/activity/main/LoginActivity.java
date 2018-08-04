package com.example.caroline.invoice.activity.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caroline.invoice.R;
import com.example.caroline.invoice.activity.BasicActivity;

public class LoginActivity extends BasicActivity {

    private Button button_login;
    private TextView textView_login_callback_pass;
    private EditText edit_login_user_name;
    private EditText edit_login_user_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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


        button_login=(Button) findViewById(R.id.button_login);
        textView_login_callback_pass=(TextView) findViewById(R.id.login_callback_pass);
        edit_login_user_name=(EditText) findViewById(R.id.login_user_name);
        edit_login_user_pass=(EditText) findViewById(R.id.login_user_password);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //将用户名和密码存储到本地的文本中，并且与服务器上的用户名和密码做一个比对
                if(edit_login_user_name.getText().toString().length()<=0){
                    Toast.makeText(LoginActivity.this,"请输入用户名",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(edit_login_user_pass.getText().toString().length()<=0){
                    Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                    return;
                }

                //1.做一个post的，服务器上的查询
                //2.并且将这个数据存储到本地，以防以后再进行查询的时候再初始化
                SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putString("username",edit_login_user_name.getText().toString().trim());
                editor.putString("password",edit_login_user_pass.getText().toString().trim());
                editor.apply();

                //Intent intent=new Intent(LoginActivity.this,ContentBasicActivity.class);
                Intent intent=new Intent(LoginActivity.this,CentreActivity.class);
                startActivity(intent);
                finish();
            }
        });

        textView_login_callback_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this,"请稍后",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
