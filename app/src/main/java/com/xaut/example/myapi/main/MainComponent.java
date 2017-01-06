package com.xaut.example.myapi.main;

import com.xaut.example.myapi.MainActivity;
import com.xaut.example.myapi.dependent.ActivityModule;
import com.xaut.example.myapi.dependent.AppComponent;

import dagger.Component;

/**
 * Created by pc on 2016/12/14.
 */
@MainActivityScope
@Component(dependencies = {AppComponent.class},modules = {MainModule.class,ActivityModule.class})
public interface MainComponent {

    void inject(MainActivity mainActivity);

    MainFragmentComponent mainFragmentComponent();
}
