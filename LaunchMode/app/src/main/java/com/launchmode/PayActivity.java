package com.launchmode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.launchmode.utils.MyUtils;

public class PayActivity extends AppCompatActivity {

    private static final String TAG = PayActivity.class.getSimpleName();

    Button btn_instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        btn_instance = findViewById(R.id.btn_openInstance);
        //打印当前的任务栈ID
        MyUtils.print(TAG,"Task Stack id:"+getTaskId());

        btn_instance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PayActivity.this,InstanceActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        MyUtils.print(TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyUtils.print(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyUtils.print(TAG,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyUtils.print(TAG,"onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        MyUtils.print(TAG,"onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyUtils.print(TAG,"onDestroy");
    }
}
