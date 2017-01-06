package com.xaut.example.myapi.main;

import com.xaut.example.myapi.main.MainActivityScope;
import com.xaut.example.myapi.main.MainFragment;

import dagger.Subcomponent;

/**
 * Created by pc on 2016/12/14.
 */
@MainActivityScope
@Subcomponent
public interface MainFragmentComponent {

    void inject(MainFragment mainFragment);
}
