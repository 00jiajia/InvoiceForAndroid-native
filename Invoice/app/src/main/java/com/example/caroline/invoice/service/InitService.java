package com.example.caroline.invoice.service;

import android.text.TextUtils;

import com.example.caroline.invoice.model.BasicInfo;
import com.example.caroline.invoice.model.BillingSettingInfo;
import com.example.caroline.invoice.model.BillingTypeInfo;
import com.example.caroline.invoice.model.IndustryInfo;
import com.example.caroline.invoice.model.KpexoptionInfo;
import com.example.caroline.invoice.model.QuestionInfo;
import com.example.caroline.invoice.model.QuotaInfo;
import com.example.caroline.invoice.model.RoleInfo;
import com.example.caroline.invoice.util.HttpCallbackListener;
import com.example.caroline.invoice.util.HttpUtil;
import com.example.caroline.invoice.util.Utility;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus1 on 2018/3/21.
 */


public class InitService {

    private List<BillingTypeInfo> billingTypeInfos;
    private List<IndustryInfo> industryInfos;
    private List<QuestionInfo> questionInfos;
    private List<RoleInfo> roleInfos;
    private List<QuotaInfo> quotaInfos;
    private List<BillingSettingInfo> billingSettingInfos;
    private boolean isTrue;

    public InitService(){
        billingTypeInfos=new ArrayList<BillingTypeInfo>();
        industryInfos=new ArrayList<IndustryInfo>();
        questionInfos=new ArrayList<QuestionInfo>();
        roleInfos=new ArrayList<RoleInfo>();
        quotaInfos=new ArrayList<QuotaInfo>();
        billingSettingInfos=new ArrayList<BillingSettingInfo>();
    }

    //行业种类
    public List<IndustryInfo> queryForIndustryTypeInfos(final String address){
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) throws JSONException {
                if(!TextUtils.isEmpty(response)){
                    JSONArray jsonArray=new JSONArray(response);
                        for (int i=0;i<jsonArray.length();i++){

                            IndustryInfo info=new IndustryInfo();
                            JSONObject objet=jsonArray.getJSONObject(i);
                            info.setHyflmc(Utility.getRtString("HYFLMC",objet));
                            info.setHyflid(Integer.parseInt(Utility.getRtString("HYFLID",objet)));
                            info.setHyfldm(Utility.getRtString("HYFLDM",objet));
                            info.setGsds(Integer.parseInt(Utility.getRtString("GSDS",objet)));

                            industryInfos.add(info);
                        }
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });

        return industryInfos;
    }

    //开票种类
    public List<BillingTypeInfo> queryForBillingTypeInfos(final String address){
        List<IndustryInfo> list=new ArrayList<IndustryInfo>();
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) throws JSONException {
                if(!TextUtils.isEmpty(response)){
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        BillingTypeInfo info=new BillingTypeInfo();
                        JSONObject object=jsonArray.getJSONObject(i);
                        info.setHyfldm(Utility.getRtString("FPZLDM",object));
                        info.setKpzlid(Integer.parseInt(Utility.getRtString("KPZLID",object)));
                        info.setGsds(Integer.parseInt(Utility.getRtString("GSDS",object)));
                        info.setKpzlid(Integer.parseInt(Utility.getRtString("KPZLID",object)));
                        info.setKpzlmc(Utility.getRtString("KPZLMC",object));
                        info.setKpzldm(Utility.getRtString("KPZLDM",object));
                        billingTypeInfos.add(info);
                    }
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });

        return billingTypeInfos;
    }

    public boolean SaveInitInfo(final String address,BasicInfo info){

        String str_info=new Gson().toJson(info);

        HttpUtil.sendHttpRequestForPost(address, str_info, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) throws JSONException {
                if(response.contains("success")){
                    isTrue=true;
                }
            }

            @Override
            public void onError(Exception e) {
                isTrue=false;
            }
        });
        return isTrue;
    }

    //管理员密保
    public List<QuestionInfo> queryForQuestions(final String address){

        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) throws JSONException {
                if(!TextUtils.isEmpty(response)){
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++){
                        QuestionInfo info=new QuestionInfo();
                        JSONObject objet=jsonArray.getJSONObject(i);
                        info.setMMWTID(Integer.parseInt(Utility.getRtString("MMWTID",objet)));
                        info.setMMWT(Utility.getRtString("MMWT",objet));
                        questionInfos.add(info);
                    }
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
        return  questionInfos;
    }

    //用户角色
    public List<RoleInfo> queryForJs(final String address){

        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) throws JSONException {
                if(!TextUtils.isEmpty(response)){
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++){
                        RoleInfo info=new RoleInfo();
                        JSONObject objet=jsonArray.getJSONObject(i);
                        info.setDYXZ(Integer.parseInt(Utility.getRtString("DYXZ",objet)));
                        info.setJSID(Integer.parseInt(Utility.getRtString("JSID",objet)));
                        info.setJSMC(Utility.getRtString("JSMC",objet));
                        roleInfos.add(info);
                    }
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
        return  roleInfos;
    }

    public List<QuotaInfo> queryForQuota(final String address){

        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) throws JSONException {
                if(!TextUtils.isEmpty(response)){
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++){
                        QuotaInfo info=new QuotaInfo();
                        JSONObject objet=jsonArray.getJSONObject(i);
                        info.setKPXE(Integer.parseInt(Utility.getRtString("KPXE",objet)));
                        info.setKPZLID(Integer.parseInt(Utility.getRtString("KPZLID",objet)));
                        quotaInfos.add(info);
                    }
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
        return  quotaInfos;
    }
    public List<BillingSettingInfo> queryForBillingSetting(final String address){

        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) throws JSONException {
                if(!TextUtils.isEmpty(response)){
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++){
                        BillingSettingInfo info= new BillingSettingInfo();
                        JSONObject objet=jsonArray.getJSONObject(i);
                        info.setFpzldm(Utility.getRtString("FPZLDM",objet));
                        info.setGsds(Integer.parseInt(Utility.getRtString("GSDS",objet)));
                        info.setHyfldm(Utility.getRtString("HYFLDM",objet));
                        info.setKpzldm(Utility.getRtString("KPZLDM",objet));
                        info.setKpzlid(Integer.parseInt(Utility.getRtString("KPZLID",objet)));
                        info.setKpzlmc(Utility.getRtString("KPZLMC",objet));
                        JSONArray a=objet.getJSONArray("kpxeOptions");
                        if(a!=null){
                            List<KpexoptionInfo> temp_list=new ArrayList<KpexoptionInfo>();
                            for(int j=0;j<a.length();j++){
                                JSONObject _temp=a.getJSONObject(j);
                                KpexoptionInfo _info=new KpexoptionInfo();
                                _info.setKPXE(Integer.parseInt(Utility.getRtString("KPXE",_temp)));
                                _info.setKPZLID(Integer.parseInt(Utility.getRtString("KPZLID",_temp)));
                                temp_list.add(_info);
                            }
                            info.setKps(temp_list);
                        }
                        billingSettingInfos.add(info);
                    }
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
        return  billingSettingInfos;
    }
}
