package com.xaut.example.myapi.testclass;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pc on 2016/12/13.
 */

@Module
public class DaggerTestModle {
    @Provides
    public Cloth getCloth(){
        Cloth cloth=new Cloth();
        cloth.setColor("红色");
        return cloth;
    }
}
