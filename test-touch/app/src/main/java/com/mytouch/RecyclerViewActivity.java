package com.mytouch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mytouch.model.DataBean;
import com.mytouch.recyclerviews.ParentRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    ParentRecyclerView parentRecyclerView;

    List<DataBean> list;
    MyAdapter myAdapter;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        context = this;
        initData();
        initView();
    }

    private void initData(){
        list = new ArrayList<>();
        for(int i=0; i<100; i++){
            DataBean dataBean = new DataBean();
            dataBean.title = "parent"+i;
            dataBean.type = i%20 == 0 ? 1 : 0;
            if(dataBean.type == 1){
                dataBean.list = new ArrayList<>();
                for(int j=0; j<20; j++){
                    DataBean.ChildDataBean child = new DataBean.ChildDataBean();
                    child.name = "child"+j;
                    dataBean.list.add(child);
                }
            }
            list.add(dataBean);
            /*if(i%20 == 0){
                dataBean.type = 1;
            }else{
                dataBean.type = 0;
            }*/
        }
    }

    private void initView(){
        parentRecyclerView = findViewById(R.id.recyclerview);
        myAdapter = new MyAdapter();
        parentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        parentRecyclerView.setAdapter(myAdapter);
    }

    /**
     * 外层的recyclerview的适配器
     */
    class MyAdapter extends RecyclerView.Adapter{

        //onCreateViewHolder参数i表示当前条目的类别
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view;
            int type = i;
            RecyclerView.ViewHolder vh;
            if(type == 0){
                view = LayoutInflater.from(context).inflate(R.layout.layout_item_child,viewGroup,false);
                vh = new ChildVH(view);
            }else{
                view = LayoutInflater.from(context).inflate(R.layout.layout_item_parent,viewGroup,false);
                vh = new ParentVH(view);
            }
            return vh;
        }

        //onBindViewHolder  参数i表示当前条目的下标
        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            int type = getItemViewType(i);
            DataBean dataBean = list.get(i);
            if(type == 1){
                ParentVH parentVH = (ParentVH) viewHolder;
                parentVH.txtTitle.setText(dataBean.title);
                //嵌套recyclerview
                ChildAdapter childAdapter = new ChildAdapter(dataBean.list);
                parentVH.itemRecy.setLayoutManager(new LinearLayoutManager(context));
                parentVH.itemRecy.setAdapter(childAdapter);
            }else{
                ChildVH childVH = (ChildVH) viewHolder;
                childVH.txtName.setText(dataBean.title);
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public int getItemViewType(int position) {
            return list.get(position).type;
        }
    }

    /**
     * 嵌套的recyclerview的adapter
     */
    class ChildAdapter extends RecyclerView.Adapter{

        List<DataBean.ChildDataBean> childList;
        public ChildAdapter(List<DataBean.ChildDataBean> childList){
            this.childList = childList;
        }


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_list_item,null);
            ChildItemVh vh = new ChildItemVh(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            ChildItemVh vh = (ChildItemVh) viewHolder;
            vh.txtItem.setText(this.childList.get(i).name);
        }

        @Override
        public int getItemCount() {
            return this.childList.size();
        }
    }

    /**
     * 父容器的itemViewHolder
     */
    class ParentVH extends RecyclerView.ViewHolder{

        TextView txtTitle;
        RecyclerView itemRecy;

        public ParentVH(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title);
            itemRecy = itemView.findViewById(R.id.item_recy);
        }
    }

    /**
     * 嵌套容器的itemViewHolder
     */
    class ChildVH extends RecyclerView.ViewHolder{

        TextView txtName;

        public ChildVH(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_name);
        }
    }

    /**
     * 嵌套的recyclerview的item
     */
    class ChildItemVh extends RecyclerView.ViewHolder{

        TextView txtItem;
        public ChildItemVh(@NonNull View itemView) {
            super(itemView);
            txtItem = itemView.findViewById(R.id.txt_item);
        }
    }
}
