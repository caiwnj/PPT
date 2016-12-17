package ppt.com.ppt.utils;

import android.content.SharedPreferences;

/**
 * Created by Caiwnj on 2016/12/17.
 */

public class SharedPreferencesUtil {
    private static SharedPreferences mSharedPreferences;

    public static void saveSharedPreferences(String key, boolean b) {
        if (mSharedPreferences == null) {
            mSharedPreferences = MyUtil.getContext().getSharedPreferences("config", MyUtil.getContext().MODE_PRIVATE);
        }
        mSharedPreferences.edit().putBoolean(key, b).commit();
    }

    public static boolean getSharedPreferences(String key, boolean defValue) {
        if (mSharedPreferences == null) {
            mSharedPreferences = MyUtil.getContext().getSharedPreferences("config", MyUtil.getContext().MODE_PRIVATE);
        }
        boolean b = mSharedPreferences.getBoolean(key, defValue);
        return b;
    }

}
