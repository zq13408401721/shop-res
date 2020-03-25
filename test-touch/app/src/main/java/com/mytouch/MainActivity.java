package com.mytouch;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.mytouch.utils.MyUtils;
import com.mytouch.views.MyView;
import com.mytouch.views.MyViewGroup;

/**
 * https://www.cnblogs.com/chengxuyinli/p/9979826.html
 * 事件分发
 */
public class MainActivity extends AppCompatActivity {

    private static String TAG;

    private MyViewGroup myViewGroup;
    private MyView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TAG = this.getClass().getName();
        initView();
    }

    private void initView(){
        myViewGroup = findViewById(R.id.myVG);
        myView = findViewById(R.id.myVW);

        myViewGroup.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        myViewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("click","onclick");
            }
        });

        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getId();
                Log.i("view click","click");
            }
        });
        myView.setLongClickable(true);
        myView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
        myView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        MyUtils.show_events(TAG,ev,"dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
        //return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        MyUtils.show_events(TAG,event,"onTouchEvent");
        /*myView.performClick();
        myViewGroup.performClick();*/
        //return super.onTouchEvent(event);
        return true;
    }
}
