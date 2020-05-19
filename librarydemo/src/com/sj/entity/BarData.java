package com.sj.entity;

import java.util.List;

public class BarData {
    private List<String> name;
    private List<Integer> count;

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<Integer> getCount() {
        return count;
    }

    public void setCount(List<Integer> count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "BarData{" +
                "name=" + name +
                ", count=" + count +
                '}';
    }
}
