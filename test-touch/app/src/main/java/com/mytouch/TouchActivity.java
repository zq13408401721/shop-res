package com.mytouch;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
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

public class TouchActivity extends AppCompatActivity {


    RecyclerView recy_left;
    RecyclerView recy_right;

    List<String> list;
    MyAdapter myAdapterLF;
    MyAdapter myAdapterRT;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
        context = this;

        recy_left = findViewById(R.id.recyclerview_left);
        recy_right = findViewById(R.id.recyclerview_right);

        list = new ArrayList<>();
        for(int i=0; i<100; i++){
            list.add("item:"+i);
        }
        myAdapterLF = new MyAdapter(1);
        recy_left.setLayoutManager(new LinearLayoutManager(this));
        recy_left.setAdapter(myAdapterLF);

        myAdapterRT = new MyAdapter(2);
        recy_right.setLayoutManager(new LinearLayoutManager(this));
        recy_right.setAdapter(myAdapterRT);
    }



    class MyAdapter extends RecyclerView.Adapter{

        int type;
        public MyAdapter(int type){
            this.type = type;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_list_item,null);
            VH vh = new VH(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            VH vh = (VH) viewHolder;
            if(type == 1){
                vh.txtItem.setTextColor(Color.parseColor("#ff0000"));
            }else{
                vh.txtItem.setTextColor(Color.parseColor("#ccff00"));
            }
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
