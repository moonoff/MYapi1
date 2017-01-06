package com.xaut.example.myapi.time;

import android.content.Intent;
import android.provider.AlarmClock;
import android.support.v4.app.Fragment;

/**
 * Created by pc on 2016/12/13.
 */

public class TimeFragment extends Fragment implements TimeContract.View {


    @Override
    public void setPresenter(TimeContract.Presenter presenter) {

    }

    @Override
    public void createAlarm(String message, int hour, int minutes) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
//                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
//                .putExtra(AlarmClock.EXTRA_HOUR, hour)
//                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
//        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
//      }
    }
}
