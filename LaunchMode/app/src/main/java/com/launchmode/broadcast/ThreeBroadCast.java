package com.launchmode.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;

import com.launchmode.utils.MyUtils;

public class ThreeBroadCast extends BroadcastReceiver {
    private final String TAG = ThreeBroadCast.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        int pid = Process.myPid();
        String str = intent.getStringExtra("appkey");
        MyUtils.print(TAG,str +" pid:"+pid);
        Bundle bundle = getResultExtras(false);
        if(bundle != null){
            MyUtils.print(TAG,bundle.getString("from"));
            MyUtils.print(TAG,bundle.getString("name"));
        }
    }
}
