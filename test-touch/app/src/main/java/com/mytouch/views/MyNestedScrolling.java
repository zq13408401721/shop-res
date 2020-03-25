package com.mytouch.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.view.NestedScrollingParent2;
import android.util.AttributeSet;
import android.view.View;

import com.mytouch.R;

public class MyNestedScrolling extends NestedScrollView implements NestedScrollingParent2 {


    private int recommendY;

    public MyNestedScrolling(@NonNull Context context) {
        super(context);
    }

    public MyNestedScrolling(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNestedScrolling(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        recommendY = child.findViewById(R.id.txt_header).getTop();
        return true;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {
        //super.onNestedScrollAccepted(child, target, axes);
    }

    @Override
    public void onStopNestedScroll(@NonNull View target, int type) {
        super.onStopNestedScroll(target);
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        if(dy > 0 && getScrollY() < recommendY){
            scrollBy(0,dy);
            consumed[1] = dy;
        }
    }
}
