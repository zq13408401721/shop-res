package com.example.myviews;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.myviews.views.DrawCircle;
import com.example.myviews.views.PersentView;

public class View2Activity extends AppCompatActivity {

    private int angle = 0; //角度
    private int times = 0; //圈数

    private PersentView persentView;
    private DrawCircle drawCircle;

    private int TotalTime = 60*60; //总时间
    private int startTime = 0; //开始执行的系统时间
    private float scaleAngle = 0; //运行时间和角度的比值关系
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view2);
        initView();
    }

    private void initView(){
        persentView = findViewById(R.id.persentView);
        drawCircle = findViewById(R.id.drawcircle);
        //启动定时器
        handler.postDelayed(runnable,30);
        startTime = (int) (System.currentTimeMillis()/1000);
        scaleAngle = TotalTime/360; //每一个角度对应多长的时间

    }

    //定时器的runnable
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //定时器一秒执行一次
            handler.postDelayed(this,1000);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    int now = (int) (System.currentTimeMillis()/1000);
                    int curTime = now-startTime;
                    int curAngle = Math.round(curTime/scaleAngle); //当前的角度
                    angle = curAngle;
                    //旋转角度到达360时记录一圈
                    /*if(angle > 360){
                        times++;
                    }*/
                    String timeStr = formatMillisToString(TotalTime-curTime);
                    drawCircle.txt_progress = timeStr;
                    if(!drawCircle.isOver && angle <= 360){
                        drawCircle.setAngle(angle);
                        drawCircle.times = times;
                        drawCircle.invalidate();
                    }else {
                        angle = 0;
                        drawCircle.isOver = true;
                    }
                    //这个不用
                    /*angle = angle > 360 ? 0 : angle;
                    persentView.Angle = angle;
                    persentView.times = times;
                    String progress = String.valueOf((int) ((float)angle/(float)360*100))+"%";
                    persentView.txt_progress = progress;
                    //刷新自定义View
                    persentView.invalidate();*/


                }
            });
        }
    };

    private Handler handler = new Handler();

    /**
     *
     * @param time
     * @return
     */
    private String formatMillisToString(long time){
        int day = (int) (time/(3600*24));
        int hour = (int) ((time/(3600*24))%3600);
        int minute = (int)((time/60)%60);
        int second = (int) (time%60);
        StringBuilder sb = new StringBuilder();
        if(day > 0){
            sb.append(day+"天");
        }
        if(hour > 0){
            if(hour < 10){
                sb.append("0"+hour);
            }else{
                sb.append(hour);
            }
            sb.append(":");
        }
        if(minute > 0){
            if(minute < 10){
                sb.append("0"+minute);
            }else{
                sb.append(minute);
            }
            sb.append(":");
        } else if(minute == 0){
            sb.append("00:");
        }
        if(second > 0){
            if(second < 10){
                sb.append("0"+second);
            }else{
                sb.append(second);
            }
        }else if(second == 0){
            sb.append("00");
        }

        return sb.toString();
    }


}
