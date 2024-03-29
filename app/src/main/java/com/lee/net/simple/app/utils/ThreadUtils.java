package com.lee.net.simple.app.utils;


import com.lee.net.simple.app.MyApplication;

/**
 * 和线程相关的工具类
 * @author liyong
 */

public class ThreadUtils {

    /**
     * 把Runnable方法提交到主线程执行
     *
     * @param runnable
     */
    public static void runOnUiThread(Runnable runnable) {
        if (android.os.Process.myTid() == MyApplication.getMainTid()) {
            runnable.run();
        } else {
            MyApplication.getHandler().post(runnable);//运行一个handler
        }
    }
}
