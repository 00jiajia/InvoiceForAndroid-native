package com.example.caroline.invoice.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;


/**
 * Created by asus1 on 2018/3/20.
 */

public class HttpUtil {


    public static void sendHttpRequest(final String address,final HttpCallbackListener listener){
        new Thread(new Runnable() {

            @Override
            public void run() {
                HttpURLConnection connection=null;
                try {
                    URL url = new URL(address);
                    connection=(HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(8000);
                    InputStream in=connection.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(in));
                    StringBuilder builder=new StringBuilder();
                    String line;
                    while((line=reader.readLine())!=null){
                        builder.append(line);
                    }
                    if(listener!=null){
                        listener.onFinish(builder.toString());
                    }
                } catch (Exception e) {
                    Log.d("ERROR", e.getMessage());
                    if(listener!=null){
                        listener.onError(e);
                    }
                }finally{
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    public static void sendHttpRequestForPost(final String address, final String str_info, final HttpCallbackListener listener){

        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpURLConnection connection=null;

                try{
                    URL url=new URL(address);
                    connection=(HttpURLConnection) url.openConnection();
                    connection.setRequestProperty("Accept","application/json");
                    connection.setReadTimeout(8000);
                    connection.setRequestMethod("POST");
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.getOutputStream().write(str_info.getBytes());


                    InputStream in=connection.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(in));
                    StringBuilder builder=new StringBuilder();
                    String line;
                    while((line=reader.readLine())!=null){
                        builder.append(line);
                    }
                    if(listener!=null){
                        listener.onFinish(builder.toString());
                    }

                }catch (Exception e) {
                    Log.d("ERROR", e.getMessage());
                    if(listener!=null){
                        listener.onError(e);
                    }
                }finally{
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }


}
