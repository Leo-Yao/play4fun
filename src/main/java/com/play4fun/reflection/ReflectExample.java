package com.play4fun.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectExample {
    public static void main(String[] args)
        throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException,
        InstantiationException, NoSuchFieldException {
        Class<?> targetClass = Class.forName("com.play4fun.reflection.Student");
        Student student = (Student)targetClass.newInstance();

        Method[] declaredMethods = targetClass.getDeclaredMethods();
        Arrays.stream(declaredMethods).forEach(method -> System.out.println(method.getName()));
        System.out.println("----------");
        Method getName = targetClass.getDeclaredMethod("getName", String.class);
        System.out.println(getName.invoke(student, "Nancy"));
        Field[] declaredFields = targetClass.getDeclaredFields();
        Arrays.stream(declaredFields).forEach(field -> System.out.println(field.getName()));
        System.out.println("----------");
        Field telePhone = targetClass.getDeclaredField("telePhone");
        telePhone.setAccessible(true);
        telePhone.set(student, "13853667824");

        Method getTelePhone = targetClass.getDeclaredMethod("getTelePhone");
        getTelePhone.setAccessible(true);
        System.out.println(getTelePhone.invoke(student));

    }
}
