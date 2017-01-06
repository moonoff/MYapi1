package com.xaut.example.myapi.dependent;

import android.content.Context;

import com.xaut.example.myapi.utils.ToastUtil;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by pc on 2016/12/14.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    Context getContext();
    ToastUtil getToastUtil();

}
