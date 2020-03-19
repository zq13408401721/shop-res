package com.mytouch;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {

    List<String> list;

    public MyAdapter(List<String> list){
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
        if(getItemViewType(i) % 2 == 0){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item,null);
        }else{
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item1,null);
        }
        VH vh = new VH(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        VH vh = (VH) viewHolder;
        vh.txt_item.setText(list.get(i));
        vh.img.setVisibility(View.VISIBLE);
        vh.img1.setVisibility(View.VISIBLE);
        if(i == 0){
            vh.img.setVisibility(View.GONE);
        }else if(i == getItemCount()-1){
            if(i % 2 == 0){
                vh.img.setVisibility(View.GONE);
            }else{
                vh.img1.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position%2==0 ? 0 : 1;
    }

    class VH extends RecyclerView.ViewHolder {
        TextView txt_item;
        ImageView img;
        ImageView img1;
        public VH(@NonNull View itemView) {
            super(itemView);
            txt_item = itemView.findViewById(R.id.txt_item);
            img = itemView.findViewById(R.id.img);
            img1 = itemView.findViewById(R.id.img1);
        }
    }
}
