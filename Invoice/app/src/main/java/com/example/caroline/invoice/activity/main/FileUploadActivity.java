package com.example.caroline.invoice.activity.main;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.caroline.invoice.R;
import com.example.caroline.invoice.activity.BasicActivity;
import com.example.caroline.invoice.util.FileUploadUtil;
import com.example.caroline.invoice.util.OnUploadListener;

import java.io.File;
import java.net.URISyntaxException;


public class FileUploadActivity  extends BasicActivity implements OnUploadListener {

    private Button open;
    private Button upload;
    private static final int FILE_SELECT_CODE = 0;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fileupload);
        open=(Button) findViewById(R.id.button_open_folder);
        upload=(Button) findViewById(R.id.button_upload);

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String path="\"/sdcard/Recorder_Videos\"";


//                String path=Environment.getExternalStorageDirectory().getPath()
//                        +  File.separator + "Ingkee" + File.separator;


                chooseFile();
            }
        });

    }


     public void chooseFile(){
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try{
            startActivityForResult(Intent.createChooser(intent, "请选择一个要上传的文件"),FILE_SELECT_CODE);
        }catch (Exception ex){
            Toast.makeText(this, "亲，木有文件管理器啊-_-!!", Toast.LENGTH_SHORT).show();
        }

     }

     @Override
     public void onActivityResult(int requestCode, int resultCode, Intent data){

         //Toast.makeText(this,"resultCode=====>"+resultCode,Toast.LENGTH_SHORT).show();
        if(resultCode== Activity.RESULT_OK){
           // Toast.makeText(this,"start",Toast.LENGTH_SHORT).show();
            Uri uri=data.getData();
            String url;
            //Toast.makeText(this,"uri---------->",Toast.LENGTH_SHORT).show();
            try{
                url=uri.getPath();
                String urlpath="http://192.168.1.215:8080/file/fileUpload";
                String fileName = url.substring(url.lastIndexOf("/") + 1);
                Toast.makeText(this,fileName,Toast.LENGTH_SHORT).show();
                //Toast.makeText(this,url,Toast.LENGTH_SHORT).show();

                if (android.os.Build.VERSION.SDK_INT > 9) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                }

                if (Build.VERSION.SDK_INT >= 23) {
                    int REQUEST_CODE_CONTACT = 101;
                    String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    //验证是否许可权限
                    for (String str : permissions) {
                        if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                            //申请权限
                            this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                        }
                    }
                }
                FileUploadUtil.sendFile(urlpath,uri,fileName,FileUploadActivity.this);

            }catch (Exception ex){
                Toast.makeText(this,ex.getMessage(),Toast.LENGTH_SHORT).show();
            }
//            super.onActivityResult(requestCode,resultCode,data);
//            return;
        }
        if(requestCode==FILE_SELECT_CODE){
            Uri uri=data.getData();
            //System.out.println(uri.getPath());

            //Toast.makeText(this,uri.getPath(),Toast.LENGTH_SHORT).show();
        }
        //super.onActivityResult(requestCode,resultCode,data);
     }

     private void UploadFile(String url){
        //File file=new File(url);

     }


//    public static String getPath(Context context, Uri uri) throws URISyntaxException {
//        if ("content".equalsIgnoreCase(uri.getScheme())) {
//            String[] projection = { "_data" };
//            Cursor cursor = null;
//            try {
//                cursor = context.getContentResolver().query(uri, projection, null, null, null);
//                int column_index = cursor.getColumnIndexOrThrow("_data");
//                if (cursor.moveToFirst()) {
//                    return cursor.getString(column_index);
//                }
//            } catch (Exception e) {
//                // Eat it  Or Log it.
//            }
//        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
//            return uri.getPath();
//        }
//        return null;
//    }

    @Override
    public void onUpload(double process) {

    }
}
