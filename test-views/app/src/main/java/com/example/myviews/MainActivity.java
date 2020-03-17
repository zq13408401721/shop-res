package com.example.myviews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_1;
    private Button btn_2;
    private Button btn_3;

    private RelativeLayout txt_group;

    private String[] times = new String[]{"0:00","10:00","12:00","15:00","19:00"};

    private List<TimeVo> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        btn_1 = findViewById(R.id.btn_view1);
        btn_2 = findViewById(R.id.btn_view2);
        btn_3 = findViewById(R.id.btn_view3);
        txt_group = findViewById(R.id.txt_group);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);

        /*RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) txt_group.getLayoutParams();
        layoutParams.height = 200;
        txt_group.setLayoutParams(layoutParams);*/

        /*for(int i=0; i<times.length; i++){
            TimeVo timeVo = new TimeVo();
            timeVo.time = dateToStamp("2019-10-14 "+times[i]);
            list.add(timeVo);
        }

        //获取当前的系统时间
        long now = System.currentTimeMillis();
        if(list.get(0).time <= now && now < list.get(1).time){
            updateStatus(0);
        }else if(list.get(1).time <= now && now < list.get(2).time){
            updateStatus(1);
        }else if(list.get(2).time <= now && now < list.get(3).time){
            updateStatus(2);
        }else if(list.get(3).time <= now && now < list.get(4).time){
            updateStatus(3);
        }else{
            updateStatus(4);
        }*/

    }

    private void updateStatus(int index){
        for(int i=0; i<list.size(); i++){
            if(i < index){
                list.get(i).status = "已开抢";
            }else if(i == index){
                list.get(i).status = "抢购中";
            }else{
                list.get(i).status = "即将开始";
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_view1:
                Intent intent = new Intent(this,View1Activity.class);
                startActivity(intent);
                break;
            case R.id.btn_view2:
                Intent intent1 = new Intent(this,View2Activity.class);
                startActivity(intent1);
                break;
            case R.id.btn_view3:
                Intent intent3 = new Intent(this,View3Activity.class);
                startActivity(intent3);
                break;
        }
    }

    /*
     * 将时间转换为时间戳
     */
    public static long dateToStamp(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long ts = date.getTime();
        return ts;
    }
}
