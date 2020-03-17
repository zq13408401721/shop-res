package com.example.myviews;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myviews.views.TagsLayout;

public class View1Activity extends AppCompatActivity {

    private TagsLayout tagsLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view1);
        initView();
    }

    private void initView(){
        tagsLayout = findViewById(R.id.tag_layout);
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        String[] strs = {"这就是一个自定义组件","android入门","你是一个程序猿","java","python从入门到放弃"};
        for(int i=0; i< strs.length; i++){
            TextView txt = new TextView(this);
            txt.setText(strs[i]);
            txt.setTextColor(Color.WHITE);
            txt.setBackgroundResource(R.drawable.txt_bg);
            tagsLayout.addView(txt,lp);
        }
    }
}
