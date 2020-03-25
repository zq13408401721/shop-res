package com.mytouch.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.mytouch.utils.MyUtils;

public class MyViewGroup extends ViewGroup {

    private static String TAG;

    public MyViewGroup(Context context) {
        super(context);
        initClassTag();
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initClassTag();
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initClassTag();
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initClassTag();
    }

    private void initClassTag(){
        TAG = this.getClass().getName();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(),widthMeasureSpec),getDefaultSize(getSuggestedMinimumHeight(),heightMeasureSpec));
    }

    /**
     * 子View中宽度最大值
     * @return
     */
    private int getMaxChildWidth(){
        int childCount = getChildCount();
        int maxWidth = 0;
        for(int i=0; i<childCount; i++){
            View childView = getChildAt(i);
            if(childView.getMeasuredWidth() > maxWidth){
                maxWidth = childView.getMeasuredWidth();
            }
        }
        return maxWidth;
    }

    /**
     * 所有子View的高度相加
     * @return
     */
    private int getTotleHeight(){
        int childCount = getChildCount();
        int height = 0;
        for(int i=0; i<childCount; i++){
            View childView = getChildAt(i);
            height += childView.getMeasuredHeight();
        }
        return height;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        final int count = getChildCount();
        int childMeasureWidth = 0;
        int childMeasureHeight = 0;
        int layoutWidth = 0;    // 容器已经占据的宽度
        int layoutHeight = 0;   // 容器已经占据的宽度
        int maxChildHeight = 0; //一行中子控件最高的高度，用于决定下一行高度应该在目前基础上累加多少
        for(int i = 0; i<count; i++){
            View child = getChildAt(i);
            //注意此处不能使用getWidth和getHeight，这两个方法必须在onLayout执行完，才能正确获取宽高
            childMeasureWidth = child.getMeasuredWidth();
            childMeasureHeight = child.getMeasuredHeight();
            if(layoutWidth<getWidth()){
                //如果一行没有排满，继续往右排列
                left = layoutWidth;
                right = left+childMeasureWidth;
                top = layoutHeight;
                bottom = top+childMeasureHeight;
            } else{
                //排满后换行
                layoutWidth = 0;
                layoutHeight += maxChildHeight;
                maxChildHeight = 0;

                left = layoutWidth;
                right = left+childMeasureWidth;
                top = layoutHeight;
                bottom = top+childMeasureHeight;
            }

            layoutWidth += childMeasureWidth;  //宽度累加
            if(childMeasureHeight>maxChildHeight){
                maxChildHeight = childMeasureHeight;
            }

            //确定子控件的位置，四个参数分别代表（左上右下）点的坐标值
            child.layout(left, top, right, bottom);
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        MyUtils.show_events(TAG,ev,"dispatchTouchEvent");
        //return true;
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        MyUtils.show_events(TAG,ev,"onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
        //return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        MyUtils.show_events(TAG,event,"onTouchEvent");
        return false;
    }
}
