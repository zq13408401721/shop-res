package com.launchmode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.launchmode.utils.MyUtils;

public class InstanceActivity extends AppCompatActivity {

    private static final String TAG = InstanceActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instance);
        MyUtils.print(TAG,"task stack id:"+getTaskId());
    }
}
