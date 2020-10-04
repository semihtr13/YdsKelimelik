package com.setsoft.ydskelimelik.util;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

public class Utils {
    public static boolean isNullOrEmpty(String text){
        return (text==null|| TextUtils.isEmpty(text));
    }
    public static void nextActivity(Context context,Class<?>cls){
        context.startActivity(new Intent(context,cls).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }
    public static void showMessage(Context context,String message,boolean isLong){
        if(isLong){
            Toast.makeText(context,message,Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}
