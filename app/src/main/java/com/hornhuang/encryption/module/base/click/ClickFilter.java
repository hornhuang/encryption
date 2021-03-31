package com.hornhuang.encryption.module.base.click;

import android.view.View;

import java.lang.reflect.Field;

/**
 * @author: Create by leek on 3/31/21
 * @email: 814484626@qq.com
 *
 * 如何处理第三方View内部的点击事件
 * 可能我们使用一个自定义控件,他的内部已经消费了点击事件,但是需要避免重复点击,我们不可能去改内部的代码,也不能重新设置点击事件,那样会丢失内部的处理逻辑;这时可以采用反射的处理方式,再结合代理来实现无缝替换
 *
 * 作者：uncochen
 * 链接：https://www.jianshu.com/p/d98e22c127ed
 * 来源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class ClickFilter {
    public static void setFilter(View view) {
        try {
            Field field = View.class.getDeclaredField("mListenerInfo");
            field.setAccessible(true);
            Class listInfoType = field.getType();
            Object listinfo = field.get(view);
            Field onclickField = listInfoType.getField("mOnClickListener");
            View.OnClickListener origin = (View.OnClickListener) onclickField.get(listinfo);
            onclickField.set(listinfo, new ClickProxy(origin));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}