package com.airhome.airmemo;

import android.content.Context;
import android.content.SharedPreferences;

import com.airhome.airmemo.lock.GestureLockView;

/**
 * Created by airhome on 2015/12/6.
 */
public class Preferences {
    private static final String SF_NAME = "com.airhome.airmemo";
    private static final String IS_FIRST_IN = "isFirstIn";
    private static final String IS_LOCKED = "isLocked";
    private static final String PASSWORD = "password";

    private static SharedPreferences preferences;

    private static SharedPreferences getInstance(Context context) {
        if (preferences == null) {
            preferences = context.getSharedPreferences(SF_NAME, Context.MODE_PRIVATE);
        }
        return preferences;
    }

    public static boolean isFirstIn(Context context) {
        return getInstance(context).getBoolean(IS_FIRST_IN, true);
    }

    public static void setIsFirstInFalse(Context context) {
        SharedPreferences.Editor editor = getInstance(context).edit();
        editor.putBoolean(IS_FIRST_IN, false);
        editor.apply();
    }

    public static boolean isSetLock(Context context) {
        return getInstance(context).getBoolean(IS_LOCKED, false);
    }

    public static void setIsLock(Context context, boolean isLocked) {
        SharedPreferences.Editor editor = getInstance(context).edit();
        editor.putBoolean(IS_LOCKED, isLocked);
        editor.apply();
    }

    public static String getPassword(Context context) {
        SharedPreferences sf = getInstance(context);
        return sf.getString(PASSWORD, "");
    }

    public static void savePassword(Context context, String password) {
        SharedPreferences sf = getInstance(context);
        SharedPreferences.Editor editor = sf.edit();
        editor.putString(PASSWORD, password);
        editor.apply();
    }
}
