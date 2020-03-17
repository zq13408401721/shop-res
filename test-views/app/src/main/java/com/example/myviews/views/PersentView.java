package com.example.myviews.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PersentView extends View {

    private static String TAG = "PersentView";

    int[] colors = {Color.RED,Color.BLUE,Color.YELLOW,Color.GREEN};

    //转圈次数
    public int times;

    //旋转角度
    public int Angle;

    //画笔
    private Paint paint;

    //画进度
    private Paint paint_progress;
    //圆形
    private RectF oval;

    //进度
    public String txt_progress;

    private int c_x; //圆心点x坐标
    private int c_y; //圆心点y坐标
    private int c_r; //圆半径
    private int c_w; //组件宽

    public PersentView(Context context) {
        super(context);
        init();
    }

    public PersentView(Context context,AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PersentView(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /*public PersentView(Context context,AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }*/

    private void init(){
        paint = new Paint();
        paint.setAntiAlias(true);
        oval = new RectF();

        paint_progress = new Paint();
        paint_progress.setColor(Color.parseColor("#ffffff"));
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
        Log.e(TAG,"onlayout");
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
        paint.setColor(colors[times%4]);  //设置上面圆的颜色
        //圆弧的形状和大小的界限
        oval.set(c_w/2-c_r,c_w/2-c_r,c_w/2+c_r,c_w/2+c_r);
        //根据角度画圆弧 -90表示把圆的起使位置设为正上方
        canvas.drawArc(oval,-90,Angle,false,paint);
        if(!TextUtils.isEmpty(txt_progress)) {
            canvas.drawText(txt_progress, c_w / 2, c_w / 2, paint_progress);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean bool = isClick(event.getX(),event.getY());
        if(bool){
            Toast.makeText(this.getContext(),"点击了",Toast.LENGTH_SHORT).show();
        }
        return super.onTouchEvent(event);
    }

    private boolean isClick(float x,float y){
        double value = Math.pow(Math.abs(c_x-x),2)+Math.pow(Math.abs(c_y-y),2);
        int dis = (int)Math.sqrt(value);
        return dis > c_r ? false : true;
    }
}
