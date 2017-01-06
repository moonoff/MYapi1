package com.xaut.example.myapi.dependent;

import android.app.Activity;

import com.xaut.example.myapi.utils.ToastUtil;

import dagger.Component;

/**
 * Created by pc on 2016/12/14.
 */
@PerActivity
@Component(modules = {ActivityModule.class},dependencies = {AppComponent.class})
public interface ActivityComponent {
    Activity getActivity();
    ToastUtil getToastUtil();
}
