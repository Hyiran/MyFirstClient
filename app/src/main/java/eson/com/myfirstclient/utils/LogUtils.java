package eson.com.myfirstclient.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * LogUtils工具说明：
 * 1、只输出等级大于LEVEL的日志
 * 所以在开发和产品发布后通过修改LEVEL来选择性输出日志
 * 等LEVEL=NOTHING则屏蔽所有日志
 * 2 v,d,i,w,e均对应两个方法
 * 若不设置TAG或TAG为空则为设置默认TAG
 */
public class LogUtils {
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING  = 6;
    public static final int LEVEL  = NOTHING;
    public static final String  SEPARATOR = ",";

    public static void v(String message){
        if(LEVEL <= VERBOSE){
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String tag = getDefaultTag(stackTraceElement);
            Log.v(tag,getLogInfo(stackTraceElement) + message);
        }
    }

    public static void v(String tag,String message){
        if (LEVEL <= VERBOSE){
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if(TextUtils.isEmpty(tag)){
                tag = getDefaultTag(stackTraceElement);
            }
            Log.v(tag,getLogInfo(stackTraceElement) + message);
        }
    }

    public static void d(String message){
        if(LEVEL <= DEBUG){
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String tag = getDefaultTag(stackTraceElement);
            Log.d(tag, getLogInfo(stackTraceElement) + message);
        }
    }

    public static void d(String tag,String message){
        if (LEVEL <= VERBOSE){
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if(TextUtils.isEmpty(tag)){
                tag = getDefaultTag(stackTraceElement);
            }
            Log.d(tag, getLogInfo(stackTraceElement) + message);
        }
    }

    public static void i(String message){
        if(LEVEL <= DEBUG){
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String tag = getDefaultTag(stackTraceElement);
            Log.i(tag, getLogInfo(stackTraceElement) + message);
        }
    }

    public static void i(String tag,String message){
        if (LEVEL <= VERBOSE){
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if(TextUtils.isEmpty(tag)){
                tag = getDefaultTag(stackTraceElement);
            }
            Log.i(tag, getLogInfo(stackTraceElement) + message);
        }
    }

    public static void w(String message){
        if(LEVEL <= DEBUG){
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String tag = getDefaultTag(stackTraceElement);
            Log.w(tag, getLogInfo(stackTraceElement) + message);
        }
    }

    public static void w(String tag,String message){
        if (LEVEL <= VERBOSE){
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if(TextUtils.isEmpty(tag)){
                tag = getDefaultTag(stackTraceElement);
            }
            Log.w(tag, getLogInfo(stackTraceElement) + message);
        }
    }

    public static void e(String tag,String message){
        if(LEVEL <= ERROR){
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if(TextUtils.isEmpty(tag)){
                tag = getDefaultTag(stackTraceElement);
            }
            Log.e(tag,message);
        }
    }

    /**
     * s输出日志所包含的信息
     * @param stackTraceElement
     */
    private static String getLogInfo(StackTraceElement stackTraceElement) {
        StringBuilder loginfoStringBuilder = new StringBuilder();
        //获取线程名
        String threadName = Thread.currentThread().getName();
        //获取线程ID
        long threadID = Thread.currentThread().getId();
        //获取文件名，即XXX.java
        String fileName = stackTraceElement.getFileName();
        //获取类名，即包名+类名
        String className = stackTraceElement.getClassName();
        //获取方法名称
        String methodName = stackTraceElement.getMethodName();
        //获取日志输出行数
        int lineNumber = stackTraceElement.getLineNumber();

        loginfoStringBuilder.append("[");
        loginfoStringBuilder.append("threadID=" + threadID).append(SEPARATOR);
        loginfoStringBuilder.append("threadName=" + threadName).append(SEPARATOR);
        loginfoStringBuilder.append("fileName=" + fileName).append(SEPARATOR);
        loginfoStringBuilder.append("className=" + className).append(SEPARATOR);
        loginfoStringBuilder.append("methodName=" + methodName).append(SEPARATOR);
        loginfoStringBuilder.append("lineNumber=" + lineNumber).append(SEPARATOR);
        loginfoStringBuilder.append("]");
        return loginfoStringBuilder.toString();
    }

    /**
     * 获取默认的TAG名称
     * 比如在MainActivity.java中调用了日志输出
     * 则TAG为MainActivity
     * @param stackTraceElement
     */
    private static String getDefaultTag(StackTraceElement stackTraceElement) {
        String fileName = stackTraceElement.getFileName();
        String stringArray[] = fileName.split("\\.");
        String tag = stringArray[0];
        return tag  ;
    }
}
