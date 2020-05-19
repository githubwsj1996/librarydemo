package com.sj.entity;

public class PieData {
    private Integer value;
    private String name;

    public PieData(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PieData{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }
}
