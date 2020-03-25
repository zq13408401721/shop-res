package com.mytouch.views;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.mytouch.utils.MyUtils;

public class MyView extends View {

    private static String TAG;

    public MyView(Context context) {
        super(context);
        initTag();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTag();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTag();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initTag();
    }

    private void initTag(){
        TAG = this.getClass().getName();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        MyUtils.show_events(TAG,event,"dispatchTouchEvent");
        //return super.dispatchTouchEvent(event);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        MyUtils.show_events(TAG,event,"onTouchEvent");
        //boolean bool = super.onTouchEvent(event);
        return true;
    }
}
