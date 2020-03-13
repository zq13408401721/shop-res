package com.myview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    int start_time = 9*60;//按分钟结算，早上9点的分钟
    int end_time = 17*60+30;  //下午5：30的分钟

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        //每天早上9：00到下午5：30以后的时间判断
        int now = hour*60+minute; //计算当前时间的分钟
        if(now >= start_time && now <= end_time){
            //表示在9：00~~17：30之间  做隐藏
        }else{
            //做显示
        }
        Log.i("calendar","hour"+hour);
    }
}
