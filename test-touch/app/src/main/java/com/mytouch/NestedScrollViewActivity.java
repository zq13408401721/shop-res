package com.mytouch;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NestedScrollViewActivity extends AppCompatActivity {


    MyScrollView myScrollView;
    MyRecyclerView recyclerView;
    TextView txtHeader;

    List<String> list;
    Context context;
    MyAdapter myAdapter;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_scroll_view);
        context = this;

        recyclerView = findViewById(R.id.recyclerview);
        myScrollView = findViewById(R.id.view_scroll);
        myScrollView.recyclerView = recyclerView;
        txtHeader = findViewById(R.id.txt_header);
        myScrollView.header_height = 600;
        recyclerView.setNestedScrollingEnabled(true);
        initView();
    }


    private void initView(){
        list = new ArrayList<>();
        for(int i=0; i<100; i++){
            list.add("item"+i);
        }
        myAdapter = new MyAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
    }

    class MyAdapter extends RecyclerView.Adapter{

        int type;
        public MyAdapter(){

        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_list_item,null);
            NestedScrollViewActivity.VH vh = new NestedScrollViewActivity.VH(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            NestedScrollViewActivity.VH vh = (NestedScrollViewActivity.VH) viewHolder;
            vh.txtItem.setTextColor(Color.parseColor("#ffff00"));
            vh.txtItem.setText(list.get(i));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    class VH extends RecyclerView.ViewHolder{

        TextView txtItem;

        public VH(@NonNull View itemView) {
            super(itemView);
            txtItem = itemView.findViewById(R.id.txt_item);
        }
    }
}
