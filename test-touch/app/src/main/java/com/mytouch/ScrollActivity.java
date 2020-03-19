package com.mytouch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ScrollActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter myAdapter;
    List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        initView();
    }

    private void initView(){
        recyclerView = findViewById(R.id.recyclerview);

        list = new ArrayList<>();
        for(int i=0; i<100; i++){
            String item = "item:"+i;
            list.add(item);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(list);
        //recyclerView.addItemDecoration(new MyItemDecoration(this));
        recyclerView.setAdapter(myAdapter);
    }
}
