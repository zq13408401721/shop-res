package com.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Size;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class MyText extends View {

    private String text;
    private String color;
    private int textSize;

    Paint paint;

    public MyText(Context context) {
        super(context);
    }

    public MyText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttr(context,attrs);
    }

    public MyText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 初始化属性
     * @param context
     */
    private void initAttr(Context context,AttributeSet attrs){
        //自定义属性和xml布局中的属性映射
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.myview);
        text = typedArray.getString(R.styleable.myview_text);
        color = typedArray.getString(R.styleable.myview_textColor);
        textSize = typedArray.getInteger(R.styleable.myview_textSize,10);
        //创建画笔
        paint = new Paint();
        //设置笔触的颜色
        paint.setColor(Color.parseColor(color));
        //设置笔触的大小
        paint.setTextSize(textSize);
    }


    //绘制 canvas 画布
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(text,100,100,paint);
    }
}
