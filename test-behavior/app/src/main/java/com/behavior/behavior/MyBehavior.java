package com.behavior.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MyBehavior extends CoordinatorLayout.Behavior {

    private float deltaY; //

    public MyBehavior(){

    }

    public MyBehavior(Context context, AttributeSet attrs){
        super(context,attrs);
    }


    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        return dependency instanceof RecyclerView;
    }

    //child顶部的标题layout dependency是recyclerview
    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        if(deltaY == 0){
            deltaY = dependency.getY()-child.getHeight(); // 初始化的时候计算recyclerview的移动位移
        }
        float dy = dependency.getY()-child.getHeight();  //每次刷新的时候recyclerview移动位移
        dy = dy < 0 ? 0 : dy;
        float y = -(dy/deltaY) * child.getHeight();  //计算对方需要移动的位移
        child.setTranslationY(y);
        return true;
    }
}
