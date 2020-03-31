package com.launchmode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Telephony;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.launchmode.services.StandardService;

public class StandardActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = StandardActivity.class.getSimpleName();

    StandardService.MyBinder standardBinder;

    Button btnBind;
    Button btnCall;
    Button btnUnbind;
    Button btnOpen;
    Button btnOther;
    Button btnOpenNoHistroy;
    TextView txt_progress;
    Button btnDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard);
        initView();
        Log.i(TAG,"TaskId:"+getTaskId());
    }

    //用来实现activity和service交互
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            standardBinder = (StandardService.MyBinder) service;
            //匿名内部类
            standardBinder.setCallback(new StandardService.ICallback() {
                @Override
                public void callback(final int progress) {
                    txt_progress.post(new Runnable() {
                        @Override
                        public void run() {
                            txt_progress.setText("当前的进度："+progress);
                        }
                    });
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private void initView(){
        btnBind = findViewById(R.id.btn_bind);
        btnCall = findViewById(R.id.btn_call);
        btnUnbind = findViewById(R.id.btn_unbind);
        btnOpen = findViewById(R.id.btn_open);
        btnOther = findViewById(R.id.btn_openOther);
        txt_progress = findViewById(R.id.txt_down_progress);
        btnOpenNoHistroy = findViewById(R.id.btn_openNoHistroy);
        btnDown = findViewById(R.id.btn_startDown);
        btnBind.setOnClickListener(this);
        btnCall.setOnClickListener(this);
        btnUnbind.setOnClickListener(this);
        btnOpen.setOnClickListener(this);
        btnOther.setOnClickListener(this);
        btnOpenNoHistroy.setOnClickListener(this);
        btnDown.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_bind:
                Intent intent = new Intent(this, StandardService.class);
                bindService(intent,connection,BIND_AUTO_CREATE);
                break;
            case R.id.btn_call:
                standardBinder.showInfo();
                break;
            case R.id.btn_startDown:
                standardBinder.download();
                break;
            case R.id.btn_unbind:
                unbindService(connection);
                connection = null;
                break;
            case R.id.btn_open:
                Intent intent1 = new Intent(this,InfoActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
                break;
            case R.id.btn_openOther:
                Intent intent2 = new Intent();
                ComponentName componentName = new ComponentName("com.myservice","com.myservice.MainActivity");
                intent2.setComponent(componentName);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent2);
                break;
            case R.id.btn_openNoHistroy:
                Intent intent_nohistroy = new Intent(StandardActivity.this,NoHistroyActivity.class);
                intent_nohistroy.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent_nohistroy);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(connection != null){
            unbindService(connection);
        }
    }
}
