package com.myservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    TextView txtTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtTask = findViewById(R.id.txt_task);
        Log.i(TAG,"TaskId:"+getTaskId());
        txtTask.setText("onCreate:"+String.valueOf(getTaskId()));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        txtTask.setText("onNewIntent:"+String.valueOf(getTaskId()));
    }
}
