package com.myview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class DetailActivity extends AppCompatActivity {

    MyTitles myTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        myTitles = findViewById(R.id.myTitle);
        myTitles.setActivity(this);

        //通过匿名的内部类实现接口回调
        myTitles.setClick(new MyTitles.ITitleClick() {
            @Override
            public void titleClick(View view) {
                switch (view.getId()){
                    case R.id.txt_shared:
                        //当前点击的是分享

                        break;
                    case R.id.txt_return:
                        //点击的返回

                        break;
                }
            }
        });
    }
}
