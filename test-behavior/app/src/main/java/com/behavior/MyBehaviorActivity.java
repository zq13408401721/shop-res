package com.behavior;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyBehaviorActivity extends AppCompatActivity {

    List<String> list;
    Context context;
    MyBehaviorActivity.MyAdapter myAdapter;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_behavior);

        recyclerView = findViewById(R.id.recyclerview);
        context = this;

        list = new ArrayList<>();

        for(int i=0; i<100; i++){
            list.add("item data "+i);
        }
        myAdapter = new MyBehaviorActivity.MyAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
    }



    class MyAdapter extends RecyclerView.Adapter{

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_item,null);
            MyBehaviorActivity.VH vh = new MyBehaviorActivity.VH(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            MyBehaviorActivity.VH vh = (MyBehaviorActivity.VH) holder;
            vh.txtItem.setText(list.get(position));
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
