package com.launchmode.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.launchmode.utils.MyUtils;

public class OneBroadCast extends BroadcastReceiver {

    private final String TAG = OneBroadCast.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String str = intent.getStringExtra("appkey");
        MyUtils.print(TAG,str);
        Bundle bundle = new Bundle();
        bundle.putString("from",TAG);
        setResultExtras(bundle);
    }
}
