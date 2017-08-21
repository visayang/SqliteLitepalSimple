package com.inventec.sqliteexample;

import org.litepal.crud.DataSupport;

/**
 * Created by Chad.Yang on 2017/8/21.
 */

public class Student extends DataSupport{

    private String name;
    private String address;

    public Student() {
    }

    public Student(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
