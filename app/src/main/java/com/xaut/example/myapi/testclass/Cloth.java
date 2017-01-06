package com.xaut.example.myapi.testclass;

/**
 * Created by pc on 2016/12/13.
 */

public class Cloth {
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color+"布料";
    }
}
