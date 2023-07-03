package com.play4fun.proxy.cglib;

import com.play4fun.proxy.AliSmsService;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CglibProxyFactory {

    public static Object getProxy(Class<?> clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setClassLoader(clazz.getClassLoader());
        enhancer.setSuperclass(clazz);
        enhancer.setCallback((Callback)new SmsInterceptor());
        return enhancer.create();
    }

//    public static void main(String[] args) {
//        AliSmsService aliSmsService = (AliSmsService) CglibProxyFactory.getProxy(AliSmsService.class);
//        aliSmsService.send("fantasy");
//    }

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); // 指定要转换的日期格式
        String currentTime = sdf.format(new Date()); // 获取当前时间并转换为指定格式的字符串
        System.out.println(currentTime);
    }
}
