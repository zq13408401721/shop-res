package com.mytouch;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;
import android.widget.TextView;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyScrollView extends ScrollView {

    public int header_height = 400;

    public MyRecyclerView recyclerView;

    private float _y = 0;


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
        int child = getChildCount();
        Log.i("Child", String.valueOf(child));
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        float nowY = ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if(recyclerView != null && recyclerView.isTop()){
                    if(Math.abs(nowY) < _y){ //上拉
                        if(getScrollY() >= header_height){
                            scrollTo(0,header_height);
                            //recyclerView.dispatchTouchEvent(ev);
                            intercept = true;
                        }else{
                            intercept = super.onInterceptTouchEvent(ev);
                        }
                    }else{
                        intercept = false;
                    }
                }
                break;
        }
        _y = ev.getY();
        return intercept;
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
