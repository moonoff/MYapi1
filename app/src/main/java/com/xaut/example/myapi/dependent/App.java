package com.xaut.example.myapi.dependent;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by pc on 2016/12/14.
 */

public class App extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        appComponent=DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return this.appComponent;
    }
}
