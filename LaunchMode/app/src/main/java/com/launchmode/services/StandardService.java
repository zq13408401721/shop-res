package com.launchmode.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.launchmode.InfoActivity;

/**
 * service -> activity需要broadcast
 */
public class StandardService extends Service {

    public static final String TAG = StandardService.class.getSimpleName();

    private StandardBinder binder = new StandardBinder();

    private Context context;

    @Override
    public void onCreate() {
        Log.i(TAG,"onCreate"+Thread.currentThread().getId());
        super.onCreate();
        context = getBaseContext();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"onStartCommand"+Thread.currentThread().getId());
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"onBind"+Thread.currentThread().getId());
        return binder;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"onDestroy"+Thread.currentThread().getId());
        super.onDestroy();
    }


    //用来做交互的Binder
    public class StandardBinder extends Binder implements MyBinder{

        @Override
        public void showInfo(){
            Intent intent = new Intent(context, InfoActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  //intent->Activity需要设置flag为FLAG_ACTIVITY_NEW_TASK
            startActivity(intent);
        }

        ICallback cb;
        int progress = 0;

        CountDownTimer countDownTimer;

        @Override
        public void setCallback(ICallback callback){
            cb = callback;
        }

        //开始下载执行耗时操作
        @Override
        public void download(){

            if(countDownTimer == null){
                countDownTimer = new CountDownTimer(60000,60) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        progress ++;
                        //每隔1s执行一个操作
                        if(cb != null){
                            cb.callback(progress);
                        }

                    }

                    @Override
                    public void onFinish() {

                    }
                };
                countDownTimer.start();
            }

        }


    }


    public interface MyBinder extends IBinder{
        void download();
        void setCallback(ICallback callback);
        void showInfo();
    }

    /**
     * 定义回调接口
     */
    public interface ICallback{
        void callback(int progress);
    }

}
