package com.hornhuang.encryption.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.DrawableRes;
import androidx.annotation.IntRange;

import com.hornhuang.encryption.R;

/**
 * @author: Create by leek on 4/1/21
 * @email: 814484626@qq.com
 */
public class StatusBarUtils {

    /**
     * 设置状态栏颜色背景图
     * @param activity
     * @param id
     */
    public static void setStatusBarDrawable(Activity activity, @DrawableRes int id) {
        //利用反射机制修改状态栏背景
        int identifier = activity.getResources().getIdentifier("statusBarBackground", "id", "android");
        View statusBarView = activity.getWindow().findViewById(identifier);
        if (statusBarView != null) {
            statusBarView.setBackgroundResource(id);
        }
    }

}