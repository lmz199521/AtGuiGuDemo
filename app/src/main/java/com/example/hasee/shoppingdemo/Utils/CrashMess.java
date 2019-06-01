package com.example.hasee.shoppingdemo.Utils;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Lmz on 2019/05/17
 * 实现一个 收集未捕获异常的类
 */
public class CrashMess implements Thread.UncaughtExceptionHandler{
    public static final String TAG="CrashHandler";

    //CrashHandler 实例
    public static  CrashMess instance;
    //程序的Context 对象
    private Application application;
    //系统默认的 ubcaughtExceptionHandler 类
    private Thread.UncaughtExceptionHandler  uncaughtExceptionHandler;
    //保证只有一个实例
    public CrashMess(Context context) {
       application = (Application) context.getApplicationContext();
       uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
       Thread.setDefaultUncaughtExceptionHandler(this);
    }

    //获取实例
    public static CrashMess getInstance(Context context){
        CrashMess crash =instance;
        if (crash == null){
            synchronized (CrashMess.class){
                crash =instance;
                if (crash == null){
                    crash =new CrashMess(context.getApplicationContext());
                    instance = crash;
                }
            }
        }
        return crash;
    }


    @Override
    public void uncaughtException(Thread t, Throwable e) {
        StringWriter stringWriter =new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        StringBuffer sb =new StringBuffer();
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-mm-dd hh-mm-ss");
        String format = simpleDateFormat.format(new Date());
        sb.append("时间："+format);
        sb.append("\r\n");
        sb.append("出错线程ID："+t.getId()+"-------出错线程名称："+t.getName());
        sb.append("\r\n");
        sb.append("出错原因："+e.getMessage());
        sb.append("\r\n");
        sb.append("异常原因："+e.getCause());
        sb.append("\r\n");
        sb.append("出错的栈："+e.toString());

        StackTraceElement[] stackTrace = e.getStackTrace();
        for (int i=0;i<stackTrace.length;i++){
            sb.append("\r\n");
            sb.append("出错的行数"+(i+1)+"："+e.getStackTrace()[i]);
        }

        Log.d("lmz",sb.toString());

    }
}
