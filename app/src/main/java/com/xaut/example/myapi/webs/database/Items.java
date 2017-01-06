package com.xaut.example.myapi.webs.database;

import io.realm.RealmObject;

/**
 * Created by pc on 2016/12/28.
 */

public class Items extends RealmObject {
    private String img;
    private String name;
    private String price;
    private String youhui;
    private String but1;
    private String but2;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getBut2() {
        return but2;
    }

    public void setBut2(String but2) {
        this.but2 = but2;
    }

    public String getBut1() {
        return but1;
    }

    public void setBut1(String but1) {
        this.but1 = but1;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYouhui() {
        return youhui;
    }

    public void setYouhui(String youhui) {
        this.youhui = youhui;
    }
}
