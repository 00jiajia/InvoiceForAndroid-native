package com.example.caroline.invoice.util;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by asus1 on 2018/3/20.
 */

public class Utility {
    public static String getRtString(String name,JSONObject obj){
        String rtnString="";
        try {
            rtnString= TextUtils.isEmpty(obj.getString(name))?"":obj.getString(name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rtnString;
    }
}
