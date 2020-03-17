package com.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View {

    private int angle = 60;  //当前绘制圆的角度 （0~360）

    private int _w,_h;
    private String color;//颜色
    private int radius; //半径
    private float fontSize; //loading的字体大小

    int cicle_x,cicle_y = 0;//圆形点的位置
    String loading = "0%"; //当前的进度

    //画笔
    Paint paint;
    //画的范围
    RectF rectF;
    Context context;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initview(context,attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initview(context,attrs);
    }

    private void initview(Context context,AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.mycicle);
        if(typedArray != null) {
            color = typedArray.getString(R.styleable.mycicle_cicle_color);
            float size = typedArray.getFloat(R.styleable.mycicle_cicle_loading_size, 12f); //sp单位
            fontSize = Utils.sp2px(context, size);
            Log.i("fontSize:", String.valueOf(fontSize));
            //radius = typedArray.getInteger(R.styleable.mycicle_cicle_radius,10);
            paint = new Paint();
            paint.setColor(Color.parseColor(color));
            //是否自定义属性，进行回收处理
            typedArray.recycle();
        }
    }

    //设置当前绘制的圆的角度
    public void setAngle(int angle){
        this.angle = angle;
    }


    /**
     * 计算当前圆的角度，对应的进度 60/360 = x/100
     * @param canvas
     */

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        _w = getWidth(); //当前组件的宽
        _h = getHeight(); //当前组件的高

        cicle_x = _w/2;
        cicle_y = _w/2;
        radius = _w/2;
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#000000"));
        canvas.drawCircle(cicle_x,cicle_y,radius,paint);
        //设置扇形的范围
        rectF = new RectF();
        rectF.set(0,0,_w,_h);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor(color));
        //画扇形
        canvas.drawArc(rectF,-90,angle,true,paint);

        //当前的%分比进度计算
        loading = (int) (((float)angle)/360f*100)+"%";
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.parseColor("#03A9F4"));
        paint.setTextSize(fontSize);
        int _tx=0,_ty = 0;
        Rect rect = getTextWH(loading);
        //计算文本的中心点
        _tx = _w/2-rect.width()/2;
        _ty = _h/2+rect.height()/2;
        //绘制文本
        canvas.drawText(loading,_tx,_ty,paint);
    }

    //动态获取文本的宽高
    private Rect getTextWH(String string){
        Rect rect = new Rect();
        paint.getTextBounds(string,0,string.length(),rect);
        return rect;
    }


}
