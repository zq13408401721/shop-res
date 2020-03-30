package com.mytouch.abstracts;

public class ChickenGame extends BaseView {
    @Override
    protected void initView() {
        super.name = "吃鸡";
    }

    @Override
    protected void startGame() {
        System.out.print(name +"start game!");
    }

    @Override
    protected void playGame() {
        System.out.print(name +"playing game!");
    }

    @Override
    protected void stopGame() {
        System.out.print(name +"stop game!");
    }
}
