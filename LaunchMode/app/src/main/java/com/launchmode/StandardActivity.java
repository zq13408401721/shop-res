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

import com.launchmode.services.StandardService;

public class StandardActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = StandardActivity.class.getSimpleName();

    StandardService.StandardBinder standardBinder;

    Button btnBind;
    Button btnCall;
    Button btnUnbind;
    Button btnOpen;
    Button btnOther;

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
            standardBinder = (StandardService.StandardBinder) service;
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
        btnBind.setOnClickListener(this);
        btnCall.setOnClickListener(this);
        btnUnbind.setOnClickListener(this);
        btnOpen.setOnClickListener(this);
        btnOther.setOnClickListener(this);
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
