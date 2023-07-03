package com.play4fun.reflection;

public class Student {

    private String name;
    private String telePhone;

    public Student() {
        name = "Amanda";
        telePhone = "15723458936";
    }

    public String getName(String name) {
        return "name is " + name;
    }

    private String getTelePhone() {
        return "telePhone is " + telePhone;
    }
}
