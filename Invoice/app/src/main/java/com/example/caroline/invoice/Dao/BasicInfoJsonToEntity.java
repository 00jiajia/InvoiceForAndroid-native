package com.example.caroline.invoice.Dao;

/**
 * Created by asus1 on 2018/3/21.
 */

//这个类可以写成泛型
public class BasicInfoJsonToEntity<T> {

    private T key;
    public BasicInfoJsonToEntity(T key){
        this.key=key;
    }
    public T getKey(){
        return key;
    }


}
