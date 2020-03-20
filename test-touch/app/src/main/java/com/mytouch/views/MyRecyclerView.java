package com.mytouch.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;


/**
 * 处理滑动冲突
 * recyclerview纵轴方向上滑动
 */
public class MyRecyclerView extends RecyclerView {

    private float lastX,lastY; //记录上一次事件分发的坐标

    public MyRecyclerView(@NonNull Context context) {
        super(context);
    }

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true); //告诉父容器不要拦截
                break;
            case MotionEvent.ACTION_MOVE:
                //判断当前是否需要拦截
                float disX = Math.abs(ev.getX()-lastX); //计算当前的x坐标和上一次x的坐标的绝对值
                float disY = Math.abs(ev.getY()-lastY); //计算当前的y坐标和上一次y的坐标的绝对值
                if(disY < disX){
                    getParent().requestDisallowInterceptTouchEvent(false);  //当前是x方向滑动让父容器拦截
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        lastX = ev.getX();
        lastY = ev.getY();
        return super.dispatchTouchEvent(ev);
    }
}
