package com.play4fun.proxy.jdk;

import com.play4fun.proxy.SmsService;
import com.play4fun.proxy.SmsServiceImpl;

import java.lang.reflect.Proxy;

public class JdkProxyFactory {

    public static Object getProxy(Object target) {
        return Proxy.newProxyInstance(
            target.getClass().getClassLoader(),
            target.getClass().getInterfaces(),
            new SmsInvocationHandler(target)
        );
    }

    public static void main(String[] args) {
        SmsService smsService = (SmsService)JdkProxyFactory.getProxy(new SmsServiceImpl());
        smsService.sendMsg("Leo");
    }
}
