package com.mytouch;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ScrollViewActivity extends AppCompatActivity {

    List<String> list;
    RecyclerView recyclerView;
    MyAdapter myAdapter;

    TextView txt_header;
    MyScrollView scrollView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);
        recyclerView = findViewById(R.id.recyclerview);
        txt_header = findViewById(R.id.txt_header);
        /*scrollView = findViewById(R.id.scrollView);
        scrollView.header_height = txt_header.getLayoutParams().height;*/

        list = new ArrayList<>();
        for(int i=0; i<50;i++){
            list.add("item"+i);
        }
        myAdapter = new MyAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
