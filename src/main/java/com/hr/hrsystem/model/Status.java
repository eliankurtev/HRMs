package com.hr.hrsystem.model;

public enum Status {
    DECLINED("1"),
    PENDING("2"),
    HIRED("3");

    private String s;
    Status(String s) {
        this.s = s;
    }
}
