package com.hornhuang.encryption.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Create by leek on 3/24/22
 * @email: 814484626@qq.com
 */
public class DateUtil {

    public static String getCurTimeFully() {
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd-HH:mm:ss-a");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        return sdf.format(date); // 输出已经格式化的现在时间（24小时制）
    }

}
