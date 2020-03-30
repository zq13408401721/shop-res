package com.launchmode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.launchmode.utils.MyUtils;

public class InfoActivity extends AppCompatActivity {
    private static final String TAG = InfoActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Log.i(TAG,"TaskId:"+getTaskId());
        MyUtils.print(TAG,"task stack id:"+getTaskId());
        MyUtils.print(TAG,"onCreate");
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
