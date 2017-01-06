package com.xaut.example.myapi.dependent;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pc on 2016/12/14.
 */
@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @PerActivity
    @Provides
    public Activity provideActivity() {
        return this.activity;
    }
}
