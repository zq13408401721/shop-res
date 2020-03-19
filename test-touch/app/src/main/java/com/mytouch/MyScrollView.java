package com.mytouch;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;
import android.widget.TextView;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyScrollView extends ScrollView {

    public int header_height;

    public MyScrollView(Context context) {
        super(context);
        init();
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){

    }

    /**
     * 在该方法中进行判断
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                float scrolly = this.getScrollY();
                if(scrolly <= header_height){
                    return true;
                }
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

   /* @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float scrolly = this.getScrollY();
        if(scrolly <= header_height){
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }*/
}
