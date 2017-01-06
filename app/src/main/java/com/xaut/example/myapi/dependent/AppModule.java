package com.xaut.example.myapi.dependent;

import android.content.Context;

import com.xaut.example.myapi.utils.ToastUtil;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pc on 2016/12/14.
 */
@Module
public class AppModule {
    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return this.context;
    }

    @Singleton
    @Provides
    public ToastUtil provideToastUtil(Context context) {
        return new ToastUtil(context);
    }

}
