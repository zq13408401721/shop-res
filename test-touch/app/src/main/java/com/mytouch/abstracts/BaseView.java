package com.mytouch.abstracts;

/**
 *抽象类 -- 基类
 */
public abstract class BaseView {

    protected String name;

    public BaseView(){
        initView();

        //执行一个玩游戏的逻辑
        //第一步开始游戏
        startGame();
        //第二步游戏过程
        playGame();
        //第三步游戏结束
        stopGame();
    }

    protected abstract void initView();  //抽象方法只有定义 没有具体实现

    protected abstract void startGame(); //开始游戏

    protected abstract void playGame(); //游戏过程

    protected abstract void stopGame(); //结束



}
