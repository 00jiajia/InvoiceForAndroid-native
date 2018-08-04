package com.example.caroline.invoice.util;

import org.json.JSONException;

/**
 * Created by asus1 on 2018/3/20.
 */

public interface HttpCallbackListener {
    void onFinish(String response) throws JSONException;
    void onError(Exception e);
}
