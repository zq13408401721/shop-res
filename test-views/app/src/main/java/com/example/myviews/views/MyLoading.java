package com.example.myviews.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MyLoading extends View {

    //画笔
    private Paint paint;

    public MyLoading(Context context) {
        super(context);
        init();
    }

    public MyLoading(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyLoading(Context context, @Nullable AttributeSet attrs, int defStyleAttr)   {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init(){
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(8);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float x = (getWidth()-getHeight()/2)/2;
        float y = getHeight()/4;
        RectF oval = new RectF(x,y,getWidth()-x,getHeight()-y);
        canvas.drawArc(oval,360,142,false,paint);
        //canvas.drawRect(oval,paint);
    }
}
