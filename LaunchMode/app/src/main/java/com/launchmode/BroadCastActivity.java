package com.launchmode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.Button;

import com.launchmode.utils.MyUtils;

public class BroadCastActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = BroadCastActivity.class.getSimpleName();

    private final String NOTIFICATION_ACTION = "com.launchmode.receiver"; //广播过滤action

    Button btnSendBroadCast;
    Button btnSendOrderBroadCast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast);
        btnSendBroadCast = findViewById(R.id.btn_sendBroadCast);
        btnSendOrderBroadCast = findViewById(R.id.btn_sendOrderBroadCast);
        MyUtils.print(TAG,"processId:"+ Process.myPid()); //当前的进程ID
    }

    private void sendBroadcast() {
        Intent intent = new Intent(NOTIFICATION_ACTION);
        intent.putExtra("msg","hello");
        sendBroadcast(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sendBroadCast:  //发送普通广播
                sendBroadcast();
                break;
            case R.id.btn_sendOrderBroadCast:  //发送有序广播
                break;
        }
    }
}
