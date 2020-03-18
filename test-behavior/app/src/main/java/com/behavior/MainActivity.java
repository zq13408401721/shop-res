package com.behavior;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnNormal;
    Button btnBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNormal = findViewById(R.id.btn_normal);
        btnNormal.setOnClickListener(this);
        btnBehavior = findViewById(R.id.btn_mybehavior);
        btnBehavior.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_normal:
                openNormal();
                break;
            case R.id.btn_mybehavior:
                openBehavior();
                break;
        }
    }

    private void openNormal(){
        Intent intent = new Intent(this,NormalActivity.class);
        startActivity(intent);
    }

    private void openBehavior(){
        Intent intent = new Intent(this,MyBehaviorActivity.class);
        startActivity(intent);
    }
}
