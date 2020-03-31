package com.launchmode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.launchmode.utils.MyUtils;

public class SingleTopActivity extends AppCompatActivity {

    private static final String TAG = SingleTopActivity.class.getSimpleName();

    Button btnSingleTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_top);

        btnSingleTop = findViewById(R.id.btn_singleTop);

        MyUtils.print(TAG,"onCreate taskId:"+getTaskId());
        btnSingleTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleTopActivity.this,SingleTopActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        MyUtils.print(TAG,"onNewIntent taskId:"+getTaskId());
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
