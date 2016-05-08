package com.airhome.airmemo;

import android.content.Context;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by airhome on 2016/5/8.
 */
public class CommonUtils {
    public static void showShortToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
    public static String formatTime(String formatStyle){
        SimpleDateFormat sdf = new SimpleDateFormat(formatStyle, Locale.getDefault());
        return sdf.format(new Date());
    }
}
