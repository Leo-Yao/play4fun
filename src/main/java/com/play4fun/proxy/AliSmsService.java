package com.play4fun.proxy;

public class AliSmsService {

    public String send(String msg) {
        System.out.println("msg: " + msg);
        return msg;
    }
}
