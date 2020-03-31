package com.launchmode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.launchmode.utils.MyUtils;

public class NoHistroyActivity extends AppCompatActivity {

    private static final String TAG = NoHistroyActivity.class.getSimpleName();


    Button btn_openInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_histroy);
        btn_openInfo =findViewById(R.id.btn_openInfo);

        MyUtils.print(TAG,"onCreate");

        btn_openInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoHistroyActivity.this,InfoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        MyUtils.print(TAG,"onRestart");
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
    protected void onDestroy() {
        super.onDestroy();
        MyUtils.print(TAG,"onDestroy");
    }
}
