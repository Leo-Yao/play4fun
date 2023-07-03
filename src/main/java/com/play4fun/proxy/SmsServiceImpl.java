package com.play4fun.proxy;

public class SmsServiceImpl implements SmsService{
    @Override
    public String sendMsg(String msg) {
        System.out.println("send msg: " + msg);
        return msg;
    }
}
