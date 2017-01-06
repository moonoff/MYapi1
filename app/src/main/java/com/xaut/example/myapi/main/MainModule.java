package com.xaut.example.myapi.main;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pc on 2016/12/14.
 */
@Module
public class MainModule {
    @Provides
    public UserRepository provideUserRepository() {
        return new UserRepository();
    }
}
