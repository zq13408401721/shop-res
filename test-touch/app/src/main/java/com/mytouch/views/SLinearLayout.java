package com.mytouch.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.mytouch.MyRecyclerView;
import com.mytouch.MyScrollView;

public class SLinearLayout extends LinearLayout {
    public SLinearLayout(Context context) {
        super(context);
    }

    public SLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 在该方法中进行判断
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_UP:
                intercept = false;
                ((MyRecyclerView)this.getChildAt(2)).bool = true;
                return false;
            case MotionEvent.ACTION_MOVE:
                float scrolly = ((ScrollInterceptScrollView)this.getParent()).getScrollY();
                if(scrolly >= 400){
                    intercept = true;
                    ((ScrollInterceptScrollView)this.getParent()).scrollTo(0,400);
                    return true;
                }else{
                    ((MyRecyclerView)this.getChildAt(2)).bool = true;
                }
                return super.onInterceptTouchEvent(ev);
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }*/
}
