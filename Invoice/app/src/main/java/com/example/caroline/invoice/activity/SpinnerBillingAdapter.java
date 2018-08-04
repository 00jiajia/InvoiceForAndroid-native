package com.example.caroline.invoice.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.caroline.invoice.R;
import com.example.caroline.invoice.model.BillingSettingInfo;
import com.example.caroline.invoice.model.KpexoptionInfo;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by asus1 on 2018/4/9.
 */

public class SpinnerBillingAdapter extends BaseAdapter{

    private Context context;
    private List<BillingSettingInfo> billingSettingInfos;
    private Integer resource;
    private LayoutInflater inflater;

    public SpinnerBillingAdapter(Context context,List<BillingSettingInfo> billingSettingInfos,int resource){
        this.context=context;
        this.resource=resource;
        this.billingSettingInfos=billingSettingInfos;
        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return billingSettingInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return billingSettingInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView=inflater.inflate(resource,null);//确实不懂
        }

        final BillingSettingInfo info=billingSettingInfos.get(position);
        List<String> kps=new ArrayList<String>();
        if(info.getKps()!=null&&info.getKps().size()>0){
            for(KpexoptionInfo _info:info.getKps()){
                kps.add(_info.getKPXE()+"");
            }
        }
        TextView textView_type=(TextView) convertView.findViewById(R.id.billing_item_billing_type);
        final Spinner spinner_quota=(Spinner) convertView.findViewById(R.id.billing_item_quota);
        spinner_quota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int _position, long id) {
                info.setKpoption(info.getKps().get(_position).getKPXE());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        textView_type.setText(info.getKpzlmc());
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_single_choice,kps);
        spinner_quota.setAdapter(adapter);
        return convertView;
    }

}
