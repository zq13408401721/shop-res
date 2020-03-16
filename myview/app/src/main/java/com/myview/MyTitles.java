package com.myview;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * 自定义一个标题组件
 * 实现：
 * 1. 返回上一个页面
 * 2. 显示标题
 * 3. 分享
 */
public class MyTitles extends ConstraintLayout implements View.OnClickListener {

    TextView txtReturn; //返回按钮
    TextView txtTitle; //显示标题的文本
    TextView txtShared; //分享按钮

    Context  context;

    AppCompatActivity activity;

    //自定义属性是否显示分享按钮
    private boolean shared_visible;
    //自定义属性标题的内容
    private String title;

    /**
     * 分享
     */
    private String shared_title;
    private String shared_content;
    private String shared_url;
    private Bitmap shared_icon;


    private ITitleClick clickFun; //接口回调

    public MyTitles(Context context) {
        super(context);
    }

    public MyTitles(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }

    public MyTitles(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs);
    }

    private void initView(Context context,AttributeSet attrs){
        this.context = context;
        //获取自定义属性值
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.mytitle);
        shared_visible = typedArray.getBoolean(R.styleable.mytitle_title_shared_visible,false);
        title = typedArray.getString(R.styleable.mytitle_title_word);

        //添加布局内容
        View view = LayoutInflater.from(context).inflate(R.layout.layout_titles,null);
        addView(view,new ConstraintLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
        txtReturn = view.findViewById(R.id.txt_return);
        txtTitle = view.findViewById(R.id.txt_title);
        txtShared = view.findViewById(R.id.txt_shared);

        //设置返回按钮的监听
        txtReturn.setOnClickListener(this);

        if(!TextUtils.isEmpty(title)){
            txtTitle.setText(title);
        }

        //初始化分享按钮的属性和事件
        txtShared.setVisibility(shared_visible ? View.VISIBLE : View.GONE);
        if(shared_visible){
            txtShared.setOnClickListener(this);
        }

    }


    /**
     * 设置activity
     * @param activity
     */
    public void setActivity(AppCompatActivity activity){
        this.activity = activity;
    }

    /**
     * 设置接口回调
     * @param click
     */
    public void setClick(ITitleClick click){
        this.clickFun = click;
    }

    /**
     * 设置分享的内容
     */
    public void setShared(String shared_title, String shared_content, String shared_url, Bitmap shared_icon){
        this.shared_title = shared_title;
        this.shared_content = shared_content;
        this.shared_url = shared_url;
        this.shared_icon = shared_icon;
    }


    /**
     * 处理点击事件
     * @param v
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_return:
                //可以把Activity传进来 直接finish, 还可以通过接口回调
                if(activity != null && !activity.isFinishing()){
                    activity.finish();
                    activity = null;
                }
                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    if(activity != null && !activity.()){
                        activity.finish();
                    }
                }*/
                break;
            case R.id.txt_shared:
                //1.把分享的内容传进来，  通过接口回调
                Toast.makeText(context,"点击分享",Toast.LENGTH_SHORT).show();
                //2.通过接口回调把点击事件传出去
                if(clickFun != null){
                    clickFun.titleClick(v);
                }
                break;
        }
    }

    //点击事件触发的接口回调
    interface ITitleClick{
        void titleClick(View view);
    }
}
