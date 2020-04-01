package com.launchmode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.Button;

import com.launchmode.utils.MyUtils;

public class BroadCastActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = BroadCastActivity.class.getSimpleName();

    public static final String NOTIFICATION_ACTION = "com.launchmode.receiver"; //广播过滤action
    public static final String NOTIFICATION_NORMAL_MYBROADCAST_ACTION = "com.launchmode.mybroadcast"; //mybroadcast
    public static final String NOTIFICATION_ORDER_ACTION = "com.launchmode.orderbroadcast"; //有序广播

    Button btnSendNormalBroadCast;
    Button btnSendBroadCast;
    Button btnSendOrderBroadCast;



    TestBroadCast testBroadCast;

    LocalBroadcastManager localBroadcastManager; //只在当前应用生效的广播管理


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast);

        btnSendNormalBroadCast = findViewById(R.id.btn_sendNormalMyBroadCast);
        btnSendBroadCast = findViewById(R.id.btn_sendBroadCast);
        btnSendOrderBroadCast = findViewById(R.id.btn_sendOrderBroadCast);
        MyUtils.print(TAG,"processId:"+ Process.myPid()); //当前的进程ID

        btnSendNormalBroadCast.setOnClickListener(this);
        btnSendBroadCast.setOnClickListener(this);
        btnSendOrderBroadCast.setOnClickListener(this);

        //注册广播
        registerBroadCast();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterBroadCast();
    }

    private void sendBroadcast() {
        Intent intent = new Intent(NOTIFICATION_ACTION);
        intent.putExtra("msg","hello");
        sendBroadcast(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sendNormalMyBroadCast:
                sendNormalMyBroadCast();
                break;
            case R.id.btn_sendBroadCast:  //发送普通广播
                sendBroadcast();
                break;
            case R.id.btn_sendOrderBroadCast:  //发送有序广播
                sendOrderBroadCast();
                break;
        }
    }

    //向mybroadcast发广播
    private void sendNormalMyBroadCast(){
        Intent intent = new Intent(NOTIFICATION_NORMAL_MYBROADCAST_ACTION);
        intent.putExtra("key","当前的消息来源于BroadCast普通广播");
        localBroadcastManager.sendBroadcast(intent);
    }

    //发送有序广播
    private void sendOrderBroadCast(){
        Intent intent = new Intent(NOTIFICATION_ORDER_ACTION);
        intent.putExtra("appkey","消息来源于有序广播");
        sendOrderedBroadcast(intent,null);
    }

    //注册广播
    private void registerBroadCast(){
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        testBroadCast = new TestBroadCast();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NOTIFICATION_NORMAL_MYBROADCAST_ACTION);
        localBroadcastManager.registerReceiver(testBroadCast,intentFilter);
    }

    //取消广播的注册
    private void unregisterBroadCast(){
        if(testBroadCast != null){
            localBroadcastManager.unregisterReceiver(testBroadCast);
            testBroadCast = null;
        }
    }

    //广播
    class TestBroadCast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra("key");
            MyUtils.print(TAG,msg);
        }
    }
}
