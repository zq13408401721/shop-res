package com.example.myviews.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class DrawCircle extends View {

    //转圈次数
    public int times;

    //旋转角度
    private int Angle;
    //记录当前是否去绘制圆
    private boolean isDrawCircle;

    //画笔
    private Paint paint;

    //画进度
    private Paint paint_progress;
    //圆形
    private RectF oval;

    //文本边框
    private Paint paint_bar;


    //进度
    public String txt_progress;

    private int c_x; //圆心点x坐标
    private int c_y; //圆心点y坐标
    private int c_r; //圆半径
    private int c_w; //组件宽

    public boolean isOver;

    public DrawCircle(Context context) {
        super(context);
        init();
    }

    public DrawCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init(){
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(8);
        oval = new RectF();

        paint_progress = new Paint();
        paint_progress.setColor(Color.parseColor("#000000"));
        paint_progress.setTextSize(40.0f);

        paint_bar = new Paint();
        paint_bar.setColor(Color.parseColor("#ff0000"));
        paint_bar.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /*int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (widthMode){
            case MeasureSpec.EXACTLY:
                break;
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }*/
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    /**
     * 传入当前要绘制弧形的角度
     * @param angle
     */
    public void setAngle(int angle){
        isDrawCircle = Angle == angle ? false : true;
        Angle = angle;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //设置画笔颜色为灰色
        paint.setColor(Color.GRAY);
        // FILL填充, STROKE描边,FILL_AND_STROKE填充和描边
        paint.setStyle(Paint.Style.STROKE);

        int width = getWidth(); //获取当前组件的宽度
        int height = getHeight();

        c_x = c_y = width/2;
        c_r = width/4;//设置背景圆的半径
        c_w = width;

        canvas.drawCircle(c_w/2,c_w/2,c_r,paint); //画背景圆
        paint.setColor(Color.RED);  //设置上面圆的颜色
        //圆弧的形状和大小的界限
        oval.set(c_w/2-c_r,c_w/2-c_r,c_w/2+c_r,c_w/2+c_r);
        //根据角度画圆弧 -90表示把圆的起使位置设为正上方
        canvas.drawArc(oval,-90,Angle,false,paint);

        if(!TextUtils.isEmpty(txt_progress)) {
            int[] wh = getTextWH(txt_progress,paint_progress);
            int tmpx = c_w/2-wh[0]/2;
            int tmpy = height/2-wh[1]/2;
            canvas.drawRect(c_w/2-wh[0]/2,height/2-wh[1]/2,c_w/2+wh[0]/2,height/2+wh[1]/2,paint_bar);
            canvas.drawText(txt_progress, tmpx,height/2+wh[1]/2, paint_progress);
        }

    }

    /**
     * 获取文本的宽高
     * @param text
     * @param paint
     * @return
     */
    private int[] getTextWH(String text,Paint paint){
        int[] wh = new int[2];
        Rect rect = new Rect();
        paint.getTextBounds(text,0,text.length(),rect);
        wh[0] = rect.width();
        wh[1] = rect.height();
        return wh;
    }
}
