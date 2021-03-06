package com.example.caroline.invoice.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caroline.invoice.R;
import com.example.caroline.invoice.util.UploadUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class UploadTest extends AppCompatActivity implements View.OnClickListener,UploadUtil.OnUploadProcessListener {

    private static final String TAG = "uploadImage";

    /**
     * 去上传文件
     */
    protected static final int TO_UPLOAD_FILE = 1;
    /**
     * 上传文件响应
     */
    protected static final int UPLOAD_FILE_DONE = 2;  //
    /**
     * 选择文件
     */
    public static final int TO_SELECT_PHOTO = 3;
    /**
     * 上传初始化
     */
    private static final int UPLOAD_INIT_PROCESS = 4;
    /**
     * 上传中
     */
    private static final int UPLOAD_IN_PROCESS = 5;
    /***
     * 这里的这个URL是我服务器的javaEE环境URL
     */
    private Button selectButton,uploadButton;
    private ImageView imageView;
    private TextView uploadImageResult;

    private String picPath = null;
    private ProgressDialog progressDialog;


    private static String requestURL = "http://192.168.1.215:8080/fileUpload/file_upload";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_test);
        initView();

    }

    private void initView(){
        selectButton=(Button) findViewById(R.id.select_button);
        uploadButton=(Button) findViewById(R.id.upload_button);
        imageView=(ImageView) findViewById(R.id.img_view);
        uploadImageResult=(TextView) findViewById(R.id.upload_result);
        progressDialog=new ProgressDialog(this);

        //选择
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(UploadTest.this,SelectPicActivity.class);
                startActivityForResult(intent, TO_SELECT_PHOTO);
            }
        });
        //上传
        uploadImageResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(picPath!=null)
                {
                    handler.sendEmptyMessage(TO_UPLOAD_FILE);
                }else{
                    Toast.makeText(UploadTest.this, "上传的文件路径出错", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    /**
     * 上传服务器响应回调
     */
    @Override
    public void onUploadDone(int responseCode, String message) {
        progressDialog.dismiss();
        Message msg = Message.obtain();
        msg.what = UPLOAD_FILE_DONE;
        msg.arg1 = responseCode;
        msg.obj = message;
        handler.sendMessage(msg);
    }

    private void toUploadFile()
    {
        uploadImageResult.setText("正在上传中...");
        progressDialog.setMessage("正在上传文件...");
        progressDialog.show();
        String fileKey = "img";
        UploadUtil uploadUtil = UploadUtil.getInstance();
        uploadUtil.setOnUploadProcessListener(this);  //设置监听器监听上传状态

        Map<String, String> params = new HashMap<String, String>();
        params.put("orderId", "11111");
        uploadUtil.uploadFile( picPath,fileKey, requestURL,params);
    }

    @Override
    public void onUploadProcess(int uploadSize) {
        Message msg = Message.obtain();
        msg.what = UPLOAD_IN_PROCESS;
        msg.arg1 = uploadSize;
        handler.sendMessage(msg);
    }

    @Override
    public void initUpload(int fileSize) {
        Message msg = Message.obtain();
        msg.what = UPLOAD_INIT_PROCESS;
        msg.arg1 = fileSize;
        handler.sendMessage(msg );
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case TO_UPLOAD_FILE:
                    toUploadFile();
                    break;
                case UPLOAD_INIT_PROCESS:
                    break;
                case UPLOAD_IN_PROCESS:
                    break;
                case UPLOAD_FILE_DONE:
                    String result = "响应码："+msg.arg1+"\n响应信息："+msg.obj+"\n耗时："+ UploadUtil.getRequestTime()+"秒";
                    uploadImageResult.setText(result);
                    break;

                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_button:
                Intent intent = new Intent(this,SelectPicActivity.class);
                startActivityForResult(intent, TO_SELECT_PHOTO);
                break;
            case R.id.upload_button:
                if(picPath!=null)
                {
                    handler.sendEmptyMessage(TO_UPLOAD_FILE);
                }else{
                    Toast.makeText(this, "上传的文件路径出错", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode== Activity.RESULT_OK && requestCode == TO_SELECT_PHOTO)
        {
            picPath = data.getStringExtra(SelectPicActivity.KEY_PHOTO_PATH);
            Log.i(TAG, "最终选择的图片="+picPath);
            Bitmap bm = BitmapFactory.decodeFile(picPath);
            imageView.setImageBitmap(bm);



            //更新图库

            Uri localUri = Uri.fromFile(new File(picPath));
            Intent localIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, localUri);
            sendBroadcast(localIntent);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
