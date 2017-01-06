package com.xaut.example.myapi.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by pc on 2016/12/14.
 */

public class ToastUtil {
    private Context context;

    public ToastUtil(Context context) {
        this.context = context;
    }

    public void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
