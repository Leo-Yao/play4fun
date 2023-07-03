package com.play4fun.proxy.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;

public class SmsInterceptor implements MethodInterceptor {


    @Override public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
        throws Throwable {
        System.out.println("before method" + method.getName());
        Object result = method.invoke(o, objects);
        System.out.println("after method" + method.getName());
        return result;
    }
}
