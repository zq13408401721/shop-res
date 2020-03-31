package com.launchmode;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.launchmode.utils.MyUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    Button btn_standard;
    Button btn_singleTop;
    Button btn_singleTask;
    Button btn_singleInstance;

    Button btn_openInfo;
    Button btn_openReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_standard = findViewById(R.id.btn_standard);
        btn_singleTop = findViewById(R.id.btn_singleTop);
        btn_singleTask = findViewById(R.id.btn_singleTask);
        btn_singleInstance = findViewById(R.id.btn_singleInstance);
        btn_openInfo = findViewById(R.id.btn_openInfo);
        btn_openReceiver = findViewById(R.id.btn_openReceiver);

        btn_standard.setOnClickListener(this);
        btn_singleTop.setOnClickListener(this);
        btn_singleTask.setOnClickListener(this);
        btn_singleInstance.setOnClickListener(this);
        btn_openInfo.setOnClickListener(this);
        btn_openReceiver.setOnClickListener(this);

        MyUtils.print(TAG,"onCreate");
        MyUtils.print(TAG,"task stack id:"+getTaskId());
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_standard:
                Intent intent = new Intent(MainActivity.this,StandardActivity.class);
                //intent.setFlags(Intent.FLAG)
                startActivity(intent);
                break;
            case R.id.btn_singleTop:
                Intent intent_singletop = new Intent(MainActivity.this,SingleTopActivity.class);
                startActivity(intent_singletop);
                break;
            case R.id.btn_singleTask:
                Intent intent_in = new Intent(MainActivity.this,InstanceActivity.class);
                startActivity(intent_in);
                break;
            case R.id.btn_singleInstance:
                Intent intent_instance = new Intent(MainActivity.this,PayActivity.class);
                startActivity(intent_instance);
                break;
            case R.id.btn_openInfo:
                Intent intent_info = new Intent(MainActivity.this,InfoActivity.class);
                startActivity(intent_info);
                break;
            case R.id.btn_openReceiver:
                Intent intent_receiver = new Intent(MainActivity.this,BroadCastActivity.class);
                startActivity(intent_receiver);
                break;
        }
    }
}
