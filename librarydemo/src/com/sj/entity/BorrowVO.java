package com.sj.entity;

import java.util.List;

public class BorrowVO {
    private int code;
    private String msg;
    private  int count;
    private List<Borrow> data;

    public BorrowVO() {
    }

    public BorrowVO(int code, String msg, int count, List<Borrow> data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Borrow> getData() {
        return data;
    }

    public void setData(List<Borrow> data) {
        this.data = data;
    }
}
