package com.xaut.example.myapi.testclass;

import dagger.Component;

/**
 * Created by pc on 2016/12/13.
 */

@Component(modules = DaggerTestModle.class)
public interface DaggerTestComponent {

    void inject(DaggerTestActivity daggerTestActivity);

}
