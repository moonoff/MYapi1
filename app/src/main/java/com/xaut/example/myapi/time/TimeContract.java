package com.xaut.example.myapi.time;

import com.xaut.example.myapi.BasePresenter;
import com.xaut.example.myapi.BaseView;

/**
 * Created by pc on 2016/12/13.
 */

public interface TimeContract {

    interface View extends BaseView<Presenter>{
        void createAlarm(String message, int hour, int minutes);
    }

    interface Presenter extends BasePresenter{

    }
}
