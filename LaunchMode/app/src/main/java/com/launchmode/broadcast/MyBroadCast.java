package com.launchmode.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.launchmode.utils.MyUtils;

/**
 * 定义一个广播的类
 */
public class MyBroadCast extends BroadcastReceiver {

    private final String TAG = MyBroadCast.class.getSimpleName();

    //接收广播通知的处理方法
    @Override
    public void onReceive(Context context, Intent intent) {
        String str = intent.getStringExtra("key");
        MyUtils.print(TAG,str);
    }
}
