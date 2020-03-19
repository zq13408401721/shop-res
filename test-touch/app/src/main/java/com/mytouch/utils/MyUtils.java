package com.mytouch.utils;

import android.util.Log;
import android.view.MotionEvent;

public class MyUtils {

    public static void showLog(String tag,String msg){
        Log.i(tag,msg);
    }

    public static void show_events(String tag, MotionEvent evt,String fname){
        Log.i(tag,evt.getAction()+"----"+fname);
    }

}
