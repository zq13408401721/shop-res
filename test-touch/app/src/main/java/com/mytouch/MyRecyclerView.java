package com.mytouch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyRecyclerView extends RecyclerView {

    public boolean bool = true;

    public MyRecyclerView(@NonNull Context context) {
        super(context);
    }

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /*@Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.onInterceptTouchEvent(e);
    }*/

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                LinearLayoutManager layoutManager= (LinearLayoutManager) getLayoutManager();
                if(layoutManager.findFirstCompletelyVisibleItemPosition() == 0){
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

   /* @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                requestDisallowInterceptTouchEvent(false);
                break;
            case MotionEvent.ACTION_CANCEL:
                requestDisallowInterceptTouchEvent(true);
                break;
        }
        return super.onTouchEvent(e);
    }
*/
    /**
     * 是否滑动到顶部
     * @return
     */
    public boolean isTop(){
        LinearLayoutManager layoutManager = (LinearLayoutManager) this.getLayoutManager();
        return layoutManager.findFirstCompletelyVisibleItemPosition() == 0 ? true : false;
    }
}
