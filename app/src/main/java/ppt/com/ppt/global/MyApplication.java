package ppt.com.ppt.global;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

/**
 * Created by Caiwnj on 2016/12/15.
 */

public class MyApplication extends Application {

    private static int mMainThredId;
    private static Handler mHandler;
    private static Context mContext;

    public static int getMainThredId() {
        return mMainThredId;
    }

    public static Handler getHandler() {
        return mHandler;
    }

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mHandler = new Handler();
        mMainThredId = Process.myTid();
    }
}
