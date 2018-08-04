package com.example.caroline.invoice.util;

import android.net.Uri;
import android.widget.Toast;

import com.example.caroline.invoice.activity.main.FileUploadActivity;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by asus1 on 2018/5/18.
 */

public class FileUploadUtil {
    public static String sendFile(String urlPath, Uri filePath,
                                  String newName,OnUploadListener listener) throws Exception {

        FileInputStream fileInputStream = new FileInputStream(new File(filePath.getPath()));

        String end = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesAvailable,bytesRead;
        int maxBufferSize = 1 * 1024 * 1024;

        URL url = new URL(urlPath);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        //下载需要将setDoInput方法的参数值设为true
        con.setDoInput(true);
        //上传需要将setDoOutput方法的参数值设为true
        con.setDoOutput(true);
        //禁止HttpURLConnection使用缓存
        con.setUseCaches(false);
        //使用POST请求，必须大写
        con.setRequestMethod("POST");
        //以下三行设置http请求头信息
        con.setRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("ENCTYPE", "multipart/form-data");
        //con.setRequestProperty("Charset", "UTF-8");
        //在模拟web页面向服务器端上传文件时，每个文件的开头需要有一个分界符，
        //分界符需要在http请求头中指定。boundary是任意一个字符串，一般为******
        con.setRequestProperty("Content-Type", "multipart/form-data;boundary="
                + boundary);
        con.setRequestProperty("file", newName);

        DataOutputStream ds = new DataOutputStream(con.getOutputStream());

        ds.writeBytes(twoHyphens + boundary + end);
        //上传文件相关信息，这些信息包括请求参数名，上传文件名，文件类型，但并不限于此
        ds.writeBytes("Content-Disposition: form-data; "
                + "name=\"file\";filename=\"" + newName + "\"" + end);
        ds.writeBytes(end);

        //获得文件的输入流，通过流传输文件。在这里我重写了FileInputStream，为了监听上传进度
//        CustomFileInputStream fStream = new CustomFileInputStream(filePath.getPath());
//        fStream.setOnUploadListener(listener);
        bytesAvailable = fileInputStream.available();

        /* 设置每次写入1024bytes */
        int bufferSize = Math.min(bytesAvailable, maxBufferSize);;
        byte[] buffer = new byte[bufferSize];

        bytesRead = fileInputStream.read(buffer, 0, bufferSize);

        //int length = -1;
        // 从文件读取数据至缓冲区
//        while ((length = fStream.read(buffer)) != -1) {
//            //将资料写入DataOutputStream中
//            ds.write(buffer, 0, length);
//        }

        while (bytesRead > 0) {

            ds.write(buffer, 0, bufferSize);
            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
        }

        ds.writeBytes(end);
        ds.writeBytes(twoHyphens + boundary + twoHyphens + end);




        int serverResponseCode = con.getResponseCode();
        String serverResponseMessage = con.getResponseMessage();


        if(serverResponseCode == 200){

        }

        ds.close();
        ds.flush();

        //上传完成以后获取服务器的反馈
//        InputStream is = con.getInputStream();
//        int ch;
//        StringBuffer b = new StringBuffer();
//        while ((ch = is.read()) != -1) {
//            b.append((char) ch);
//        }
//
//        ds.close();
//        return b.toString();
        return null;
    }
}
